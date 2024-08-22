import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customers, Customer } from '../core/interfaces/customers.type';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private apiUrl = 'http://localhost:3000/customers'; // Express API endpoint

  constructor(private http: HttpClient) {}

  // Get all customers
  getCustomers(): Observable<Customers[]> {
    return this.http.get<Customers[]>(this.apiUrl);
  }

  // Get a single customer by ID
  getCustomer(id: string): Observable<Customers> {
    return this.http.get<Customers>(`${this.apiUrl}/${id}`);
  }

  // Create a new customer
  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl, customer);
  }

  // Update an existing customer by ID
  updateCustomer(id: string, post: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${this.apiUrl}/${id}`, post);
  }

  // Delete a customer by ID
  deleteCustomer(id: string): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(this.apiUrl);
  }

  findByFirstName(firstName: string): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl).pipe(
      map((customers) => {
        const regex = new RegExp(firstName, 'i'); // 'i' for case-insensitive search
        return customers.filter((customer) => regex.test(customer.firstName));
      })
    );
  }
}
