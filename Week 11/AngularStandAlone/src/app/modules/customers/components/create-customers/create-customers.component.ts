import { Component } from '@angular/core';
import { Customer } from '../../../../core/interfaces/customers.type';
import { CustomerService } from '../../../../service/customers.service';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-create-customers',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
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
      this.router.navigate(['/customers']); // Redirect to posts list after creation
    });
  }
}
