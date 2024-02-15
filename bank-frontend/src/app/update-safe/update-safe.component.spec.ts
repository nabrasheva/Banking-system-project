import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSafeComponent } from './update-safe.component';

describe('UpdateSafeComponent', () => {
  let component: UpdateSafeComponent;
  let fixture: ComponentFixture<UpdateSafeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateSafeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateSafeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
