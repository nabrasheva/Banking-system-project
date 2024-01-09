import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrl: './login-admin.component.css'
})
export class LoginAdminComponent {
  adminLoginForm: FormGroup;

  errorMessage: string = '';

  constructor(
    private fb:FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.adminLoginForm = this.fb.group({
      email:['', Validators.email],
      password: ['',Validators.required]
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
        //localStorage.setItem('email', this.adminLoginForm.get('email')?.value);
        this.router.navigate(['admin']).then(r=>r);
      },
      error: () => {
        this.errorMessage = 'Email or password is incorrect';
      }
    });
  }
}
