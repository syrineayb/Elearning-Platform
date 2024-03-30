import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {authGuard} from "../app-common/services/guards/auth/auth.guard";
import {candidateGuard} from "../app-common/services/guards/candidate/candidate.guard";
import {ProfileComponent} from "../app-common/pages/profile/profile.component";
import {MainComponent} from "./pages/main/main.component";
import {CandidateHomeComponent} from "./pages/home/candidate-home/candidate-home.component";
import {TopicListComponent} from "./pages/topic-list/topic-list.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [authGuard, candidateGuard], // Apply guards here if necessary
    children: [
      {
        path: 'profile', component: ProfileComponent,
      },
      {
        path: '', component: CandidateHomeComponent,
      },{
        path: 'topics', component: TopicListComponent,
      },
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CandidateRoutingModule { }
