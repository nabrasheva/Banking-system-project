import { Injectable } from '@angular/core';
import {Observable, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoginRequest} from "../model/login-request";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private router: Router,
    private http:HttpClient
  ) {}

  login(loginCredentials: LoginRequest): Observable<any>{
    return this.http.post("http://localhost:8080/user/login", loginCredentials)
      .pipe(
        tap((response: any) => {
          const token = response.token;
          localStorage.setItem('token', token);
          const email = response.email;
          localStorage.setItem('email', email);
        })
      );
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    const email = localStorage.getItem('email');

    if(!token){
      return false;
    }
    if(!email){
      return false;
    }
    return true;
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    this.router.navigate(['/login']).then(r=>r);
  }

}
