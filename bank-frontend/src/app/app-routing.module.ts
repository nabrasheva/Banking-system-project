import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SignupComponent} from "./signup/signup.component";
import {WelcomePageComponent} from "./welcome-page/welcome-page.component";
import {LoginUserComponent} from "./login-user/login-user.component";
import {SafePageComponent} from "./safe-page/safe-page.component";

const routes: Routes = [{ path: 'login', component: LoginUserComponent},
  { path: 'signup', component: SignupComponent},
  { path: 'welcomePage', component: WelcomePageComponent},
  { path: 'safes', component: SafePageComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
