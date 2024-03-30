import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { AuthRequest } from '../../models/authentication/auth-request';
import { AuthResponse } from '../../models/authentication/auth-response';
import { environment } from '../../../environments/environment';
import {catchError, map, Observable, of, throwError} from 'rxjs';

import {RegisterRequest} from "../../models/authentication/register-request";
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



  login(authRequest: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/api/auth/login`, authRequest).pipe(
      map(response => {
        this.isLogged = true;
        this.username = response.username; // Assuming the server returns the username in the response
        localStorage.setItem('token', response.token);
        localStorage.setItem('username', response.username);
        return response;
      }),
      catchError(error => {
        this.isLogged = false;
        this.username = undefined;
        return of(null);
      })
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



/*  isAuthenticated(): boolean {
    // Check if the token exists in local storage or any other authentication logic
    const token = localStorage.getItem('token');
    return !!token; // Convert token to boolean (true if token exists, false otherwise)
  }*/



  isAuthenticated(): boolean {
    return this.isLogged;
  }
  getUserRole(): Observable<string> {

    return this.http.get<string>(`${this.baseUrl}/api/auth/user-role`);
  }
}
