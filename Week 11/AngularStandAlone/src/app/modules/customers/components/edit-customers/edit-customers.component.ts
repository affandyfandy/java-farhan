import { Component } from '@angular/core';
import { Customer } from '../../../../core/interfaces/customers.type';
import { CustomerService } from '../../../../service/customers.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-customers',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './edit-customers.component.html',
  styleUrl: './edit-customers.component.css',
})
export class EditCustomersComponent {
  customer: Customer = { firstName: '', lastName: '', email: '', phone: '' };

  constructor(
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const customerId = this.route.snapshot.paramMap.get('id');
    this.customerService.getCustomer(customerId!).subscribe((data) => {
      this.customer = data;
    });
  }

  updatePost(): void {
    const postId = this.route.snapshot.paramMap.get('id');
    this.customerService
      .updateCustomer(postId!, this.customer)
      .subscribe(() => {
        this.router.navigate(['/customers']);
      });
  }
}
