import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Safe} from "../model/safe";

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
}
