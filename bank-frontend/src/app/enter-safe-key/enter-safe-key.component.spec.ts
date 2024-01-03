import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterSafeKeyComponent } from './enter-safe-key.component';

describe('EnterSafeKeyComponent', () => {
  let component: EnterSafeKeyComponent;
  let fixture: ComponentFixture<EnterSafeKeyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EnterSafeKeyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EnterSafeKeyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
