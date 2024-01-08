import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {SignupService} from "../services/signup.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  signupForm: FormGroup;

  errorMessage: string = '';
  passwordMismatch: boolean = false;

  infoMessage: string = '';
  isInfoMessage: boolean = false;

  constructor(
    private fb: FormBuilder,
   private signupService: SignupService,
    private router: Router
  ) {
    this.signupForm = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.email]],
      password: ['', [Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$')]],
      confirmPassword: ['', [Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$')]],
      country: ['', [Validators.required]]
    })
  }

  signup(){
    if(!this.signupForm.valid){
      return;
    }

    this.errorMessage = '';

    const password = this.signupForm.get('password')?.value;
    const confirmPassword = this.signupForm.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      this.passwordMismatch = true;
      return;
    }

    this.signupService.signup(this.signupForm.value).subscribe({
      next: () => {
        this.router.navigate(['login']);
      },
      error: () => {
        this.errorMessage = 'An error occurred during signup';
      }
    });
  }

  // public showInfo(message:string): void {
  //   this.isInfoMessage = true;
  //   this.infoMessage = message;
  // }
  //
  // public closeModal(): void {
  //   this.isInfoMessage = false;
  //   this.infoMessage = '';
  // }
}
