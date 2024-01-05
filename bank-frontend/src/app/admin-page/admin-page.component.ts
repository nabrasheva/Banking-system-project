import {Component} from '@angular/core';
import {Account} from "../model/account";
import {Transaction} from "../model/transaction";
import {Safe} from "../model/safe";
import {MatDialog} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../services/account.service";
import {BankUserService} from "../services/bank-user.service";
import {DebitCard} from "../model/debit-card";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.css'
})
export class AdminPageComponent {
  account!: Account;
  transactions!: Transaction[];
  safes!: Safe[];
  card!: DebitCard;
  iban!: string;
  email!: string;

  constructor(private dialog: MatDialog, private route: ActivatedRoute, private router: Router, private accountService: AccountService, private bankUserService: BankUserService) {
  }

  getTransactions() {
    if (this.iban) {
      this.accountService.getSafesByAccount(this.iban).subscribe(data => {
        this.transactions = data;
      });
    }
  }

  getSafes() {
    if (this.iban) {
      this.accountService.getSafesByAccount(this.iban).subscribe(data => {
        this.safes = data;
      });
    }
  }

  getBankCard() {
    if (this.iban) {
      this.accountService.getCreditCard(this.iban).subscribe(data => {
        this.card = data;
      });
    }
  }

getAccount() {
    if (this.email){
      this.bankUserService
    }
}
}
