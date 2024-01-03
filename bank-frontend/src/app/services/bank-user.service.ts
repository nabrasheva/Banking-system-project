import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BankUserService {

  private readonly token: string;
  private readonly headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token') || '';
    this.headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
  }

  getAccountByBankUser(email:string): Observable<any> {
    return this.http.get(`http://localhost:8080/user/account?email=${email}`, { headers: this.headers });
  }

}
