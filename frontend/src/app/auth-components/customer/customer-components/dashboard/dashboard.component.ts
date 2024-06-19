import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../customer-service/customer.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  products: any[] = [];
  purchaseForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private service: CustomerService // Asegúrate de importar tu servicio correctamente
  ) {}

  ngOnInit(): void {
    this.initPurchaseForm();
    this.getAllProducts();
  }

  initPurchaseForm(): void {
    this.purchaseForm = this.fb.group({
      quantity: [null, Validators.required],
      deliveryAddress: [null],
      comment: [null]
    });
  }

  

  getAllProducts(): void {
    this.service.getAllProducts().subscribe((res) => {
      this.products = res;
    });
  }
}
