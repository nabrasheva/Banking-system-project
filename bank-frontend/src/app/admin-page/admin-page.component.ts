import {Component, EventEmitter, Output, ViewEncapsulation} from '@angular/core';
import {Account} from "../model/account";
import {Transaction} from "../model/transaction";
import {Safe} from "../model/safe";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../services/account.service";
import {BankUserService} from "../services/bank-user.service";
import {DebitCard} from "../model/debit-card";
import {BankUser} from "../model/bank-user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {CreateAdminComponent} from "../create-admin/create-admin.component";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.css',
  encapsulation: ViewEncapsulation.None
})
export class AdminPageComponent {
  @Output() emitter = new EventEmitter<any>();
  emailForm: FormGroup;
  ibanForm: FormGroup;
  account!: Account | undefined;
  transactions!: Transaction[] | undefined;
  safes!: Safe[] | undefined;
  card!: DebitCard | undefined;
  user!: BankUser | undefined;
  iban!: string;
  email!: string;
  transactionCards: Transaction[] = [];
  safesCards: Safe[] = [];

  infoMessage: string = '';
  isInfoMessage: boolean = false;

  constructor(private dialog: MatDialog, private route: ActivatedRoute, private router: Router, private accountService: AccountService, private bankUserService: BankUserService, private fb: FormBuilder, private authService: AuthService) {
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
        next: (data) => {
          this.user = data;
        },
        error: err => {
          this.showInfo(err.error.error);
        }
      });
    }
  }

  getAccount() {
    const email = this.getEmail();
    if (email) {
      this.bankUserService.getAccountByBankUser(email).subscribe({
        next: (data) => {
          this.account = data;
        },
        error: err => {
          this.showInfo(err.error.error);
        }
      });
    }
  }


  getTransactions() {
    const iban = this.getIban();
    if (iban) {
      this.accountService.getTransactionsByAccount(iban).subscribe({
        next: (data) => {
          this.transactions = data;
          console.log(data)
          if (this.transactions != undefined) {
            this.transactionCards = this.transactions
              .map(transaction => ({
                sentAmount: transaction.sentAmount,
                receiverIban: transaction.receiverIban,
                reason: transaction.reason,
                creditPayment: transaction.creditPayment,
                issueDate: transaction.issueDate as number
              }));
          }

          console.log("Tran", this.transactionCards)
        },
        error: err => {
          this.showInfo(err.error.error);
        }
      });
    }
  }

  getSafes() {
    const iban = this.getIban();
    if (iban) {
      this.accountService.getSafesByAccount(iban).subscribe(
        {
          next: (data) => {
            this.safes = data;
            console.log(data)
            if (this.safes != undefined){
              this.safesCards = this.safes.map(safe => ({
                name: safe.name,
                key: safe.key,
                initialFunds: safe.initialFunds
              }));
            }
            console.log('Mapped Safes:', this.safesCards);
          },
          error: err => {
            this.showInfo(err.error.error);
          }
        });
    }
  }

  getBankCard() {
    const iban = this.getIban();
    if (iban) {
      this.accountService.getCreditCard(iban).subscribe({
        next: (data) => {
          this.card = data;
        },
        error: err => {
          this.showInfo(err.error.error);
        }
      });
    }
  }


  getInfoByEmail() {
    this.getBankUser();
    this.getAccount();
  }

  getInfoByIban() {
    this.getBankCard();
    this.getSafes();
    this.getTransactions();

  }

  createAdmin() {
    const dialogRef: MatDialogRef<CreateAdminComponent, any> = this.dialog.open(CreateAdminComponent, {
      data: {
        username: this.user?.username,
        email: this.user?.email,
        password: this.user?.password,
        country: this.user?.country
      }
    });

    dialogRef.componentInstance.emitter.subscribe(() => {
      this.dialog.closeAll();
    });
    this.showInfo("Admin is created!");


  }

  deleteUser() {
    const email = this.getEmail();
    if (email != undefined) {
      this.bankUserService.deleteUser(email).subscribe({
        next: value => {
          this.showInfo("User is deleted!")
          this.user = undefined;
          this.card = undefined;
          this.account = undefined;
          this.safesCards = [];
          this.transactionCards = [];
          this.emailForm.get('email')?.reset('');
          this.ibanForm.get('iban')?.reset('');
        },
        error: err => {
          this.showInfo(err.error.error);
        }
      });
    }
  }

  logOut() {
    this.authService.logoutAdmin();
  }

  public showInfo(message: string, duration:number = 10000): void {
    this.isInfoMessage = true;
    this.infoMessage = message;

    setTimeout(()=>{
      this.isInfoMessage = false
    },duration);
  }
}
