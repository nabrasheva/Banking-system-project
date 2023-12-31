import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import {MaterialUiModule} from "./material-ui/material-ui.module";
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { SafePageComponent } from './safe-page/safe-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    WelcomePageComponent,
    SafePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialUiModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
