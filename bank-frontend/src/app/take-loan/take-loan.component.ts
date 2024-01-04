import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Transaction} from "../model/transaction";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {AccountService} from "../services/account.service";
import {SendTransaction} from "../model/send-transaction";
import {Loan} from "../model/loan";

@Component({
  selector: 'app-take-loan',
  templateUrl: './take-loan.component.html',
  styleUrl: './take-loan.component.css'
})
export class TakeLoanComponent {
  loanForm!:FormGroup;
  public error: boolean = false;
  public errorMessage: string = '';
  @Output() emitter = new EventEmitter<any>();
  iban!:string;
  receivedTransactions!:Transaction[];
  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any, private fb: FormBuilder, private accountService: AccountService) {
  }

  ngOnInit(){
    this.iban = this.dialogData.iban;
    this.loanForm = this.fb.group({
      creditAmount: ['', Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]
    })
  }

  public showError(message:string): void {
    this.error = true;
    this.errorMessage = message;
  }

  public closeModal(): void {
    this.error = false;
    this.errorMessage = '';
  }

  takeLoan() {
    if(!this.loanForm.valid){
      return;
    }
    const newLoan: Loan = {
      userIban:this.iban,
      creditAmount:this.loanForm.get('creditAmount')?.value
    }

    this.accountService.takeLoan( newLoan).subscribe({
      next: value => {
        this.receivedTransactions = value;
        this.emitter.emit(this.receivedTransactions);
      },
      error: err => {
        console.log(err);
        this.showError(err);
      }
    })
  }
}
