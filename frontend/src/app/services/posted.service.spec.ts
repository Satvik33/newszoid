import { TestBed } from '@angular/core/testing';

import { PostedService } from './posted.service';

describe('PostedService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PostedService = TestBed.get(PostedService);
    expect(service).toBeTruthy();
  });
});
