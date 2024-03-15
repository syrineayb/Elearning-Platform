import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import { CandidateRoutingModule } from './candidate-routing.module';
import { MainComponent } from './pages/main/main.component';
import { AppCommonModule } from "../app-common/app-common.module";
//import {ProfilsComponent} from "./pages/profils/profils.component";
import {candidateGuard} from "../app-common/services/guards/candidate/candidate.guard";
import {authGuard} from "../app-common/services/guards/auth/auth.guard";
import { CandidateProfileComponent } from './pages/candidate-profile/candidate-profile.component';
import { UpdateProfileComponent } from './pages/update-profile/update-profile.component';
import { SecurityProfileComponent } from './pages/security-profile/security-profile.component';
import { NavigationBarComponent } from './pages/navigation-bar/navigation-bar.component';
import { NotificationSettingsComponent } from './pages/notification-settings/notification-settings.component';
import {ProfileNavbarComponent} from "../../components/profile-navbar/profile-navbar.component";
import {AppModule} from "../../app.module";
import {ProfileSidebarComponent} from "../../components/profile-sidebar/profile-sidebar.component";

@NgModule({
  declarations: [
    MainComponent,
   // ProfilesComponent,
    CandidateProfileComponent,
    UpdateProfileComponent,
    SecurityProfileComponent,
    NavigationBarComponent,
    NotificationSettingsComponent,
    ProfileNavbarComponent,
    ProfileSidebarComponent
   // UpdateCandidateProfileComponent
  ],
  exports: [
    CandidateProfileComponent,
    UpdateProfileComponent,
    SecurityProfileComponent,
    NotificationSettingsComponent,
    ProfileNavbarComponent,
    ProfileSidebarComponent

    // UpdateCandidateProfileComponent
  ],
  imports: [
    CommonModule,
    CandidateRoutingModule,
    AppCommonModule,
    NgOptimizedImage,
   // AppModule,
  ]
})
export class CandidateModule { }
