import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {AccountService} from "../services/account.service";
import {Transaction} from "../model/transaction";
import {SendTransaction} from "../model/send-transaction";
import {DebitCard} from "../model/debit-card";

@Component({
  selector: 'app-send-money',
  templateUrl: './send-money.component.html',
  styleUrl: './send-money.component.css'
})
export class SendMoneyComponent {
  sendingForm!:FormGroup;
  cardForm!:FormGroup;
  public error: boolean = false;
  public errorMessage: string = '';
  @Output() emitter = new EventEmitter<any>();
  iban!:string;
  receivedTransactions!:Transaction[];
  card!: DebitCard;
  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any, private fb: FormBuilder, private accountService: AccountService) {
  }

  ngOnInit(){
    this.iban = this.dialogData.iban;
    this.sendingForm = this.fb.group({
      sentAmount:  ['', [Validators.required, Validators.pattern('^(?!0+(\\.0{1,2})?$)\\d+(\\.\\d{1,2})?$')]],
      receiverIban: ['', [Validators.required, Validators.max(34)]],
      reason: ['', [Validators.required]],
    })

    this.cardForm = this.fb.group({
      number: [
        '',
        [Validators.required, Validators.pattern('^[0-9]{16}$')],
      ],
      expiryDate: ['', Validators.required],
      cvv: [
        '',
        [
          Validators.required,  Validators.pattern('^[0-9]{3,4}$')
        ],
      ],
    })

    this.accountService.getCreditCard(this.iban).subscribe({
      next: value => {
        this.card = value;
      },
      error: err => {
        console.log(err);
        this.showError(err.error.error);
      }
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
  sendMoney() {
    if(!this.sendingForm.valid){
      return;
    }

    if(!this.cardForm.valid){
      return;
    }

    const number:string = this.cardForm.get('number')?.value;
    const cvv = this.cardForm.get('cvv')?.value;

    if(number !== this.card.number || cvv !== this.card.cvv.toString() || this.cardForm.get('expiryDate')?.value !== this.card.expiryDate.toString())
    {
      this.showError('Invalid debit card!');
      return;
    }

    const newTransaction: SendTransaction = {
      sentAmount:this.sendingForm.get('sentAmount')?.value,
      receiverIban:this.sendingForm.get('receiverIban')?.value,
      reason:this.sendingForm.get('reason')?.value,
      creditPayment:false
    }

    this.accountService.sendMoney(this.iban, newTransaction).subscribe({
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
