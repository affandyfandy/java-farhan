import {
  Component,
  Input,
  Output,
  EventEmitter,
  OnChanges,
  SimpleChanges,
} from '@angular/core';
import { trigger, transition, style, animate } from '@angular/animations';
import { CustomerService } from '../../../service/customer.service';
import { Customer } from '../../../core/interfaces/customers.type';

@Component({
  selector: 'app-modal-customer',
  templateUrl: './modal-customer.component.html',
  styleUrls: ['./modal-customer.component.css'],
  animations: [
    trigger('enterAnimation', [
      transition(':enter', [
        style({ transform: 'translateY(4px)', opacity: 0 }),
        animate(
          '300ms ease-out',
          style({ transform: 'translateY(0)', opacity: 1 })
        ),
      ]),
      transition(':leave', [
        style({ transform: 'translateY(0)', opacity: 1 }),
        animate(
          '150ms ease-in',
          style({ transform: 'translateY(4px)', opacity: 0 })
        ),
      ]),
    ]),
  ],
})
export class ModalCustomerComponent implements OnChanges {
  @Input() isOpenModal = false;
  @Input() customer: Customer = {
    id: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
  };

  @Output() closeModal = new EventEmitter<void>();
  @Output() customerUpdated = new EventEmitter<void>();

  isEditing = false;
  customers?: Customer[] = [];

  constructor(private customerService: CustomerService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['customer'] && this.customer.id) {
      this.loadCustomer(this.customer.id);
    }
    this.loadCustomers();
  }

  loadCustomer(id: string): void {
    this.customerService.getCustomer(id).subscribe({
      next: (data) => {
        this.customer = data;
        console.log('Customer data loaded:', this.customer);
      },
      error: (e) => console.error('Error loading customer data:', e),
    });
  }

  loadCustomers(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (e) => console.error('Error loading customers:', e),
    });
  }

  close(): void {
    this.closeModal.emit();
  }

  startEditing(): void {
    this.isEditing = true;
  }

  updateCustomer(): void {
    if (this.customer.id) {
      this.customerService
        .updateCustomer(this.customer.id, this.customer)
        .subscribe({
          next: (updatedCustomer) => {
            console.log('Customer updated successfully:', updatedCustomer);
            this.isEditing = false;
            // window.location.reload();
            this.customerUpdated.emit(); // Emit event to notify parent
            this.close();
          },
          error: (e) => console.error('Error updating customer:', e),
        });
    } else {
      console.error('Customer ID is missing');
    }
  }
}
