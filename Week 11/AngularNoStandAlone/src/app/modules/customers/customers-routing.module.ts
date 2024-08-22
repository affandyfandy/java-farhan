import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomersComponent } from './components/customers/customers.component';
import { ListCustomersComponent } from './components/list-customers/list-customers.component';
import { CreateCustomersComponent } from './components/create-customers/create-customers.component';

const routes: Routes = [
  {
    path: '',
    component: CustomersComponent,
    children: [
      {
        path: '',
        component: ListCustomersComponent,
      },
      {
        path: 'create',
        component: CreateCustomersComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CustomersRoutingModule {}
