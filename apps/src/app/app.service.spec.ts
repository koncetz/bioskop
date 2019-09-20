import { TestBed, getTestBed, inject, async } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';

describe('AppService', () => {
  let injector: TestBed;
  let service: AppService;
  let mock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        AppService,
        HttpClient,
      ],
    });

    injector = getTestBed();
    service = injector.get(AppService);
    mock = injector.get(HttpTestingController);
  });

  it('should be created', () => {
    const service: AppService = TestBed.get(AppService);
    expect(service).toBeTruthy();
  });
});