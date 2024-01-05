import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SafeService {

  private readonly token: string;
  private readonly headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.token = localStorage.getItem('token') || '';
    this.headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
  }

  getAllSafes(): Observable<any> {
    return this.http.get(`http://localhost:8080/safe`, {headers: this.headers});
  }

  getSafeByName(name: string): Observable<any> {
    return this.http.get(`http://localhost:8080/safe?name=${name}`, {headers: this.headers});
  }
}
