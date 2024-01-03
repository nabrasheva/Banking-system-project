import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import {MaterialUiModule} from "./material-ui/material-ui.module";
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { SafePageComponent } from './safe-page/safe-page.component';
import { LoginUserComponent } from './login/login-user/login-user.component';
import { LoginAdminComponent } from './login/login-admin/login-admin.component';
import {ReactiveFormsModule} from "@angular/forms";
import { InfoPageComponent } from './info-page/info-page.component';
import { EnterSafeKeyComponent } from './enter-safe-key/enter-safe-key.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { ShowSafeComponent } from './show-safe/show-safe.component';
import { CreateSafeComponent } from './create-safe/create-safe.component';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    WelcomePageComponent,
    SafePageComponent,
    LoginUserComponent,
    LoginAdminComponent,
    InfoPageComponent,
    EnterSafeKeyComponent,
    ErrorPageComponent,
    ShowSafeComponent,
    CreateSafeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialUiModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
