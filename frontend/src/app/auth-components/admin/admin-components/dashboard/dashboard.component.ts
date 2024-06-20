import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-services/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  products: any[] = []; 
  batches: any[] = []; 
  storages: any[] = []; 
  validateForm!: FormGroup;
  batchForm!: FormGroup;
  storageForm!: FormGroup;
  isSpinning: boolean = false;

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router,
    private service: AdminService
  ) {}

  ngOnInit() {
    this.getAllProducts();
    this.getAllBatches();
    this.getAllStorages();

    this.validateForm = this.fb.group({
      name: [null, Validators.required],
      description: [null, Validators.required],
      price: [null, Validators.required],
      cost: [null, Validators.required],
      code: [null, Validators.required],
    });

    this.batchForm = this.fb.group({
      productId: [null, Validators.required],
      receptionDate: [null, Validators.required],
      quantityReceived: [null, Validators.required],
      batchPrice: [null, Validators.required],
      supplier: [null, Validators.required],
      storageId: [null, Validators.required],
      
    });

    this.storageForm = this.fb.group({
      location: [null, Validators.required],      
    });
  }

  
  submitForm() {
    if (this.validateForm.valid) {
      this.isSpinning = true;
      const formData: FormData = new FormData();
      formData.append('name', this.validateForm.get('name')?.value);
      formData.append('description', this.validateForm.get('description')?.value);
      formData.append('price', this.validateForm.get('price')?.value);
      formData.append('cost', this.validateForm.get('cost')?.value);
      formData.append('code', this.validateForm.get('code')?.value);
  
      this.service.postProduct(formData).pipe(
        catchError(() => {
          this.message.error('Error al actualizar el producto. Verifica que el Codigo del Producto sea unico.', { nzDuration: 5000 });
          return of(null);
        })
      ).subscribe((res) => {
        if (res) {
          this.isSpinning = false;
          this.message.success('Product Creado Correctamente!', { nzDuration: 5000 });
          this.getAllProducts();
          this.validateForm.reset();
        }
      });
    }
  }

  submitBatchForm() {
    if (this.batchForm.valid) {
      this.isSpinning = true;

      const formData: FormData = new FormData();
      formData.append('receptionDate', this.batchForm.get('receptionDate')?.value);
      formData.append('quantityReceived', this.batchForm.get('quantityReceived')?.value);
      formData.append('batchPrice', this.batchForm.get('batchPrice')?.value);
      formData.append('supplier', this.batchForm.get('supplier')?.value);
      formData.append('storageId', this.batchForm.get('storageId')?.value); 
      formData.append('productId', this.batchForm.get('productId')?.value); 
      this.service.postBatch(formData).subscribe((res) => {
        this.isSpinning = false;
        this.message.success('Batch Registered Successfully', { nzDuration: 5000 });
        this.getAllBatches();
        this.batchForm.reset();
      });
    }
  }

  submitStorageForm() {
    if (this.storageForm.valid) {
      this.isSpinning = true;
      const formData: FormData = new FormData();
      formData.append('location', this.storageForm.get('location')?.value);
      
      this.service.postStorage(formData).subscribe((res) => {
        this.isSpinning = false;
        this.message.success('Almacenamiento creado correctamente!', { nzDuration: 5000 });
        this.getAllStorages();
      });
    }
  }

  getAllProducts() {
    this.service.getAllProducts().subscribe((res) => {
      this.products = res;
    });
  }

  getAllBatches() {
    this.service.getAllBatches().subscribe((res) => {
      this.batches = res;
    });
  }

  getAllStorages() {
    this.service.getAllStorages().subscribe((res) => {
      this.storages = res;
    });
  }

  deleteProduct(id: number) {
    this.service.deleteProduct(id).subscribe((res) => {
      this.message.success('Product Deleted Successfully', { nzDuration: 5000 });
      this.getAllProducts();
    });
  }

  editProduct(id: number) {
    this.router.navigate(['/admin/product', id]);
  }
}
