import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SafePageComponent } from './safe-page.component';

describe('SafePageComponent', () => {
  let component: SafePageComponent;
  let fixture: ComponentFixture<SafePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SafePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SafePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
