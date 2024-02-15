import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowSafeComponent } from './show-safe.component';

describe('ShowSafeComponent', () => {
  let component: ShowSafeComponent;
  let fixture: ComponentFixture<ShowSafeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShowSafeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ShowSafeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
