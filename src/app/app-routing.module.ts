import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {HomeComponent} from "./pages/home/home.component";
import {NotfoundComponent} from "./pages/notfound/notfound.component";
import {authGuard} from "./modules/app-common/services/guards/auth/auth.guard";
import {teacherGuard} from "./modules/app-common/services/guards/teacher/teacher.guard";
import {candidateGuard} from "./modules/app-common/services/guards/candidate/candidate.guard";

const routes: Routes = [
  {path:'',component:HomeComponent},

  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },

  {
    path: 'candidate',
    loadChildren: () => import('./modules/candidate/candidate.module').then(m => m.CandidateModule),
    canActivate: [authGuard, candidateGuard] // Add guards to 'candidate' route
  },
  {
    path: 'instructor',
    loadChildren: () => import('./modules/instructor/instructor.module').then(m => m.InstructorModule)},
  {
    path: 'admin',
    loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule),

  },
  {path:'**',component:NotfoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
