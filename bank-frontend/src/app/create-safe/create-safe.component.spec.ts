import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSafeComponent } from './create-safe.component';

describe('CreateSafeComponent', () => {
  let component: CreateSafeComponent;
  let fixture: ComponentFixture<CreateSafeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateSafeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateSafeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
