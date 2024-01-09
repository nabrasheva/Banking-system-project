import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UpdateBankUser} from "../model/update-bank-user";
import {BankUser} from "../model/bank-user";

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

  getUserByEmail(email:string): Observable<any>{
    return this.http.get(`http://localhost:8080/user?email=${email}`, { headers: this.headers });
  }

  updateUserByEmail(email:string, user:UpdateBankUser): Observable<any>{
    return this.http.patch(`http://localhost:8080/user/${email}`,user, { headers: this.headers });

  }

  createAdmin(user:BankUser): Observable<any>{
    return  this.http.post(`http://localhost:8080/user/create-admin`,user, {headers:this.headers});
  }

  deleteUser(email:string): Observable<any>{
    return this.http.delete(`http://localhost:8080/user/${email}`, {headers: this.headers})
  }
}
