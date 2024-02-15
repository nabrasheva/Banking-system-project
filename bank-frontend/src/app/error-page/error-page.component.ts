import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrl: './error-page.component.css'
})
export class ErrorPageComponent {
  @Input() errorMessage!: string;
  @Output() closeModal = new EventEmitter<void>();

  public close(): void {
    this.closeModal.emit();
  }
}
