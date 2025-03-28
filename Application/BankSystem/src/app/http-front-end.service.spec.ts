import { TestBed } from '@angular/core/testing';

import { HttpFrontEndService } from './http-front-end.service';

describe('HttpFrontEndService', () => {
  let service: HttpFrontEndService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpFrontEndService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
