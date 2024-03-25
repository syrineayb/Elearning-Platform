import {Component, Input} from '@angular/core';
import {navbarData, NavItem} from "./nav-data";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../../services/auth/authentication.service";

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent {
  @Input() navItems: NavItem[] = []; // Input property to receive navigation items from parent component
  //protected readonly navbarData = navbarData;
  constructor(private router: Router,public authService: AuthenticationService) {} // Inject the Router
  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }
}
