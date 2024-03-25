import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';

import { CandidateRoutingModule } from './candidate-routing.module';
import { AppCommonModule } from "../app-common/app-common.module";
import {FormsModule} from "@angular/forms";
import {MainComponent} from "./pages/main/main.component";

@NgModule({
  declarations: [
    MainComponent,
   // ProfilesComponent,

  ],
  exports: [

    // UpdateCandidateProfileComponent
  ],
  imports: [
    CommonModule,
    CandidateRoutingModule,
    AppCommonModule,
    FormsModule,
    NgOptimizedImage,
   // AppModule,
  ]
})
export class CandidateModule { }
