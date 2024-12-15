import { Routes } from '@angular/router';
import { ProductsComponent } from './components/products/products.component';
import { ListProductsComponent } from './components/list-products/list-products.component';
import { CreateProductsComponent } from './components/create-products/create-products.component';

export const productRoutes: Routes = [
  {
    path: '',
    component: ProductsComponent,
    children: [
      { path: '', component: ListProductsComponent },
      { path: 'create', component: CreateProductsComponent },
    ],
  },
];
