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
import { UpdateSafeComponent } from './update-safe/update-safe.component';
import { TransactionsPageComponent } from './transactions-page/transactions-page.component';
import { SendMoneyComponent } from './send-money/send-money.component';
import { TakeLoanComponent } from './take-loan/take-loan.component';
import { ReturnLoanComponent } from './return-loan/return-loan.component';
import { AccountPageComponent } from './account-page/account-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { UpdateBankUserComponent } from './update-bank-user/update-bank-user.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminPageComponent } from './admin-page/admin-page.component';


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
    CreateSafeComponent,
    UpdateSafeComponent,
    TransactionsPageComponent,
    SendMoneyComponent,
    TakeLoanComponent,
    ReturnLoanComponent,
    AccountPageComponent,
    AdminPageComponent,
    ProfilePageComponent,
    UpdateBankUserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialUiModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
