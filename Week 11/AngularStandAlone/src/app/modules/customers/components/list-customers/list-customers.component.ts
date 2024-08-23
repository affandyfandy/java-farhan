import { Component, Input, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Customer } from '../../../../core/interfaces/customers.type';
import { CustomerService } from '../../../../service/customers.service';
import { FormsModule } from '@angular/forms';
import { ModalCustomerComponent } from '../../../../main/components/modal-customer/modal-customer.component';
import { PhonePipe } from '../../../../core/pipe/phone.pipe';

@Component({
  selector: 'app-list-customers',
  standalone: true,
  imports: [
    RouterModule,
    CommonModule,
    FormsModule,
    ModalCustomerComponent,
    PhonePipe,
  ],
  templateUrl: './list-customers.component.html',
  styleUrls: ['./list-customers.component.css'],
})
export class ListCustomersComponent implements OnInit {
  customers?: Customer[] = [];
  currentCustomer: Customer = {
    id: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
  };
  currentIndex = -1;
  firstName = '';
  @Input() isOpenModal = false;

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.customerService.getCustomers().subscribe((data) => {
      this.customers = data;
    });
  }

  deleteCustomer(id: string | undefined): void {
    if (id) {
      this.customerService.deleteCustomer(id).subscribe(() => {
        this.loadCustomers(); // Reload customers after deletion
      });
    } else {
      console.error('Customer ID is undefined');
    }
  }

  onSearch(): void {
    this.searchTitle();
  }

  searchTitle(): void {
    this.customerService.findByFirstName(this.firstName).subscribe({
      next: (data) => {
        this.customers = data;
        console.log(data);
      },
      error: (e) => console.error(e),
    });
  }

  setActiveCustomer(customer: Customer, index: number): void {
    this.isOpenModal = true;
    this.currentCustomer = customer;
    this.currentIndex = index;
    console.log('Modal opened for customer ID:', customer.id); // Log the customer ID
  }

  handleCloseModal(): void {
    this.isOpenModal = false;
  }
}
