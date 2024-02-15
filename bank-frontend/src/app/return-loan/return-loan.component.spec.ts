import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnLoanComponent } from './return-loan.component';

describe('ReturnLoanComponent', () => {
  let component: ReturnLoanComponent;
  let fixture: ComponentFixture<ReturnLoanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReturnLoanComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReturnLoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
