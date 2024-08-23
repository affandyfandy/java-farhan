import { Component, OnInit } from '@angular/core';
import { AgGridAngular } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';
import { ProductService } from '../../../../service/product.service';
import { Product } from '../../../../core/interfaces/product.type';

@Component({
  selector: 'app-list-products',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css'],
})
export class ListProductsComponent implements OnInit {
  products: Product[] = [];
  rowData: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe((data) => {
      this.products = data;
      this.rowData = this.products.map((product) => ({
        name: product.name,
        price: product.price,
        status: product.status,
        quantity: product.quantity,
        createdAt: product.createdAt,
        updatedAt: product.updatedAt,
      }));
    });
  }

  colDefs: ColDef[] = [
    { field: 'name', headerName: 'Name' },
    { field: 'price', headerName: 'Price' },
    { field: 'status', headerName: 'Status' },
    { field: 'quantity', headerName: 'Quantity' },
    { field: 'createdAt', headerName: 'Created At' },
    { field: 'updatedAt', headerName: 'Updated At' },
  ];
}
