import {Component, ViewEncapsulation} from '@angular/core';
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
  styleUrl: './admin-page.component.css',
  encapsulation: ViewEncapsulation.None
})
export class AdminPageComponent {
  emailForm: FormGroup;
  ibanForm: FormGroup;
  account!: Account;
  transactions!: Transaction[];
  safes!: Safe[];
  card!: DebitCard;
  user!: BankUser;
  iban!: string;
  email!: string;
  transactionCards: Transaction[] = [];
  safesCards: Safe[] = [];
  constructor(private dialog: MatDialog, private route: ActivatedRoute, private router: Router, private accountService: AccountService, private bankUserService: BankUserService, private fb: FormBuilder,private authService:AuthService) {
    this.emailForm = this.fb.group({
      email: ['', Validators.email]
    });
    this.ibanForm = this.fb.group({
      iban: ['', Validators.required]
    });
  }


  private getEmail(): string | undefined {
    return this.emailForm.get('email')?.value;
  }

  private getIban(): string | undefined {
    return this.ibanForm.get('iban')?.value;
  }

  getBankUser() {
    const email = this.getEmail();
    if (email) {
      this.bankUserService.getUserByEmail(email).subscribe({
        next:(data) => {
          this.user = data;
        },
        error:(error) =>{
          console.log(error)
        }
      });
    }
  }

  getAccount() {
    const email = this.getEmail();
    if (email) {
      this.bankUserService.getAccountByBankUser(email).subscribe({
        next:(data) => {
          this.account = data;
        },
        error:(error) =>{
          console.log(error)
        }
      });
    }
  }


  getTransactions() {
    const iban = this.getIban();
    if (iban) {
      this.accountService.getSafesByAccount(iban).subscribe({
        next: (data) =>{
          this.transactions = data;
          this.transactionCards = this.transactions.map(transaction => ({
            sentAmount:transaction.sentAmount,
            receiverIban: transaction.receiverIban,
            reason:transaction.reason,
            creditPayment:transaction.creditPayment,
            issueDate:transaction.issueDate
          }));
      },
        error:(error) =>{
          console.log(error)
        }
      });
    }
  }

  getSafes() {
    const iban = this.getIban();
    if (iban) {
      this.accountService.getSafesByAccount(iban).subscribe(
        {
          next: (data)  => {
            this.safes = data;
            console.log(data)
            this.safesCards = this.safes.map(safe => ({
              name: safe.name,
              key:safe.key,
              initialFunds:safe.initialFunds
            }));
            console.log('Mapped Safes:', this.safesCards);
          },
          error:(error) =>{
            console.log(error)
          }
        });
    }
  }

  getBankCard() {
    const iban = this.getIban();
      if (iban) {
        this.accountService.getCreditCard(iban).subscribe({
          next:(data) => {
            this.card = data;
          },
          error:(error) =>{
            console.log(error)
          }
        });
      }
    }


  getInfoByEmail() {
     this.getBankUser();
     this.getAccount();
  }

getInfoByIban(){

    this.getBankCard();
    this.getSafes();
    this.getTransactions()

}


  logOut() {
    this.authService.logout();
  }
}
