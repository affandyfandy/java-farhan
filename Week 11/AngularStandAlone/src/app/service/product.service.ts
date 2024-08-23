import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AppConstants } from '../config/app.constants';
import { Product } from '../core/interfaces/product.type';
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

  getProducts(
    sortBy: 'name' | 'price' = 'name',
    sortOrder: 'asc' | 'desc' = 'asc',
    page: number = 1,
    limit: number = 10
  ): Observable<Product[]> {
    const params = new HttpParams()
      .set('_sort', sortBy)
      .set('_order', sortOrder)
      .set('_page', page.toString())
      .set('_limit', limit.toString());

    return this.http.get<Product[]>(this.apiUrl, { params });
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
}

// this.customerService.getProducts('price', 'asc', 1, 10).subscribe((products) => {
//   console.log(products);
// });
