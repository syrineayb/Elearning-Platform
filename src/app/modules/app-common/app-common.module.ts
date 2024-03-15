import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyuppercasePipe } from './services/pipes/myuppercase.pipe';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import {RouterLink, RouterModule} from "@angular/router";
import {BrowserModule} from "@angular/platform-browser";
import {HttpClientModule} from "@angular/common/http";
import {AuthenticationService} from "../../services/auth/authentication.service";

@NgModule({
  declarations: [
    MyuppercasePipe,
    SidebarComponent,



  ],
  exports: [
    MyuppercasePipe,
    SidebarComponent,



  ],
  imports: [

    CommonModule,
    RouterLink,
    RouterModule,
    HttpClientModule,

  ],
  providers: [

  ],

})
export class AppCommonModule { }
