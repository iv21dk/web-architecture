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

  private TEST_DURATION_TIMEOUT_MS: number = 60 * 1000; // Server closes the test

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
          this.startPutTestData();
        },
        error => console.log(error) //TODO: handle error
      );
  }

  private startPutTestData() {
     const start: number = Date.now();
     let curr: number;
     do {
        setTimeout(() => this.putTestData(), 100);
        curr = Date.now();
     } while (!this.testClosed && curr - start < this.TEST_DURATION_TIMEOUT_MS)
     //this.testClosed = true;
     //this.stopTest();
  }

  private putTestData() {
    let randIndex: number = Math.trunc(Math.random() * this.dataTemplates.length);
    let randomTemplate: DataTemplateModel = this.dataTemplates[randIndex];
    this.http.put('/api/tests/' + this.currentTest.id + '/data/' + randomTemplate.id, undefined)
      .subscribe(
        res => {
        },
        error => {
          if (error.status === 423) {
            this.testClosed = true;
          }
        }
      );
  }

  stopTest() {
    if (this.currentTest === undefined) {
      alert("Test is not started!");
      return;
    }
    this.http.put('/api/tests/' + this.currentTest.id + '/close', undefined)
      .subscribe(
        res => {
          this.currentTest = undefined;
          this.testClosed = true;
        },
        error => console.log(error)
      );
    //this.currentTest = undefined;
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