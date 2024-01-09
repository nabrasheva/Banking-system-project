import {Component, EventEmitter, Inject, Input, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {AccountService} from "../services/account.service";

@Component({
  selector: 'app-update-safe',
  templateUrl: './update-safe.component.html',
  styleUrl: './update-safe.component.css'
})
export class UpdateSafeComponent {
  iban!:string;
  name!:string;
  @Output() emitter = new EventEmitter<any>();

  moneyForm!:FormGroup;
  public error: boolean = false;
  public errorMessage: string = '';


  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any,private fb: FormBuilder, private accountService:AccountService) {}


  ngOnInit(){
    this.iban = this.dialogData.iban;
    this.name = this.dialogData.name;
    this.moneyForm = this.fb.group({
      money: ['', [Validators.required, Validators.pattern('/^\d+(\.\d{1,2})?$/')]]
    });
  }

  public closeModal(): void {
    this.error = false;
    this.errorMessage = '';
  }

  public showError(message:string): void {
    this.error = true;
    this.errorMessage = message;
  }

  updateSafeMoney() {
    if(!this.moneyForm.valid){
      return;
    }
    const money = this.moneyForm.get('money')?.value;
    this.accountService.updateSafe(this.iban, this.name, money).subscribe({
      next: value => {
        this.emitter.emit(this.name);
      },
      error: err => {
        console.log(err);
        this.showError(err.error.error);
      }
    });

  }
}
