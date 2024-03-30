import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/auth/authentication.service';
import { AuthRequest } from '../../models/authentication/auth-request';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import {TokenService} from "../../modules/app-common/services/token/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  authRequest: AuthRequest = {email: '', password: ''};
  errorMsg: string[] = [];
  @ViewChild('passwordInput') passwordInput!: ElementRef;
  passwordVisible: boolean = false;
  toastShown: boolean = false;
  loginForm!: FormGroup; // Define a FormGroup variable

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private toasterService: ToastrService,
    private formBuilder: FormBuilder,
    private tokenService: TokenService,
    ) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]], // Add validators for email
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(16)]],
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      if (!this.toastShown) {
        this.toasterService.error('Please fill in all the required fields with correct information.', 'Form Validation Error', {
          positionClass: 'toast-center-center',
          toastClass: 'custom-toast-error',
        });
        this.toastShown = true;
        setTimeout(() => {
          this.toastShown = false;
        }, 3000);
      }
      return;
    }

    this.authRequest = this.loginForm.value;

    this.authService.login(this.authRequest).subscribe({
      next: (response) => {
        if (response === null) {
          this.toasterService.error('An unexpected error occurred. Please try again later.', 'Login Error', {
            positionClass: 'toast-center-center',
            toastClass: 'custom-toast-error',
          });
          return;
        }

        console.log('Login successful:', response);
        this.tokenService.accessToken = response.access_token as string;
        localStorage.setItem('accessToken', response.access_token || '');
        localStorage.setItem('username', response.username || '');

        this.redirectConnectedUser();
      },
      error: (error) => {
        console.error('Login error:', error);

        if (error.status === 403) {
          this.toasterService.error('Invalid email or password. Please try again.', 'Login Error', {
            positionClass: 'toast-center-center',
            toastClass: 'custom-toast-error',
          });
        } else {
          this.toasterService.error('An unexpected error occurred. Please try again later.', 'Server Error', {
            positionClass: 'toast-center-center',
            toastClass: 'custom-toast-error',
          });
        }
      }
    });
  }

  private redirectConnectedUser() {
    console.log('Redirecting user...');
    console.log('Is Candidate:', this.tokenService.isCandidate());
    console.log('Is Instructor:', this.tokenService.isInstructor());
    console.log('Is Admin:', this.tokenService.isAdmin());
    // Add more debug logs if needed

    if (this.tokenService.isCandidate()) {
      console.log('Redirecting to candidate route...');
      this.router.navigate(['candidate']); // Redirect to candidate route
    } else if (this.tokenService.isInstructor()) {
      console.log('Redirecting to instructor route...');
      this.router.navigate(['instructor']); // Redirect to trainer route
    } else if (this.tokenService.isAdmin()) {
      console.log('Redirecting to admin route...');
      this.router.navigate(['admin']); // Redirect to admin route
    } else {
      console.log('No matching role found for redirection.');
      // Handle the case where no matching role is found
    }
  }

  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
    const passwordInput = document.getElementById('password') as HTMLInputElement;
    if (passwordInput) {
      passwordInput.type = this.passwordVisible ? 'text' : 'password';
    }
  }


}
