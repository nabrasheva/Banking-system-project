import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignupComponent} from "./signup/signup.component";
import {WelcomePageComponent} from "./welcome-page/welcome-page.component";
import {LoginUserComponent} from "./login/login-user/login-user.component";
import {SafePageComponent} from "./safe-page/safe-page.component";
import {TransactionsPageComponent} from "./transactions-page/transactions-page.component";
import {AccountPageComponent} from "./account-page/account-page.component";
import {LoginAdminComponent} from "./login/login-admin/login-admin.component";

const routes: Routes = [{path: 'login', component: LoginUserComponent},
  {path: "loginAdmin", component: LoginAdminComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'welcomePage', component: WelcomePageComponent},
  {path: 'safes', component: SafePageComponent},
  { path: 'transactions', component: TransactionsPageComponent },
  { path: 'account', component: AccountPageComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
