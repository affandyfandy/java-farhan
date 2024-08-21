import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./modules/home/home.module').then((m) => m.HomeModule),
    // Lazy loading HomeModule
  },
  {
    path: 'customers',
    loadChildren: () =>
      import('./modules/customers/customers.module').then(
        (m) => m.CustomersModule
      ), // Lazy loading CustomerModule
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./modules/auth/auth.module').then((m) => m.AuthModule), // Lazy loading AuthModule
  },
  {
    path: '**',
    redirectTo: '',
  },
];
