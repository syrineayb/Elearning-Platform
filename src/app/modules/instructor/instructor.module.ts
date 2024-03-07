import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstructorRoutingModule } from './instructor-routing.module';
import {MainComponent} from "./pages/main/main.component";
import {AppCommonModule} from "../app-common/app-common.module";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [
    MainComponent
  ],
  imports: [
    CommonModule,
    InstructorRoutingModule,
    AppCommonModule,
    RouterModule
  ]
})
export class InstructorModule { }
