import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import {HttpClientModule} from "@angular/common/http";
import {RouterLink, RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {AppCommonModule} from "../app-common/app-common.module";

import {MainComponent} from "./pages/main/main.component";


@NgModule({
  declarations: [
    MainComponent,

  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    AppCommonModule,
    FormsModule,
    RouterLink,
    RouterModule,
    HttpClientModule,
  ]
})
export class AdminModule { }
