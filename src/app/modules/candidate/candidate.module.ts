import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CandidateRoutingModule } from './candidate-routing.module';
import { MainComponent } from './pages/main/main.component';
import { AppCommonModule } from "../app-common/app-common.module";
import {ProfilsComponent} from "./pages/profils/profils.component";
import {candidateGuard} from "../app-common/services/guards/candidate/candidate.guard";
import {authGuard} from "../app-common/services/guards/auth/auth.guard";

@NgModule({
  declarations: [
    MainComponent,
    ProfilsComponent
  ],
  imports: [
    CommonModule,
    CandidateRoutingModule,
    AppCommonModule,

  ]
})
export class CandidateModule { }
