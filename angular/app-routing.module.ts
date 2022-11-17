import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductComponent } from './product/product.component';
import { SalesComponent } from './sales/sales.component';

const appRoutes: Routes = [
  { path: 'product', component: ProductComponent},
  { path: 'sales', component: SalesComponent},
  { path: '**', redirectTo: '/product' }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}