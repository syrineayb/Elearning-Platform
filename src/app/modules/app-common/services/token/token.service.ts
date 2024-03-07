import { Injectable } from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() {
  }

  set token(token: string) {
    localStorage.setItem('token', token);
  }

  get token() {
    return localStorage.getItem('token') as string;
  }

  isTokenValid() {
    const token = this.token;
    if (!token) {
      return false;
    }
    // Decode the token
    const jwtHelper = new JwtHelperService();
    const decodedToken = jwtHelper.decodeToken(token);
    console.log('Decoded Token:', decodedToken); // Log decoded token payload
    // Check expiry date
    const isTokenExpired = jwtHelper.isTokenExpired(token);
    if (isTokenExpired) {
      localStorage.clear();
      return false;
    }
    return true;
  }


  isTokenNotValid() {
    return !this.isTokenValid();
  }

  get userRoles(): string[] {
    const token = this.token;
    if (token) {
      const jwtHelper = new JwtHelperService();
      const decodedToken = jwtHelper.decodeToken(token);
      console.log(decodedToken.authorities);
      return decodedToken.authorities || []; // Assuming roles are stored under 'role' in the token
    }
    return [];
  }

  isUser(): boolean {
    return this.userRoles.includes('ROLE_USER');
  }

  isAdmin(): boolean {
    const token = this.token;
    if (!token) {
      return false;
    }

    // Decode the token
    const jwtHelper = new JwtHelperService();
    const decodedToken = jwtHelper.decodeToken(token);

    // Check if "role" array exists in the decoded token payload
    if (decodedToken && decodedToken.role) {
      // Check if the user has the "ROLE_ADMIN" role
      return decodedToken.role.some((role: any) => role.name === 'ROLE_ADMIN');
    }

    return false;
  }

  isCandidate(): boolean {
    const token = this.token;
    if (!token) {
      return false;
    }

    // Decode the token
    const jwtHelper = new JwtHelperService();
    const decodedToken = jwtHelper.decodeToken(token);

    // Check if "role" array exists in the decoded token payload
    if (decodedToken && decodedToken.role) {
      // Check if the user has the "ROLE_CANDIDATE" role
      return decodedToken.role.some((role: any) => role.name === 'ROLE_CANDIDATE');
    }

    return false;
  }


  isInstructor(): boolean {
    const token = this.token;
    if (!token) {
      return false;
    }

    // Decode the token
    const jwtHelper = new JwtHelperService();
    const decodedToken = jwtHelper.decodeToken(token);

    // Check if "role" array exists in the decoded token payload
    if (decodedToken && decodedToken.role) {
      // Check if the user has the "ROLE_INSTRUCTOR" role
      return decodedToken.role.some((role: any) => role.name === 'ROLE_INSTRUCTOR');
    }

    return false;
  }

  isNotCandidate(): boolean {
    return !this.isCandidate();
  }

  isNotInstructor(): boolean {
    return !this.isInstructor();
  }

  hasNoRole(): boolean {
    const roles = this.userRoles;
    return roles.length === 0 || roles.includes('ROLE_NO_ROLE');
  }
}
