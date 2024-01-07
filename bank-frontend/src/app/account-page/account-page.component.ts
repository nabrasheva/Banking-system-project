import {Component} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {MatDialog} from "@angular/material/dialog";
import {AccountService} from "../services/account.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Account} from "../model/account";
import {DebitCard} from "../model/debit-card";

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrl: './account-page.component.css'
})
export class AccountPageComponent {
  iban!: string;
  account!: Account;
  card!: DebitCard;

  constructor(private accountService: AccountService, private route: ActivatedRoute, private router: Router, private dialog: MatDialog, private authService: AuthService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.iban = params['iban'];
    });

    this.accountService.getAccountByIban(this.iban).subscribe({
      next: value => {
        this.account = value;
      },
      error: err => {
        console.log(err)
      }
    })
    this.accountService.getCreditCard(this.iban).subscribe({
      next: value => {
        this.card = value;
      },
      error: err => {
        console.log(err);
      }
    })
  }

  logOut() {
    this.authService.logout();
  }
}
