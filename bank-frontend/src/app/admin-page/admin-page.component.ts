import { Component } from '@angular/core';
import {Account} from "../model/account";
import {Transaction} from "../model/transaction";
import {Safe} from "../model/safe";

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.css'
})
export class AdminPageComponent {
  account!: Account;
  transactions! : Transaction[];
  safes!: Safe[];
  iban!:string;
  email!: string;



}
