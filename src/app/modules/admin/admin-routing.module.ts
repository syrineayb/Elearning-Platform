import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {AddTopicComponent} from "./pages/add-topic/add-topic.component";
import {ListTopicComponent} from "./pages/list-topic/list-topic.component";

const routes: Routes = [
  { path: '', component: MainComponent,

    children: [
      { path: 'addTopic', component:AddTopicComponent },
      { path: 'listTopic', component:ListTopicComponent },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
