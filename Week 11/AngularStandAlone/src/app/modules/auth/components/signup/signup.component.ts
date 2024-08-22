import { Component } from '@angular/core';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../../../service/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from '../../../../core/interfaces/user.type';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  user: User = {
    id: uuidv4(),
    username: '',
    email: '',
    password: '',
    gender: 'male',
  };

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.register(this.user).subscribe(() => {
      alert('Register Successfully');
      // Redirect to sign-in page after successful registration
      this.router.navigate(['/auth/signin']);
    });
  }
}
