import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {AddTopicComponent} from "./pages/add-topic/add-topic.component";
import {ListTopicComponent} from "./pages/list-topic/list-topic.component";
import {ProfileComponent} from "../app-common/pages/profile/profile.component";
import {authGuard} from "../app-common/services/guards/auth/auth.guard";
import {adminGuard} from "../app-common/services/guards/admin/admin.guard";

const routes: Routes = [
  { path: '', component: MainComponent,
    canActivate: [authGuard, adminGuard], // Apply guards here if necessary

    children: [
      { path: 'addTopic', component:AddTopicComponent },
      { path: 'listTopic', component:ListTopicComponent },
     {path:'profile',component:ProfileComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
