import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthChekerService {

  constructor(private authService: AuthService,
              private router : Router
  ) {}

  canActivate(): boolean {
    if(this.authService.isAuthenticated()){
      return true;
    } else{
      this.router.navigate(['/login']);
      return false;
    }
  }
}
