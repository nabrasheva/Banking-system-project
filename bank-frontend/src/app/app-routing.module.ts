import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {WelcomePageComponent} from "./welcome-page/welcome-page.component";

const routes: Routes = [{ path: 'login', component: LoginComponent},
  { path: 'signup', component: SignupComponent},
  { path: 'welcomePage', component: WelcomePageComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
