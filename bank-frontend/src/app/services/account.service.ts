import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Safe} from "../model/safe";
import {Transaction} from "../model/transaction";
import {SendTransaction} from "../model/send-transaction";
import {Loan} from "../model/loan";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private readonly token: string;
  private readonly headers: HttpHeaders;
  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token') || '';
    this.headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
  }

  getSafesByAccount(iban:string): Observable<any>
  {
    return this.http.get(`http://localhost:8080/account/${iban}/safe`, { headers: this.headers });
  }

  getTransactionsByAccount(iban:string): Observable<any>
  {
    return this.http.get(`http://localhost:8080/account/${iban}/transaction`, { headers: this.headers });
  }

  deleteSafeByNameAndIban(iban:string, name:string): Observable<any>
  {
    return this.http.delete(`http://localhost:8080/account/${iban}/${name}`, { headers: this.headers });

  }

  createSafeForAccount(iban:string, safe:Safe): Observable<any>
  {
    return this.http.post(`http://localhost:8080/account/${iban}/safe`, safe, { headers: this.headers });
  }

  updateSafe(iban:string, name:string, funds:number): Observable<any>
  {
    return this.http.patch(`http://localhost:8080/account/${iban}/safe?name=${name}&funds=${funds.toString()}`, {}, { headers: this.headers });

  }

  sendMoney(iban:string, transaction:SendTransaction): Observable<any>
  {
    return this.http.post(`http://localhost:8080/account/${iban}/transaction`, transaction, { headers: this.headers });
  }

  takeLoan(loan:Loan): Observable<any>
  {
    return this.http.post(`http://localhost:8080/account/take-loan`, loan, { headers: this.headers });
  }

  returnLoan( loan:Loan): Observable<any>
  {
    return this.http.post(`http://localhost:8080/account/return-loan`, loan, { headers: this.headers });
  }

  getCreditCard(iban:string) : Observable<any>{
    return this.http.get(`http://localhost:8080/account/${iban}/card`, { headers: this.headers });
  }
  getAccountByIban(iban:string): Observable<any>{
    return this.http.get(`http://localhost:8080/account?iban=${iban}`, {headers:this.headers})
  }
}
