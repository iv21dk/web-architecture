import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TestModel } from './test.model';
import { DataTemplateService } from '../data-templates/data-template.service';
import { DataTemplateModel } from '../data-template/data-template.model';

@Injectable({
    providedIn: 'root'
  })
export class TestService {

  constructor(private http: HttpClient, private dataTemplateService: DataTemplateService) { 
    this.getActiveTest();
  }

  private TEST_DURATION_TIMEOUT_MS: number = 2 * 60 * 1000; // Server closes the test
  private TEST_CLOSED_REQUEST_STATUS: number = 423;
  private PUT_TEST_DATA_DURATION_MS: number = 100;

  private currentTest: TestModel;
  private testClosed: boolean = true;
  private dataTemplates: DataTemplateModel[];
  
  getCurentTest(): TestModel {
    return this.currentTest;
  }

  startTest() {
  
    this.dataTemplateService.getTemplatesFromServer();
    this.dataTemplates = this.dataTemplateService.getTemplates();

    if(this.dataTemplates.length === 0) {
      alert("Please create data templates!");
      return;
    }
    
    this.http.post<TestModel>('/api/tests', undefined)
      .subscribe(
        res => {
          this.currentTest = res;
          this.testClosed = false;
          console.log("Test is created. test id=" + res.id + 
            ", curr time=" + (new Date).toString());
          this.putTestDataRecursively(Date.now());
        },
        error => console.log(error) //TODO: handle error
      );
  }

  private putTestDataRecursively(startTime: number) {
    if (this.testClosed) {
      return;
    }
    if (Date.now() - startTime > this.TEST_DURATION_TIMEOUT_MS) {
      return;
    }
    setTimeout(() => {
      this.putTestData();
      this.putTestDataRecursively(startTime);
    }, this.PUT_TEST_DATA_DURATION_MS);
  }
  
  private startPutTestData() {
     const start: number = Date.now();
     let curr: number;
     do {
        setTimeout(() => this.putTestData(), this.PUT_TEST_DATA_DURATION_MS);
        curr = Date.now();
     } while (!this.testClosed && curr - start < this.TEST_DURATION_TIMEOUT_MS)
  }

  private putTestData() {
    let randIndex: number = Math.trunc(Math.random() * this.dataTemplates.length);
    let randomTemplate: DataTemplateModel = this.dataTemplates[randIndex];
    this.http.put('/api/tests/' + this.currentTest.id + '/data/' + randomTemplate.id, undefined)
      .subscribe(
        res => {},
        error => {
          if (error.status === this.TEST_CLOSED_REQUEST_STATUS) {
            if (!this.testClosed) {
              this.testClosed = true;
              //console.time("Test is closed. test id=" + this.currentTest.id);
              console.log("Test is closed. test id=" + this.currentTest.id +
                 ", curr time=" + (new Date).toString());
            }
          }
        }
      );
  }

  stopTest() {
    if (this.currentTest === undefined) {
      alert("Test is not started!");
      return;
    }
    this.testClosed = true;
    this.http.put('/api/tests/' + this.currentTest.id + '/close', undefined)
      .subscribe(
        res => {
          this.currentTest = undefined;
        },
        error => console.log(error)
      );
  }

  private getActiveTest() {
    this.http.get<TestModel>('/api/tests/active')
      .subscribe(
        res => {
          if (res === null) {
            this.currentTest = undefined;
          } else {
            this.currentTest = res;
          }
        },
        error => console.log(error) //TODO: handle error
      );
  }

}