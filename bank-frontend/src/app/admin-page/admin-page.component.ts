import {Component} from '@angular/core';
import {Account} from "../model/account";
import {Transaction} from "../model/transaction";
import {Safe} from "../model/safe";
import {MatDialog} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../services/account.service";
import {BankUserService} from "../services/bank-user.service";
import {DebitCard} from "../model/debit-card";
import {BankUser} from "../model/bank-user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.css'
})
export class AdminPageComponent {
  adminForm: FormGroup;
  account!: Account;
  transactions!: Transaction[];
  safes!: Safe[];
  card!: DebitCard;
  user!: BankUser;
  iban!: string;
  email!: string;

  constructor(private dialog: MatDialog, private route: ActivatedRoute, private router: Router, private accountService: AccountService, private bankUserService: BankUserService, private fb: FormBuilder,private authService:AuthService) {
    this.adminForm = this.fb.group({
      email: ['', Validators.email],
      iban: ['', Validators.required]
    });
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
    if (this.email) {
      this.bankUserService.getAccountByBankUser(this.email).subscribe(data => {
        this.account = data;
      });
    }
  }

  getBankUser() {
    const emailControl = this.adminForm.get('email');

    if (emailControl && emailControl.valid) {
      const email = emailControl.value;

      if (email) {
        this.bankUserService.getUserByEmail(email).subscribe(data => {
          console.log('Data received:', data);
          this.user = data;
        });
      }
    }
  }

  logOut() {
    this.authService.logout();
  }
}
