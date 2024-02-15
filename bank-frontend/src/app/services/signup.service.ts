import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BankUser} from "../model/bank-user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http:HttpClient) { }

  signup(user:BankUser): Observable<any> {
    return this.http.post(`http://localhost:8080/user/registration`,user);
  }
}
