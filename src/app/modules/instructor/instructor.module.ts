import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstructorRoutingModule } from './instructor-routing.module';
import {MainComponent} from "./pages/main/main.component";
import {AppCommonModule} from "../app-common/app-common.module";



@NgModule({
  declarations: [
    MainComponent
  ],
  imports: [
    CommonModule,
    InstructorRoutingModule,
    AppCommonModule
  ]
})
export class InstructorModule { }
