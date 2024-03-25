import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../../services/auth/authentication.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements  OnInit {
  @Input() sidebarActive = false;
  @Output() toggleSidebar = new EventEmitter();
  constructor(private router: Router,public authService: AuthenticationService) {} // Inject the Router

  ngOnInit(): void {
  }
  logout(): void {
    this.authService.logout();
  }

}
