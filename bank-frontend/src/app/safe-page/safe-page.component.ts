import { Component } from '@angular/core';
import {Safe} from "../model/safe";
import {MatTableDataSource} from "@angular/material/table";
import {SafeRow} from "../model/SafeRow";
import {MatDialog} from "@angular/material/dialog";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-safe-page',
  templateUrl: './safe-page.component.html',
  styleUrl: './safe-page.component.css'
})
export class SafePageComponent {
  displayedColumns: string[] = ['name', 'button'];
  safes!: Safe[];
  dataSource: MatTableDataSource<SafeRow> = new MatTableDataSource();
  ngOnInit() : void{
    //get all safes by account from service and push name


  }

  constructor(private dialog: MatDialog,private route: ActivatedRoute, private router: Router,) {
  }

  findSafeByName(name:string):Safe | undefined
  {
    return this.safes.find(safe => safe.name === name);
  }

  viewSafe(element: SafeRow) {
    let safe = this.findSafeByName(element.name);

  }
}
