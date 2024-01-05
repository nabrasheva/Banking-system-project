import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {FormBuilder} from "@angular/forms";
import {BankUserService} from "../services/bank-user.service";

@Component({
  selector: 'app-update-bank-user',
  templateUrl: './update-bank-user.component.html',
  styleUrl: './update-bank-user.component.css'
})
export class UpdateBankUserComponent {
  @Output() emitter = new EventEmitter<any>();
  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any,private fb: FormBuilder, private userService:BankUserService) {
  }


}
