import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstructorRoutingModule } from './instructor-routing.module';
import {MainComponent} from "./pages/main/main.component";
import {AppCommonModule} from "../app-common/app-common.module";
import {RouterModule} from "@angular/router";
import { InstructorProfileComponent } from './pages/instructor-profile/instructor-profile.component';
import {CandidateModule} from "../candidate/candidate.module";



@NgModule({
  declarations: [
    MainComponent,
    InstructorProfileComponent
  ],
  exports: [
    InstructorProfileComponent
  ],
  imports: [
    CommonModule,
    InstructorRoutingModule,
    AppCommonModule,
    RouterModule,
    CandidateModule
  ]
})
export class InstructorModule { }
