import { Component, OnInit } from '@angular/core';
import { AgGridAngular, AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';
import { ProductService } from '../../../../service/product.service';
import { Product } from '../../../../core/interfaces/product.type';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ListProductsActionComponent } from '../../../../main/components/list-products-action/list-products-action.component';

@Component({
  selector: 'app-list-products',
  standalone: true,
  imports: [
    AgGridModule,
    AgGridAngular,
    DatePipe,
    FormsModule,
    ListProductsActionComponent,
  ], // Make sure to include ListProductsActionComponent here
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css'],
  providers: [DatePipe],
})
export class ListProductsComponent implements OnInit {
  products: Product[] = [];
  rowData: Product[] = [];
  productName = '';

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
        console.log('data', data);
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

  openProduct(product: Product): void {
    // Implement logic to open the product details, if needed
  }
}
