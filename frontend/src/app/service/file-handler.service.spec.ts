import { TestBed } from '@angular/core/testing';

import { FileHandlerService } from './file-handler.service';

describe('FileHandlerService', () => {
  let service: FileHandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FileHandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
