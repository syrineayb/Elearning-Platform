import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {ProfileComponent} from "../app-common/pages/profile/profile.component";
import {authGuard} from "../app-common/services/guards/auth/auth.guard";
import {instructorGuard} from "../app-common/services/guards/instructor/instructor.guard";

const routes: Routes = [
  {path: '', component: MainComponent,
    canActivate: [authGuard, instructorGuard], // Apply guards here if necessary
    children: [
      {
        path: 'profile', component: ProfileComponent,
      },
    ]
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstructorRoutingModule { }
