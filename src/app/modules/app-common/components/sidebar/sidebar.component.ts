import { Component, OnInit } from '@angular/core';

import {Router} from "@angular/router";
import {AuthenticationService} from "../../../../services/auth/authentication.service";
import {TokenService} from "../../services/token/token.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit{
  showLinks: boolean = false;
  userRoles: string[] = [];

  constructor(private router: Router,public authService: AuthenticationService,private tokenService: TokenService) {} // Inject the Router
  ngOnInit(): void {
    this.userRoles = this.tokenService.userRoles;
    console.log('User Roles:', this.userRoles);
  }


  // Method to navigate to the profile route
  goToProfile() {
    console.log('root to ptofile');
    this.router.navigateByUrl('profiles'); // Navigate to the 'profiles' route
  }
  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }
  logout(): void {
    this.authService.logout();
  }
}
