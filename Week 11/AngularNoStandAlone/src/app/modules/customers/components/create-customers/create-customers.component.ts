import { Component } from '@angular/core';
import { Customer } from '../../../../core/interfaces/customers.type';
import { CustomerService } from '../../../../service/customer.service';
import { Router } from '@angular/router';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-create-customers',
  templateUrl: './create-customers.component.html',
  styleUrl: './create-customers.component.css',
})
export class CreateCustomersComponent {
  customer: Customer = {
    id: uuidv4(),
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
  };

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  createCustomer(): void {
    this.customerService.createCustomer(this.customer).subscribe(() => {
      alert('Customer created successfully!');
      this.router.navigate(['/customers']);
    });
  }
}

