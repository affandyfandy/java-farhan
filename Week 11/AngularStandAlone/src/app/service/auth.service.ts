import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AppConstants } from '../config/app.constants';
import { User } from '../core/interfaces/user.type';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = `${AppConstants.BASE_API_URL}/users`;

  constructor(private http: HttpClient) {}

  // Method for logging in
  login(username: string, password: string): Observable<User | null> {
    return this.http
      .get<User[]>(this.apiUrl)
      .pipe(
        map(
          (users) =>
            users.find(
              (user) => user.username === username && user.password === password
            ) || null
        )
      );
  }

  // Method for registering a new user
  register(user: Omit<User, 'id'>): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }
}
