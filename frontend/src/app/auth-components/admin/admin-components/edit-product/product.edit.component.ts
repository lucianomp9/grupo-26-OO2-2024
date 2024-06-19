import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin-services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product.edit.component.html',
  styleUrls: ['./product.edit.component.scss']
})
export class ProductEditComponent implements OnInit {
  validateForm!: FormGroup;
  productId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private service: AdminService,
    private message: NzMessageService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.productId = Number(params.get('id'));
      if (this.productId) {
        this.loadProductData(this.productId);
      }
    });

    this.validateForm = this.fb.group({
      name: [null, Validators.required],
      description: [null, Validators.required],
      price: [null, Validators.required],
      cost: [null, Validators.required],
      code: [null, Validators.required],
    });
  }

  loadProductData(id: number) {
    this.service.getProduct(id).subscribe(product => {
      this.validateForm.patchValue({
        name: product.name,
        description: product.description,
        price: product.price,
        cost: product.cost,
        code: product.code
      });
    });
  }

  submitForm() {
    if (this.validateForm.valid && this.productId !== null) {
      const formData: FormData = new FormData();
      formData.append('name', this.validateForm.get('name')?.value);
      formData.append('description', this.validateForm.get('description')?.value);
      formData.append('price', this.validateForm.get('price')?.value);
      formData.append('cost', this.validateForm.get('cost')?.value);
      formData.append('code', this.validateForm.get('code')?.value);
  
      this.service.editProduct(this.productId, formData).pipe(
        catchError(() => {
          this.message.error('Error al actualizar el producto. Verifica que el Codigo del Producto sea unico.', { nzDuration: 5000 });
          return of(null);
        })
      ).subscribe((res) => {
        if (res) {
          this.message.success('Producto actualizado correctamente!', { nzDuration: 5000 });
          this.router.navigate(['/admin/dashboard']);
        }
      });
    }
  }
}

