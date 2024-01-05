import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BankUserService} from "../services/bank-user.service";
import {AuthService} from "../services/auth.service";
import {BankUser} from "../model/bank-user";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {SendMoneyComponent} from "../send-money/send-money.component";
import {UpdateBankUserComponent} from "../update-bank-user/update-bank-user.component";
import {Transaction} from "../model/transaction";

@Component({
    selector: 'app-profile-page',
    templateUrl: './profile-page.component.html',
    styleUrl: './profile-page.component.css'
})
export class ProfilePageComponent {

    user_email!: string;
    user!: BankUser;

    constructor(private dialog: MatDialog, private router: Router, private route: ActivatedRoute, private userService: BankUserService, private authService: AuthService) {
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
            this.user_email = params['user_email'];
        });

      this.userService.getBankUserByEmail(this.user_email).subscribe({
        next: value => {
          this.user = value;
        },
        error: err => {
          console.log(err);
        }
      })
    }

    logOut() {
        this.authService.logout();
    }

    updateUser() {
        const dialogRef: MatDialogRef<UpdateBankUserComponent, any> = this.dialog.open(UpdateBankUserComponent, {
            data: { username: this.user.username, password: this.user.password }
        });

        dialogRef.componentInstance.emitter.subscribe((transactions: Transaction[]) => {

            this.dialog.closeAll();
        });
    }
}
