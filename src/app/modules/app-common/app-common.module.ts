import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { MyuppercasePipe } from './services/pipes/myuppercase.pipe';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import {RouterLink, RouterModule} from "@angular/router";
import {BrowserModule} from "@angular/platform-browser";
import {HttpClientModule} from "@angular/common/http";
import {AuthenticationService} from "../../services/auth/authentication.service";
import {CandidateProfileComponent} from "./pages/candidate-profile/candidate-profile.component";
import {NotificationSettingsComponent} from "./pages/notification-settings/notification-settings.component";
import {SecurityProfileComponent} from "./pages/security-profile/security-profile.component";
import {UpdateProfileComponent} from "./pages/update-profile/update-profile.component";
import {ProfileNavbarComponent} from "./components/profile-navbar/profile-navbar.component";
import {ProfileSidebarComponent} from "./components/profile-sidebar/profile-sidebar.component";

@NgModule({
  declarations: [
    MyuppercasePipe,
    SidebarComponent,
CandidateProfileComponent,
    NotificationSettingsComponent,
    SecurityProfileComponent,
    UpdateProfileComponent,
    CandidateProfileComponent,
    NotificationSettingsComponent,
    ProfileNavbarComponent,
    ProfileSidebarComponent


  ],
  exports: [
    MyuppercasePipe,
    SidebarComponent,



  ],
  imports: [

    CommonModule,
    RouterLink,
    RouterModule,
    HttpClientModule,
    NgOptimizedImage

  ],
  providers: [

  ],

})
export class AppCommonModule { }
