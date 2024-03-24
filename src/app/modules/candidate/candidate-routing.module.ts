import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {authGuard} from "../app-common/services/guards/auth/auth.guard";
import {candidateGuard} from "../app-common/services/guards/candidate/candidate.guard";
import {ProfileComponent} from "../app-common/pages/profile/profile.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [authGuard, candidateGuard], // Apply guards here if necessary
    children: [
      {
        path: 'profile',
        component: ProfileComponent,
      },
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CandidateRoutingModule { }
