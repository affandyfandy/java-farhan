import { Component } from '@angular/core';
import { AuthService } from '../../../../service/auth.service';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css',
})
export class SigninComponent {
  username = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe((user) => {
      if (user) {
        // Redirect to a dashboard or home page after successful login
        alert('login successfully');
        this.router.navigate(['/customers']);
      } else {
        this.errorMessage = 'Invalid email or password';
      }
    });
  }
}
