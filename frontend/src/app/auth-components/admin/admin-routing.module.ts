import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './admin-components/dashboard/dashboard.component';
import { ProductEditComponent } from './admin-components/edit-product/product.edit.component';

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  { path: 'product/:id', component: ProductEditComponent }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
