import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TakeLoanComponent } from './take-loan.component';

describe('TakeLoanComponent', () => {
  let component: TakeLoanComponent;
  let fixture: ComponentFixture<TakeLoanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TakeLoanComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TakeLoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
