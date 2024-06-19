import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../customer-service/customer.service';
import { Router } from '@angular/router';
import { StorageService } from '../../../../auth-services/storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  products: any[] = [];
  sales: any[] = [];
  purchaseForm: FormGroup;
  
    constructor(
      private fb: FormBuilder,
      private message: NzMessageService,
      private router: Router,
      private service: CustomerService,
    ) {}
  
    ngOnInit() {
      this.getAllProducts();
      this.getAllSales();
  
      this.purchaseForm = this.fb.group({
        productId: [null, Validators.required],
        quantity: [null, Validators.required],
      });

    }
  
   
  
    submitPurchaseForm() {
      if (this.purchaseForm.valid) {
        const formData: FormData = new FormData();
        
        formData.append('userId', StorageService.getUserId());
        formData.append('productId', this.purchaseForm.get('productId')?.value);
        formData.append('quantity', this.purchaseForm.get('quantity')?.value);
    
        this.service.postSale(formData).subscribe((res) => {
          this.message.success('Compra realizada correctamente!', { nzDuration: 5000 });
          this.purchaseForm.reset();
          this.getAllSales;
        });
      }
    }
    
    getAllSales() {
      this.service.getAllSales().subscribe((res) => {
        this.sales = res;
      });
    }
  
    getAllProducts() {
      this.service.getAllProducts().subscribe((res) => {
        this.products = res;
      });
    }

  }
  