import {Component, Input} from '@angular/core';
import {navbarData, NavItem} from "./nav-data";

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent {
  @Input() navItems: NavItem[] = []; // Input property to receive navigation items from parent component
  //protected readonly navbarData = navbarData;
}
