import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../services/account.service";
import {Safe} from "../model/safe";

@Component({
  selector: 'app-create-safe',
  templateUrl: './create-safe.component.html',
  styleUrl: './create-safe.component.css'
})
export class CreateSafeComponent {

  safeForm!: FormGroup;
  public error: boolean = false;
  public errorMessage: string = '';
  @Output() emitter = new EventEmitter<any>();
  iban!:string;
  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any, private fb: FormBuilder, private accountService: AccountService) {
  }

  ngOnInit(){
    this.iban = this.dialogData.iban;
    this.safeForm = this.fb.group({
      name: ['', [Validators.required]],
      key:['', [Validators.required]],
      initialFunds: ['',[ Validators.required, Validators.pattern('/^\d+(\.\d{1,2})?$/')]]
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
  addSafe() {
    if(!this.safeForm.valid){
      return;
    }

    let newSafe: Safe = this.safeForm.getRawValue();
    newSafe.name = newSafe.name.trim();
    newSafe.key = newSafe.key.trim();

    this.accountService.createSafeForAccount(this.iban, newSafe).subscribe({
      next:value => {
      this.emitter.emit(newSafe);
        this.safeForm.reset();
      },
      error: err => {
        console.log(err.error.error);
        this.showError(err.error.error);
      }
    })
  }
}
