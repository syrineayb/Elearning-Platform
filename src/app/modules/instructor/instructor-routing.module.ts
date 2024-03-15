import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "../candidate/pages/main/main.component";
//import {ProfilsComponent} from "../candidate/pages/profils/profils.component";

const routes: Routes = [
  {path: '', component: MainComponent,},
  //{path:'editprofil',component:ProfilsComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstructorRoutingModule { }
