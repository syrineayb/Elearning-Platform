import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { FooterComponent } from './modules/app-common/components/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotfoundComponent } from './pages/notfound/notfound.component';
import {AppInitializerService} from "./modules/app-common/services/app_init/app-initializer.service";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { CarouselComponent } from './components/carousel/carousel.component';
import {CarouselModule} from "ngx-bootstrap/carousel";
import { CategoriesComponent } from './components/category/categories.component';
import { CourseComponent } from './components/course/course.component';
import { CourseCardComponent } from './components/course-card/course-card.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import {RouterModule} from "@angular/router";
import {AuthenticationService} from "./services/auth/authentication.service";
import {CandidateModule} from "./modules/candidate/candidate.module";
import {InstructorModule} from "./modules/instructor/instructor.module";
import { CategoryCardComponent } from './components/category-card/category-card.component';
import { LessonCardComponent } from './components/lesson-card/lesson-card.component';
import { LessonComponent } from './components/lesson/lesson.component';
import {AppCommonModule} from "./modules/app-common/app-common.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";


export function createCustomTranslationLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, 'i18n/', '.json');
}
export function initializeApp(appInitializer: AppInitializerService) {
  return () => appInitializer.initializeApp();
}
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NavbarComponent,
    NotfoundComponent,
    CarouselComponent,
    CategoriesComponent,
    CourseComponent,
    CourseCardComponent,
    ContactusComponent,
    CategoryCardComponent,
    LessonCardComponent,
    LessonComponent,

    //ProfileSidebarComponent,
    // ProfileNavbarComponent


  ],
  imports: [

    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CarouselModule.forRoot(),
    AppCommonModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({timeOut: 3000}),
    CandidateModule,


    // NgbToast,


  ],
  providers: [AuthenticationService],
  /*
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    }
   */
    exports: [
        // ProfileSidebarComponent
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
