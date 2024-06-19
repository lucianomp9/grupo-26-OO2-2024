import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerRoutingModule } from './customer-routing.module';
import { DashboardComponent } from './customer-components/dashboard/dashboard.component';
import { DemoNgZorroAntdModule } from '../../DemoNgZorroAntdModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ViewProductsByCategoryComponent } from './customer-components/view-products-by-category/view-products-by-category.component';
import { PostReservationComponent } from './customer-components/post-reservation/post-reservation.component';



@NgModule({
  declarations: [
    DashboardComponent,
    ViewProductsByCategoryComponent,
    PostReservationComponent,
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    
  ]
})
export class CustomerModule { }
