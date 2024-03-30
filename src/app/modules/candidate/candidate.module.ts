import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CandidateRoutingModule } from './candidate-routing.module';
import { AppCommonModule } from "../app-common/app-common.module";
import { FormsModule } from "@angular/forms";
import { MainComponent } from "./pages/main/main.component";
import { CandidateHomeComponent } from './pages/home/candidate-home/candidate-home.component';
import { TopicListComponent } from './pages/topic-list/topic-list.component';
import { CourseListComponent } from './pages/course-list/course-list.component';
import { FooterComponent } from "../app-common/components/footer/footer.component";

@NgModule({
    declarations: [
        MainComponent,
        CandidateHomeComponent,
        TopicListComponent,
        CourseListComponent,
        FooterComponent
    ],
    exports: [
        FooterComponent,
    ],
    imports: [
        CommonModule,
        CandidateRoutingModule,
        AppCommonModule,
        FormsModule,
    ]
})
export class CandidateModule { }
