import { TestBed, async, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DatePipe } from '@angular/common';
import { FormBuilder } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';

import { AppComponent } from './app.component';
import { AppService } from './app.service';
import { HttpClient } from 'selenium-webdriver/http';

describe('AppComponent', () => {
  let injector: TestBed;
  let service: AppService;
  let mock: HttpTestingController;
  let component: AppComponent;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        HttpClientTestingModule,
        MatAutocompleteModule,
      ],
      declarations: [
        AppComponent,
      ],
      providers: [
        DatePipe,
        FormBuilder,
      ]
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  describe('ngOnInit', () => {

  });

  // afterEach(() => {
  //   mock.verify();
  // });
});