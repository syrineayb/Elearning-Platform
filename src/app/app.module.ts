import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotfoundComponent } from './pages/notfound/notfound.component';
import {AppInitializerService} from "./modules/app-common/services/app_init/app-initializer.service";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { CarouselComponent } from './components/carousel/carousel.component';
import {CarouselModule} from "ngx-bootstrap/carousel";
import { CategoriesComponent } from './components/categories/categories.component';
import { CourseComponent } from './components/course/course.component';
import { CourseCardComponent } from './components/course-card/course-card.component';
import { ContactusComponent } from './components/contactus/contactus.component';

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
    FooterComponent,
    HomeComponent,
    NavbarComponent,
    NotfoundComponent,
    CarouselComponent,
    CategoriesComponent,
    CourseComponent,
    CourseCardComponent,
    ContactusComponent
  ],
  imports: [

    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CarouselModule.forRoot()

  ],
  providers: [

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
