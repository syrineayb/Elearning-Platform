import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/auth/authentication.service';
import { RegisterRequest } from '../../models/register-request';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerRequest: RegisterRequest = { email: "", firstName: "", lastName: "", password: "", roleName: [] };
  errorMsg: string[] = [];
  @ViewChild('passwordInput') passwordInput!: ElementRef;
  passwordVisible: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  login() {
    this.router.navigate(['login']);
  }

  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
    const inputEl: HTMLInputElement = this.passwordInput.nativeElement;
    inputEl.type = this.passwordVisible ? 'text' : 'password';
  }

  register() {
    if (this.registerRequest.roleName.includes('candidate')) {
      this.registerRequest.roleName = ['ROLE_CANDIDATE'];
    } else if (this.registerRequest.roleName.includes('Instructor')) {
      this.registerRequest.roleName = ['ROLE_INSTRUCTOR'];
    }

    this.errorMsg = []; // Clear existing error messages

    this.authService.register(this.registerRequest)
      .subscribe({
        next: () => {

            // Redirect to a default page or handle appropriately
            this.router.navigate(['login']);

        },
        error: (err) => {
          if (err && err.error && err.error.validationErrors) {
            this.errorMsg = err.error.validationErrors; // Assign error messages
          } else {
            this.errorMsg = ['An unexpected error occurred. Please try again.']; // Fallback error message
          }
        }
      });
  }
}
