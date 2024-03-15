import { Component, OnInit } from '@angular/core';
import { ProfilService } from '../../services/profil/profil.service';
import { ProfileResponse } from '../../../../models/profile-response';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../../services/auth/authentication.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent{

  constructor(private router: Router,public authService: AuthenticationService) {} // Inject the Router

  // Method to navigate to the profile route
  goToProfile() {
    console.log('root to ptofile');
    this.router.navigateByUrl('profiles'); // Navigate to the 'profiles' route
  }
}
