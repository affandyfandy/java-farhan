import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomersRoutingModule } from './customers-routing.module';
import { ListCustomersComponent } from './components/list-customers/list-customers.component';
import { CreateCustomersComponent } from './components/create-customers/create-customers.component';
import { EditCustomersComponent } from './components/edit-customers/edit-customers.component';
import { CustomersComponent } from './components/customers/customers.component';
import { FormsModule } from '@angular/forms';
import { ModalCustomerComponent } from '../../shared/components/modal-customer/modal-customer.component';

@NgModule({
  declarations: [
    ListCustomersComponent,
    CreateCustomersComponent,
    EditCustomersComponent,
    CustomersComponent,
    ModalCustomerComponent,
  ],
  imports: [CommonModule, CustomersRoutingModule, FormsModule],
})
export class CustomersModule {}
