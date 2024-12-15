import { Routes } from '@angular/router';
import { RouterConfig } from './config/app.constants';

export const routes: Routes = [
  {
    path: RouterConfig.HOME.path,
    loadChildren: () =>
      import('./modules/home/home.module').then((m) => m.HomeModule),
    // Lazy loading HomeModule
  },
  {
    path: RouterConfig.CUSTOMER.path,
    loadChildren: () =>
      import('./modules/customers/customers.module').then(
        (m) => m.CustomersModule
      ), // Lazy loading CustomerModule
  },
  {
    path: RouterConfig.AUTH.path,
    loadChildren: () =>
      import('./modules/auth/auth.module').then((m) => m.AuthModule), // Lazy loading AuthModule
  },
  {
    path: RouterConfig.PRODUCTS.path,
    loadChildren: () =>
      import('./pages/products/products.routes').then((m) => m.productRoutes), // Lazy loading Product Routes
  },
  {
    path: RouterConfig.NOT_FOUND.path, // Route to display the NotFoundComponent
    component: RouterConfig.NOT_FOUND.component,
  },
  {
    path: '**',
    redirectTo: RouterConfig.NOT_FOUND.path, // Redirects to the 'not-found' path
  },
];
