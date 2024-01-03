import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrl: './login-user.component.css'
})
export class LoginUserComponent {
  loginForm: FormGroup;

  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
   // private authService: AuthService,
    private router: Router
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

    // this.authService.login(this.loginForm.value).subscribe({
    //   next: () => {
    //     localStorage.setItem('email', this.loginForm.get('email')?.value);
    //     this.router.navigate(['welcomePage']).then(r=>r);
    //   },
    //   error: () => {
    //     this.errorMessage = 'Email or password is incorrect';
    //   }
    // });
  }
}
