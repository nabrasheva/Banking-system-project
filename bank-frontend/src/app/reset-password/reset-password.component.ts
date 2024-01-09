import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
  createForm!: FormGroup;
  public error: boolean = false;
  errorMessage: string = '';
  @Output() emitter = new EventEmitter<any>();

  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any,private fb:FormBuilder, private  authService:AuthService ) {
  }
  ngOnInit(){
    this.createForm = this.fb.group({
      email: ['', [Validators.email]]
    });
  }

  recoverPassword(){
    if (!this.createForm.valid){
      return;
    }

    let email: string = this.createForm.get('email')?.value;
    email =email.trim();

    this.authService.recoverPassword(email).subscribe({
      next: value => {
        this.emitter.emit();
      },
      error: err => {
        console.log(err);
        this.showError(this.errorMessage = err.error.error);
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
