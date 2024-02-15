import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ResetPasswordComponent} from "../../reset-password/reset-password.component";

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrl: './login-admin.component.css'
})
export class LoginAdminComponent {
  adminLoginForm: FormGroup;

  errorMessage: string = '';


  infoMessage: string = '';
  isInfoMessage: boolean = false;

  constructor(
    private fb:FormBuilder,
    private authService: AuthService,
    private router: Router,
    private dialog: MatDialog,
  ) {
    this.adminLoginForm = this.fb.group({
      email:['', [Validators.email, Validators.pattern('\\S+')]],
      password: ['',[Validators.required, Validators.pattern('\\S+')]]
    });
  }

  login() :void {
    if (!this.adminLoginForm.valid){
      return
    }

    this.errorMessage = '';

    this.authService.login(this.adminLoginForm.value).subscribe({
      next: () => {
        const isUser = localStorage.getItem('isUser');
        if(isUser === 'true')
        {
          localStorage.removeItem('token');
          localStorage.removeItem('email');
          localStorage.removeItem('isUser');
          this.errorMessage = 'Email or password is incorrect';
          return;
        }
        this.router.navigate(['admin']).then(r=>r);
      },
      error: () => {
        this.errorMessage = 'Email or password is incorrect';
      }
    });
  }

  recoverPassword() {
    const  dialogRef: MatDialogRef<ResetPasswordComponent, any> = this.dialog.open(ResetPasswordComponent, {
    });

    dialogRef.componentInstance.emitter.subscribe(() =>{
      this.dialog.closeAll();
    });

    this.showInfo("Your password is recovered. Check your email!")
  }

  public showInfo(message: string): void {
    this.isInfoMessage = true;
    this.infoMessage = message;

  }
}
