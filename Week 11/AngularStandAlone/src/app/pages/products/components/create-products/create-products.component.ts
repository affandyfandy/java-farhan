import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { v4 as uuidv4 } from 'uuid';
import { Product } from '../../../../core/interfaces/product.type';
import { Status } from '../../../../core/interfaces/status.type';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductService } from '../../../../service/product.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-create-products',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './create-products.component.html',
  styleUrls: ['./create-products.component.css'],
})
export class CreateProductsComponent implements OnInit {
  productForm!: FormGroup;
  public Status = Status;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      id: [uuidv4(), Validators.required],
      name: ['', Validators.required],
      price: [undefined, [Validators.required, Validators.min(0)]],
      status: [Status.Active, Validators.required],
      quantity: [undefined, [Validators.required, Validators.min(0)]],
      createdAt: [new Date().toISOString(), Validators.required],
      updatedAt: [new Date().toISOString(), Validators.required],
    });
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      const product: Product = this.productForm.value;
      this.createProduct(product);
    }
  }

  createProduct(productData: Product): void {
    this.productService.createProduct(productData).subscribe(() => {
      alert('Product created successfully');
      this.router.navigate(['/products']);
    });
  }
}
