import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { MyuppercasePipe } from './services/pipes/myuppercase.pipe';
import {RouterLink, RouterModule} from "@angular/router";
import {BrowserModule} from "@angular/platform-browser";
import {HttpClientModule} from "@angular/common/http";
import {AuthenticationService} from "../../services/auth/authentication.service";
import {NotificationSettingsComponent} from "./pages/notification-settings/notification-settings.component";
import {SecurityProfileComponent} from "./pages/security-profile/security-profile.component";
import {UpdateProfileComponent} from "./pages/update-profile/update-profile.component";
import {ProfileNavbarComponent} from "./components/profile-navbar/profile-navbar.component";
import { HeaderComponent } from './components/header/header.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import {ProfileComponent} from "./pages/profile/profile.component";

@NgModule({
  declarations: [
    MyuppercasePipe,
    NotificationSettingsComponent,
    SecurityProfileComponent,
    UpdateProfileComponent,
    ProfileComponent,
    NotificationSettingsComponent,
    ProfileNavbarComponent,
    HeaderComponent,
    SideBarComponent


  ],
  exports: [
    MyuppercasePipe,
    HeaderComponent,
    SideBarComponent,
    ProfileNavbarComponent,
    ProfileComponent,


  ],
  imports: [

    CommonModule,
    RouterLink,
    RouterModule,
    HttpClientModule,
    NgOptimizedImage,


  ],
  providers: [

  ],

})
export class AppCommonModule { }
