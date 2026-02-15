import { TestBed } from '@angular/core/testing';

import { Accountholderservice } from './accountholderservice';

describe('Accountholderservice', () => {
  let service: Accountholderservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Accountholderservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
