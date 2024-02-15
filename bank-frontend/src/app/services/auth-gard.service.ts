import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthGardService {

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
