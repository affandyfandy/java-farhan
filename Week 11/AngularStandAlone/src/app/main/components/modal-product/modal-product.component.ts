// modal-product.component.ts
import {
  Component,
  Input,
  Output,
  EventEmitter,
  OnChanges,
  SimpleChanges,
} from '@angular/core';
import { trigger, transition, style, animate } from '@angular/animations';
import { CommonModule } from '@angular/common';
import {
  FormsModule,
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Product } from '../../../core/interfaces/product.type';
import { Status } from '../../../core/interfaces/status.type';
import { ProductService } from '../../../service/product.service';

@Component({
  selector: 'app-modal-product',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './modal-product.component.html',
  styleUrls: ['./modal-product.component.css'], // Corrected from styleUrl to styleUrls
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
export class ModalProductComponent implements OnChanges {
  @Input() isOpenModal = false;
  @Input() product: Product = {
    id: '',
    name: '',
    price: 0,
    status: Status.Active,
    quantity: 0,
    createdAt: null,
    updatedAt: null,
  };

  @Output() closeModal = new EventEmitter<void>();
  @Output() productUpdated = new EventEmitter<void>();

  isEditing = false;
  products?: Product[] = [];
  productForm: FormGroup;
  public Status = Status;

  constructor(private productService: ProductService, private fb: FormBuilder) {
    this.productForm = this.fb.group({
      id: [{ value: '', disabled: true }],
      name: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      status: [Status, Validators.required],
      quantity: [0, [Validators.required, Validators.min(0)]],
      createdAt: [{ value: null, disabled: true }],
      updatedAt: [{ value: null, disabled: true }],
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['product'] && this.product.id) {
      this.loadProduct(this.product.id);
    }
    this.loadProducts();
  }

  loadProduct(id: string): void {
    this.productService.getProduct(id).subscribe({
      next: (data) => {
        this.product = data;
        this.productForm.patchValue(this.product);
        console.log('Product data loaded:', this.product);
      },
      error: (e) => console.error('Error loading product data:', e),
    });
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe({
      next: (data) => {
        this.products = data;
      },
      error: (e) => console.error('Error loading products:', e),
    });
  }

  close(): void {
    this.closeModal.emit();
    this.isEditing = false;
  }

  startEditing(): void {
    this.isEditing = true;
  }

  onStatusToggle(event: Event): void {
    const target = event.target as HTMLInputElement;
    const newStatus = target.checked ? Status.Active : Status.Deactive;
    this.productForm.get('status')?.setValue(newStatus);
    this.product.status = newStatus;
  }

  updateProduct(): void {
    if (this.productForm.valid) {
      const updatedProduct = this.productForm.getRawValue();
      if (updatedProduct.id) {
        // Update the updatedAt field with the current date and time
        updatedProduct.updatedAt = new Date().toISOString();

        this.productService
          .updateProduct(updatedProduct.id, updatedProduct)
          .subscribe({
            next: (updatedProduct) => {
              console.log('Product updated successfully:', updatedProduct);
              this.isEditing = false;
              this.productUpdated.emit(); // Emit event to notify parent
              this.close();
            },
            error: (e) => console.error('Error updating product:', e),
          });
      } else {
        console.error('Product ID is missing');
      }
    } else {
      console.error('Form is invalid');
    }
  }
}
