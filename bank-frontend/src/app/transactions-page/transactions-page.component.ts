import {Component} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Transaction} from "../model/transaction";
import {TransactionRow} from "../model/transaction-row";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../services/account.service";
import {SendMoneyComponent} from "../send-money/send-money.component";
import {TakeLoanComponent} from "../take-loan/take-loan.component";
import {ReturnLoanComponent} from "../return-loan/return-loan.component";
import {AuthService} from "../services/auth.service";


@Component({
  selector: 'app-transactions-page',
  templateUrl: './transactions-page.component.html',
  styleUrl: './transactions-page.component.css'
})
export class TransactionsPageComponent {
  displayedColumns: string[] = ['sentAmount', 'receiverIban', 'reason','issueDate'];
  transactions!: Transaction[];
  dataSource: MatTableDataSource<TransactionRow> = new MatTableDataSource();
  iban!: string;

  constructor(private dialog: MatDialog, private route: ActivatedRoute, private router: Router, private accountService: AccountService, private authService: AuthService) {
  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.iban = params['iban'];
    });

    this.accountService.getTransactionsByAccount(this.iban).subscribe({
      next: (data) => {
        this.transactions = data;
        this.transactions.forEach(transaction => {
          if (transaction.issueDate !== undefined) {
            this.dataSource.data.push({
              sentAmount: transaction.sentAmount,
              receiverIban: transaction.receiverIban,
              reason: transaction.reason,
              issueDate: new Date(transaction.issueDate)
            });
          }
        })
        this.dataSource._updateChangeSubscription();
      },
      error: (error) => {
        console.error(error);
      }
    })

  }

  sendMoney() {
    const dialogRef: MatDialogRef<SendMoneyComponent, any> = this.dialog.open(SendMoneyComponent, {
      data: {iban: this.iban}
    });

    dialogRef.componentInstance.emitter.subscribe((transaction: Transaction) => {
      console.log(transaction)

      if (transaction.issueDate !== undefined) {
        this.dataSource.data.push({
          sentAmount: transaction.sentAmount,
          receiverIban: 'none',
          reason: transaction.reason,
          issueDate: new Date(transaction.issueDate)
        });
      }
      this.dataSource._updateChangeSubscription();
      this.dialog.closeAll();
    });
  }

  takeLoan() {
    const dialogRef: MatDialogRef<TakeLoanComponent, any> = this.dialog.open(TakeLoanComponent, {
      data: {iban: this.iban}
    });

    dialogRef.componentInstance.emitter.subscribe((transaction: Transaction) => {
      console.log(transaction)

      if (transaction.issueDate !== undefined) {
        this.dataSource.data.push({
          sentAmount: transaction.sentAmount,
          receiverIban: 'none',
          reason: transaction.reason,
          issueDate: new Date(transaction.issueDate)
        });
      }
      this.dataSource._updateChangeSubscription();
      this.dialog.closeAll();
    });
  }

  returnLoan() {
    const dialogRef: MatDialogRef<ReturnLoanComponent, any> = this.dialog.open(ReturnLoanComponent, {
      data: {iban: this.iban}
    });

    dialogRef.componentInstance.emitter.subscribe((transaction: Transaction) => {
      console.log(transaction)

      if (transaction.issueDate !== undefined) {
        this.dataSource.data.push({
          sentAmount: transaction.sentAmount,
          receiverIban: 'none',
          reason: transaction.reason,
          issueDate: new Date(transaction.issueDate)
        });
      }
      this.dataSource._updateChangeSubscription();
      this.dialog.closeAll();
    });
  }

  logOut() {
    this.authService.logout();
  }
}
