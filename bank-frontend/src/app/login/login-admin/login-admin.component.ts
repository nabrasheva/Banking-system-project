import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

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
    // private authService: AuthService,
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
  }
}
