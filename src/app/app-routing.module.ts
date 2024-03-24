import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { NotfoundComponent } from './pages/notfound/notfound.component';
import { authGuard } from './modules/app-common/services/guards/auth/auth.guard';
import { candidateGuard } from './modules/app-common/services/guards/candidate/candidate.guard';
import {instructorGuard} from "./modules/app-common/services/guards/instructor/instructor.guard";
import {adminGuard} from "./modules/app-common/services/guards/admin/admin.guard";
import {AppCommonModule} from "./modules/app-common/app-common.module";
import {ProfileComponent} from "./modules/app-common/pages/profile/profile.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },


  {
    path: 'candidate',
    loadChildren: () => import('./modules/candidate/candidate.module').then(m => m.CandidateModule),
    canActivate: [authGuard, candidateGuard] // Apply guards to the candidate route
  },
  {
    path: 'instructor',
    loadChildren: () => import('./modules/instructor/instructor.module').then(m => m.InstructorModule),
    canActivate: [authGuard, instructorGuard]
  },
  {
    path: 'admin',
    loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule),
    canActivate: [authGuard, adminGuard]
  },

  { path: '**', component: NotfoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
