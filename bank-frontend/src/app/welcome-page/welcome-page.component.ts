import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {BankUserService} from "../services/bank-user.service";
import {Account} from "../model/account";
@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrl: './welcome-page.component.css'
})
export class WelcomePageComponent {
  user_email!:string;
  account!: Account;
  constructor(private router: Router, private userService: BankUserService) {}

  ngOnInit(){
    this.user_email = localStorage.getItem('email') || '';
    this.userService.getAccountByBankUser(this.user_email).subscribe({
      next: (data) => {
       this.account = data;
      },
      error: (error) => {
        console.error(error);
      }
    })

}

  navigateToSafesPage() {
    this.router.navigate(['safes', {iban: this.account.iban}]).then(r => r);
  }

  navigateToTransactionsPage() {
    this.router.navigate(['transactions', {iban: this.account.iban}]).then(r => r);

  }

  navigateToAccountPage()
  {
    this.router.navigate(['account', {iban: this.account.iban, account: this.account}]).then(r => r);

  }
}
