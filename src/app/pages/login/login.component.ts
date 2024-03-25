import {Component, ElementRef, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {AuthRequest} from "../../models/auth-request";
import {AuthenticationService} from "../../services/auth/authentication.service";
import {TokenService} from "../../modules/app-common/services/token/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  authRequest: AuthRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];
  @ViewChild('passwordInput') passwordInput!: ElementRef;
  passwordVisible: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {
  }
  togglePasswordVisibility() {
    console.log('Toggle password visibility method called');
    this.passwordVisible = !this.passwordVisible;
    const inputEl: HTMLInputElement = this.passwordInput.nativeElement;
    inputEl.type = this.passwordVisible ? 'text' : 'password';
  }

  login() {
    this.errorMsg = [];
    this.authService.login(this.authRequest)
      .subscribe({
        next: (res) => {
          this.tokenService.accessToken = res.access_token as string;
          localStorage.setItem('accessToken', res.access_token || '');
          localStorage.setItem('username', res.username || '');

          this.redirectConnectedUser(); // Redirect after successful login
          console.log(res);
        },
        error: (err) => {
          console.log(err);
          if (err && err.error && err.error.validationErrors) {
            this.errorMsg = err.error.validationErrors; // Assign error messages
          } else {
            this.errorMsg.push(err.errorMsg); // Fallback error message
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




  register() {
    this.router.navigate(['register']);
  }
}
