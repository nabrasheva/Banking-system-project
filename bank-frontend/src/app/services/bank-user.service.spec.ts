import { TestBed } from '@angular/core/testing';

import { BankUserService } from './bank-user.service';

describe('BankUserService', () => {
  let service: BankUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
