import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstructorRoutingModule } from './instructor-routing.module';
import {AppCommonModule} from "../app-common/app-common.module";
import {RouterModule} from "@angular/router";
import {CandidateModule} from "../candidate/candidate.module";
import {FormsModule} from "@angular/forms";
import {MainComponent} from "./pages/main/main.component";



@NgModule({
  declarations: [
    MainComponent,

  ],
  exports: [

  ],
  imports: [
    CommonModule,
    InstructorRoutingModule,
    AppCommonModule,
    FormsModule,


  ]
})
export class InstructorModule { }
