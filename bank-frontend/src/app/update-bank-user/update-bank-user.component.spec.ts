import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBankUserComponent } from './update-bank-user.component';

describe('UpdateBankUserComponent', () => {
  let component: UpdateBankUserComponent;
  let fixture: ComponentFixture<UpdateBankUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateBankUserComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateBankUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
