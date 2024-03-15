import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/auth/authentication.service';
import { RegisterRequest } from '../../models/register-request';
import {TokenService} from "../../modules/app-common/services/token/token.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerRequest: RegisterRequest = { email: "", firstname: "", lastname: "", password: "", role: "" };
  errorMsg: string[] = [];
  @ViewChild('passwordInput') passwordInput!: ElementRef;
  passwordVisible: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
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
    if (this.registerRequest.role.includes('candidate')) {
      this.registerRequest.role = 'ROLE_CANDIDATE';
    } else if (this.registerRequest.role.includes('Instructor')) {
      this.registerRequest.role = 'ROLE_INSTRUCTOR';
    }

    this.errorMsg = []; // Clear existing error messages

    this.authService.register(this.registerRequest)
      .subscribe({
        next: (response) => {
          if (response.access_token) {

            this.tokenService.accessToken = response.access_token || ''; // Assign accessToken directly

            // Redirect to a default page or handle appropriately
            this.router.navigate(['login']);
          } else {
            console.error('Access token not found in response:', response);
            // Handle error case where access token is missing in the response
          }
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
