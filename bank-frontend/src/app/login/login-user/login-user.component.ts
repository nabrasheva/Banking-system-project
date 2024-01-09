import {Component, ViewEncapsulation} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ResetPasswordComponent} from "../../reset-password/reset-password.component";

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrl: './login-user.component.css',
  encapsulation: ViewEncapsulation.None,
})
export class LoginUserComponent {
  loginForm: FormGroup;

  errorMessage: string = '';

  infoMessage: string = '';
  isInfoMessage: boolean = false;

  constructor(
    private fb: FormBuilder,
   private authService: AuthService,
    private router: Router,
    private dialog: MatDialog,
  ) {
    this.loginForm = this.fb.group({
      email: ['', Validators.email],
      password: ['', ]
    });
  }
  // Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$')
  login(){
    if(!this.loginForm.valid){
      return;
    }

    this.errorMessage = '';

    this.authService.login(this.loginForm.value).subscribe({
      next: () => {
        const isUser = localStorage.getItem('isUser');
        if(isUser === 'false')
        {
          localStorage.removeItem('token');
          localStorage.removeItem('email');
          localStorage.removeItem('isUser');
          this.errorMessage = 'Email or password is incorrect';
          return;
        }
        this.router.navigate(['welcomePage']).then(r=>r);
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
