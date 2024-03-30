import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/auth/authentication.service';
import { RegisterRequest } from '../../models/authentication/register-request';
import { ToastrService } from "ngx-toastr";
import { TokenService } from "../../modules/app-common/services/token/token.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerRequest: RegisterRequest = { email: "", firstname: "", lastname: "", password: "", role: "" };
  registerForm!: FormGroup;
  errorMsg: string[] = [];
  toastShown: boolean = false;
  passwordVisible: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthenticationService,
    private toasterService: ToastrService,
    private router: Router,
    private tokenService: TokenService
  ) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstname: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      lastname: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(50)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(16)]],
      role: ['', Validators.required]
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    if (this.registerForm.invalid) {
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

    // Set the role directly from the form value
    this.registerRequest.role = this.registerForm.value.role;

    this.registerRequest.email = this.registerForm.value.email;
    this.registerRequest.firstname = this.registerForm.value.firstname;
    this.registerRequest.lastname = this.registerForm.value.lastname;
    this.registerRequest.password = this.registerForm.value.password;

    this.authService.register(this.registerRequest).subscribe({
      next: (response) => {
        if (response.access_token) {
          this.tokenService.accessToken = response.access_token || '';
          console.log('Registration successful:', response);
          this.router.navigateByUrl('/login');
        } else {
          console.error('Access token not found in response:', response);
        }
      },
      error: (error) => {
        console.error('Registration error:', error);
        this.errorMsg = [];

        if (error.status === 403) {
          this.toasterService.error('This email is already registered. Please use a different email.', 'Error', {
            positionClass: 'toast-center-center',
            toastClass: 'custom-toast-error',
          });
        } else if (error.status === 400 && error.error && error.error.validationErrors) {
          const validationErrors = error.error.validationErrors;
          validationErrors.forEach((validationError: string) => {
            switch (validationError) {
              case 'firstname':
                this.errorMsg.push('First name is required');
                break;
              case 'lastname':
                this.errorMsg.push('Last name is required');
                break;
              case 'email':
                this.errorMsg.push('Invalid email format');
                break;
              case 'password':
                this.errorMsg.push('Password is required and must be at least 6 characters long');
                break;
              case 'role':
                this.errorMsg.push('Please select a user type');
                break;
              default:
                this.errorMsg.push('An unexpected error occurred. Please try again later.');
                break;
            }
          });

          if (this.errorMsg.length > 0) {
            this.toasterService.error(this.errorMsg.join('<br/>'), 'Validation Error', {
              positionClass: 'toast-center-center',
              toastClass: 'custom-toast-error',
              enableHtml: true
            });
          }
        } else {
          this.toasterService.error('An unexpected error occurred. Please try again later.', 'Server Error', {
            positionClass: 'toast-center-center',
            toastClass: 'custom-toast-error',
          });
        }
      }
    });

  }

  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
    const passwordInput = document.getElementById('password') as HTMLInputElement;
    if (passwordInput) {
      passwordInput.type = this.passwordVisible ? 'text' : 'password';
    }
  }

}
