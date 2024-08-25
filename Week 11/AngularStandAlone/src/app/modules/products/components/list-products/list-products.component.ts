// list-products.component.ts

import { Component, OnInit, Input } from '@angular/core';
import { AgGridAngular, AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';
import { ProductService } from '../../../../service/product.service';
import { Product } from '../../../../core/interfaces/product.type';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ListProductsActionComponent } from '../../../../main/components/list-products-action/list-products-action.component';
import { Status } from '../../../../core/interfaces/status.type';
import { ModalProductComponent } from '../../../../main/components/modal-product/modal-product.component';

@Component({
  selector: 'app-list-products',
  standalone: true,
  imports: [
    AgGridModule,
    AgGridAngular,
    DatePipe,
    FormsModule,
    ListProductsActionComponent,
    ModalProductComponent,
  ],
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css'],
  providers: [DatePipe],
})
export class ListProductsComponent implements OnInit {
  products: Product[] = [];
  rowData: Product[] = [];
  productName = '';
  currentProduct: Product = {
    id: '',
    name: '',
    price: 0,
    status: Status.Active,
    quantity: 0,
    createdAt: null,
    updatedAt: null,
  };
  currentIndex = -1;
  @Input() isOpenModal = false;

  colDefs: ColDef[] = [
    { field: 'name', headerName: 'Product Name' },
    { field: 'price', headerName: 'Price' },
    { field: 'status', headerName: 'Status' },
    { field: 'quantity', headerName: 'Quantity' },
    { field: 'createdAt', headerName: 'Created At' },
    { field: 'updatedAt', headerName: 'Updated At' },
    {
      headerName: 'Actions',
      field: 'action',
      cellRenderer: ListProductsActionComponent,
      cellRendererParams: {
        context: this,
      },
      minWidth: 350,
    },
  ];

  constructor(
    private productService: ProductService,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe((data) => {
      this.products = data;
      this.updateRowData(this.products);
    });
  }

  onSearch(): void {
    this.searchProductName();
  }

  searchProductName(): void {
    this.productService.findByName(this.productName).subscribe({
      next: (data) => {
        this.products = data;
        this.updateRowData(this.products);
      },
      error: (e) => console.error(e),
    });
  }

  updateRowData(products: Product[]): void {
    this.rowData = products.map((product) => ({
      ...product,
      createdAt: this.datePipe.transform(product.createdAt, 'fullDate'),
      updatedAt: this.datePipe.transform(product.updatedAt, 'fullDate'),
    }));
  }

  deleteProduct(id: string): void {
    this.productService.deleteProduct(id).subscribe({
      next: () => {
        this.loadProducts(); // Reload products after deletion
      },
      error: (e) => console.error(e),
    });
  }

  setActiveProduct(product: Product, index: number): void {
    this.isOpenModal = true;
    this.currentProduct = product;
    this.currentIndex = index;
    console.log('Modal opened for product ID:', product.id);
  }

  onCloseModal(): void {
    this.isOpenModal = false;
  }

  onProductUpdated(): void {
    this.loadProducts();
  }

  updateProductStatus(id: string, currentStatus: Status): void {
    // Determine new status
    const newStatus =
      currentStatus === Status.Active ? Status.Deactive : Status.Active;

    this.productService.updateProductStatus(id, newStatus).subscribe({
      next: (updatedProduct) => {
        console.log('Product status updated successfully:', updatedProduct);
        this.loadProducts(); // Refresh the product list
      },
      error: (e) => console.error('Error updating product status:', e),
    });
  }
}
