import { Component } from '@angular/core';
import {navbarData, NavItem} from "../../../app-common/components/side-bar/nav-data";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  sidebarActive = false;
  navItems: NavItem[] = [];
  constructor() {
    // Determine the user's role (instructor or candidate) and assign the appropriate navItems array
    const userRole = 'instructor'; // You need to replace this with the actual user's role
    this.navItems = navbarData[userRole];
  }
}
