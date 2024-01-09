import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BankUserService} from "../services/bank-user.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {BankUser} from "../model/bank-user";

@Component({
  selector: 'app-create-admin',
  templateUrl: './create-admin.component.html',
  styleUrl: './create-admin.component.css'
})
export class CreateAdminComponent {
  createForm!: FormGroup;
  public error: boolean = false;
  errorMessage: string = '';
  @Output() emitter = new EventEmitter<any>();

  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any,private fb:FormBuilder, private  userService:BankUserService ) {
  }
  ngOnInit(){
    this.createForm = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.email]],
      password: ['', [Validators.pattern('^(?=.*\\d)(?=.*[a-z]).{3,30}$')]],
      country: ['', [Validators.required]]
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

  addAdmin(){
    if (!this.createForm.valid){
      return;
    }

    let newAdmin: BankUser = this.createForm.getRawValue();
    newAdmin.username =newAdmin.username.trim();
    newAdmin.email =newAdmin.email.trim();

    this.userService.createAdmin(newAdmin).subscribe({
      next: value => {
        this.emitter.emit(newAdmin);
        this.createForm.reset();
      },
      error: err => {
        console.log(err);
        this.showError(this.errorMessage = err.error.error);
      }
    })
  }
}
