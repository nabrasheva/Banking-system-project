import {Component, EventEmitter, Inject, Input, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Safe} from "../model/safe";

@Component({
  selector: 'app-enter-safe-key',
  templateUrl: './enter-safe-key.component.html',
  styleUrl: './enter-safe-key.component.css'
})
export class EnterSafeKeyComponent {
  @Input() key!:string;
  @Output() emitter = new EventEmitter<any>();

  keyForm!:FormGroup;
  public error: boolean = false;
  public errorMessage: string = '';

  constructor(@Inject(MAT_DIALOG_DATA) private dialogData: any,private fb: FormBuilder) {}

  ngOnInit(){
    this.key = this.dialogData.key;

    this.keyForm = this.fb.group({
      key: ['', Validators.required]
    });
  }

  authenticateSafeView() {
    if(this.keyForm.get('key')?.value === this.key)
    {
      this.emitter.emit(true);
    }
    else {
      this.error = true;
      this.errorMessage = 'Incorrect key!';
    }
  }

  public closeModal(): void {
    this.error = false;
    this.errorMessage = '';
  }
}
