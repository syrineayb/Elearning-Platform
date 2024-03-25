import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthRequest } from '../../models/auth-request';
import { AuthResponse } from '../../models/auth-response';
import { environment } from '../../../environments/environment';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import {RegisterRequest} from "../../models/register-request";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class  AuthenticationService {
  baseUrl = environment.app.baseUrl;
  isLogged = false;
  username: string | undefined; // Initialize as undefined

  constructor(private http: HttpClient, private router: Router) { // Inject Router here
    this.checkLoginStatus();
  }

  login(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/api/auth/login`, authRequest).pipe(
      tap(response => {
        this.isLogged = true;
        this.username = response.username || ''; // Assign a default value if response.username is null
      }),
      catchError(this.handleError<AuthResponse>('login', {} as AuthResponse))
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    this.isLogged = false;
    this.username = undefined;
    this.router.navigate(['login']);
    // Clear the username
  }

  private checkLoginStatus(): void {
    const token = localStorage.getItem('token');
    this.isLogged = !!token;
    if (this.isLogged) {
      this.username = localStorage.getItem('username') || ''; // Assign a default value if localStorage.getItem('username') returns null
    }
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
  register(registerRequest: RegisterRequest) {
    console.log(registerRequest.role);
    return this.http.post<any>(`${this.baseUrl}/api/auth/register`, registerRequest);
  }
  isAuthenticated(): boolean {
    // Check if the token exists in local storage or any other authentication logic
    const token = localStorage.getItem('token');
    return !!token; // Convert token to boolean (true if token exists, false otherwise)
  }

 /*
  getUsername(): Observable<string> {
    // Make an HTTP request to fetch the username
    return this.http.get<string>(`${this.baseUrl}/api/user/username`);
  }

  */

  //ng g s profiles/profiles
}
