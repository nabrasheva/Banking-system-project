import {Component, EventEmitter, Inject, Input, Output} from '@angular/core';
import {Safe} from "../model/safe";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {ShowSafeRow} from "../model/show-safe-row";
import {SafeRow} from "../model/SafeRow";
import {AccountService} from "../services/account.service";
@Component({
  selector: 'app-show-safe',
  templateUrl: './show-safe.component.html',
  styleUrl: './show-safe.component.css'
})
export class ShowSafeComponent {

  @Input() safe!:Safe;
  @Output() emitter = new EventEmitter<any>();
  dataSource: MatTableDataSource<ShowSafeRow> = new MatTableDataSource();
  displayedColumns: string[] = ['name', 'funds', 'update_btn', 'delete_btn'];
  iban!:string;
  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any, private accountService: AccountService) {}

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

  }
}
