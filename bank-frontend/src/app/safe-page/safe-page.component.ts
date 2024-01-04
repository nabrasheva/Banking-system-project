import { Component } from '@angular/core';
import {Safe} from "../model/safe";
import {MatTableDataSource} from "@angular/material/table";
import {SafeRow} from "../model/SafeRow";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../services/account.service";
import {EnterSafeKeyComponent} from "../enter-safe-key/enter-safe-key.component";
import {ShowSafeComponent} from "../show-safe/show-safe.component";
import {CreateSafeComponent} from "../create-safe/create-safe.component";

@Component({
  selector: 'app-safe-page',
  templateUrl: './safe-page.component.html',
  styleUrl: './safe-page.component.css'
})
export class SafePageComponent {
  displayedColumns: string[] = ['name', 'button'];
  safes!: Safe[];
  dataSource: MatTableDataSource<SafeRow> = new MatTableDataSource();
  iban!:string;

  constructor(private dialog: MatDialog,private route: ActivatedRoute, private router: Router, private accountService:AccountService) {
  }
  ngOnInit() : void{

    this.route.params.subscribe(params => {
    this.iban = params['iban'];
    });

    this.accountService.getSafesByAccount(this.iban).subscribe({
      next: (data) => {
        this.safes = data;
        this.safes.forEach(safe=>{
          this.dataSource.data.push({name:safe.name});
        })
      },
      error: (error) => {
        console.error(error);
      }
    })

    //this.dataSource.data.push({name:'Nia'}, {name:'Ani'});
  }

  findSafeByName(name:string):Safe | undefined
  {
    return this.safes.find(safe => safe.name === name);
  }

  viewSafe(element: SafeRow) {
    let safe = this.findSafeByName(element.name);
    const dialogRef: MatDialogRef<EnterSafeKeyComponent, any> = this.dialog.open(EnterSafeKeyComponent, {
      data: { key: safe }
    });

    let showSafe = false;
    dialogRef.componentInstance.emitter.subscribe((isAuthenticated: boolean) => {
        showSafe = isAuthenticated;
      this.dialog.closeAll();
    });

    if(showSafe)
    {
      const dialogRef: MatDialogRef<ShowSafeComponent, any> = this.dialog.open(ShowSafeComponent, {
        data: { safe: safe , iban: this.iban}
      });

      dialogRef.componentInstance.emitter.subscribe((name)=>{
        if(name !== 'none')
        {
          const safeRow = this.dataSource.data.find(safeRow => safeRow.name === name);
          if(safeRow !== undefined)
          {
            const index = this.dataSource.data.indexOf(safeRow);
            if (index !== -1) {
              this.dataSource.data.splice(index, 1);
              this.dataSource._updateChangeSubscription();
            }
          }

        }
        this.dialog.closeAll();
      })
    }
  }

  createSafe() {
    const dialogRef: MatDialogRef<CreateSafeComponent, any> = this.dialog.open(CreateSafeComponent, {
      data: { iban: this.iban}
    });

    dialogRef.componentInstance.emitter.subscribe((safe:Safe)=>{
      this.dataSource.data.push({name: safe.name});
      this.dataSource._updateChangeSubscription();
      this.dialog.closeAll();
    })
  }
}
