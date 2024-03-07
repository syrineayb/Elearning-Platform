import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MyuppercasePipe } from './services/pipes/myuppercase.pipe';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import {RouterLink} from "@angular/router";

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

  ],

})
export class AppCommonModule { }
