import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BankUserService} from "../services/bank-user.service";
import {UpdateBankUser} from "../model/update-bank-user";

@Component({
  selector: 'app-update-bank-user',
  templateUrl: './update-bank-user.component.html',
  styleUrl: './update-bank-user.component.css'
})
export class UpdateBankUserComponent {
  @Output() emitter = new EventEmitter<any>();
  userForm!:FormGroup;
  public error: boolean = false;
  public errorMessage: string = '';
  username!:string;
  password!:string;
  user_email!: string;
  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any,private fb: FormBuilder, private userService:BankUserService) {
  }

  ngOnInit()
  {
    this.username = this.dialogData.username;
    this.password = this.dialogData.password;
    this.user_email = this.dialogData.user_email;
    this.userForm = this.fb.group({
      username: [''],
      password: ['']
    });
  }
  updateUser() {
    const formUsername = this.userForm.get('username')?.value;
    const formPassword = this.userForm.get('password')?.value;
    let userCredentials: UpdateBankUser={
      username: '',
      password: ''
    };
    if(formUsername !== '')
    {
      userCredentials.username = formUsername;
    }
    if(formPassword !== '')
    {
      userCredentials.password = formPassword;
    }

    this.userService.updateUserByEmail(this.user_email, userCredentials).subscribe({
      next: value => {
        this.emitter.emit(userCredentials);
      },
      error: err => {
        console.log(err);
        this.showError(err);
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
}
