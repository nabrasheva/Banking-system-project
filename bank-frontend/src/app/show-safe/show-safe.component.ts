import {Component, EventEmitter, Inject, Input, Output} from '@angular/core';
import {Safe} from "../model/safe";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {ShowSafeRow} from "../model/show-safe-row";
import {SafeRow} from "../model/SafeRow";
import {AccountService} from "../services/account.service";
import {UpdateSafeComponent} from "../update-safe/update-safe.component";
import {SafeService} from "../services/safe.service";
@Component({
  selector: 'app-show-safe',
  templateUrl: './show-safe.component.html',
  styleUrl: './show-safe.component.css'
})
export class ShowSafeComponent {

  @Input() safe!:Safe;
  @Output() emitter = new EventEmitter<any>();
  @Output() updateEmitter = new EventEmitter<any>();
  dataSource: MatTableDataSource<ShowSafeRow> = new MatTableDataSource();
  displayedColumns: string[] = ['name', 'funds', 'update_btn', 'delete_btn'];
  iban!:string;
  constructor(private dialog: MatDialog, @Inject(MAT_DIALOG_DATA) private dialogData: any, private accountService: AccountService, private safeService: SafeService) {}

  ngOnInit()
  {
    this.safe=this.dialogData.safe;
    this.iban = this.dialogData.iban;
    this.dataSource.data.push({name: this.safe.name, funds: this.safe.initialFunds});
  }

  closeModal()
  {
    this.emitter.emit('none');
  }

  deleteSafe(element:SafeRow) {
    this.accountService.deleteSafeByNameAndIban(this.iban, element.name).subscribe({
      next: value => {
        this.emitter.emit(element.name);
      },
      error: err => {
        console.log(err);
      }
    })
  }

  updateSafe(element: SafeRow) {
    const dialogRef: MatDialogRef<UpdateSafeComponent, any> = this.dialog.open(UpdateSafeComponent, {
      data: { iban: this.iban, name: element.name }
    });

    dialogRef.componentInstance.emitter.subscribe((name:string)=>{
      let safe = this.dataSource.data.at(0);
      if(safe !== undefined)
      {
        console.log('in if')
        this.safeService.getSafeByName(name).subscribe({
          next:value => {
            // this.safe = value;
            // console.log(this.safe)
            this.updateEmitter.emit(value);
            // const showSafeRow: ShowSafeRow = {
            //   name: this.safe.name,
            //   funds: this.safe.initialFunds
            // }
            // this.dataSource.data.splice(0, 1);
            // this.dataSource.data.push(showSafeRow);
            // this.dataSource._updateChangeSubscription();
          },
          error: err => {
           console.log(err);
          }
        })
      }
      this.dialog.closeAll();
    })
  }
}
