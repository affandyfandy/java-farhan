import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AppConstants } from '../config/app.constants';
import { Product } from '../core/interfaces/product.type';
import { Status } from '../core/interfaces/status.type';
@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl = `${AppConstants.BASE_API_URL}/products`; // Express API endpoint

  constructor(private http: HttpClient) {}

  // Get all products
  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  // Get a single product by ID
  getProduct(id: string): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }

  // Create a new customer
  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, product);
  }

  // Update an existing product by ID
  updateProduct(id: string, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/${id}`, product);
  }

  // Delete a product by ID
  deleteProduct(id: string): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/${id}`);
  }

  // Delete all products
  deleteAll(): Observable<any> {
    return this.http.delete(this.apiUrl);
  }

  findByName(name: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl).pipe(
      map((products) => {
        const regex = new RegExp(name, 'i'); // 'i' for case-insensitive search
        return products.filter((product) => regex.test(product.name));
      })
    );
  }

  updateProductStatus(id: string, status: Status): Observable<Product> {
    return this.http.patch<Product>(`${this.apiUrl}/${id}`, { status });
  }
}
