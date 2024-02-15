import { TestBed } from '@angular/core/testing';

import { AuthChekerService } from './auth-cheker.service';

describe('AuthChekerService', () => {
  let service: AuthChekerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthChekerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
