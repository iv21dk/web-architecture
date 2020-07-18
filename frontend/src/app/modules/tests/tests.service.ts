import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TestModel } from './test.model';
import { DataTemplateService } from '../data-templates/components/data-templates/data-template.service';
import { Observable } from 'rxjs';
import { DataTemplateModel } from '../data-templates/components/data-template/data-template.model';

@Injectable({
    providedIn: 'root'
  })
export class TestService {

  constructor(private http: HttpClient, private dataTemplateService: DataTemplateService) { 
    this.getActiveTest();
  }

  private TEST_DURATION_TIMEOUT_MS: number = 2 * 60 * 1000; // Server closes the test
  private TEST_CLOSED_REQUEST_STATUS: number = 423;
  private PUT_TEST_DATA_INTERVAL_MS: number = 300;

  private currentTest: TestModel;
  private testClosed: boolean = true;
  private dataTemplates: DataTemplateModel[];
  private timer: number = 0;

  isTestClosed(): boolean {
    return this.testClosed;
  }

  getCurentTest(): TestModel {
    return this.currentTest;
  }

  startTest(): Observable<TestModel> {
  
    this.dataTemplateService.getAllTemplatesFromServer();
    this.dataTemplates = this.dataTemplateService.getTemplates();

    if(this.dataTemplates.length === 0) {
      alert("Please create data templates!");
      return;
    }
    var request: Observable<TestModel> = this.http.post<TestModel>('/api/tests', undefined);
    request.subscribe(
      res => {
        this.currentTest = res;
        this.testClosed = false;
        console.log("Test is created. test id=" + res.id + 
          ", curr time=" + (new Date).toString());
        this.incrementTimer();
        this.putTestDataRecursively(Date.now());
      },
      error => console.log(error) //TODO: handle error
    )
    return request;
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
    }, this.PUT_TEST_DATA_INTERVAL_MS);
  }

  private putTestData() {
    let randIndex: number = Math.trunc(Math.random() * this.dataTemplates.length);
    let randomTemplate: DataTemplateModel = this.dataTemplates[randIndex];
    this.http.put('/api/tests/' + this.currentTest.id + '/data/' + randomTemplate.id, undefined)
      .subscribe(
        res => {
          if (!this.testClosed) {
            this.currentTest.requestsCount += 1;
          }
        },
        error => {
          if (error.status === this.TEST_CLOSED_REQUEST_STATUS) {
            if (!this.testClosed) {
              this.testClosed = true;
              console.log("Test is closed. test id=" + this.currentTest.id +
                ", curr time=" + (new Date).toString());
              this.getTestById(this.currentTest.id);
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
    this.http.put('/api/tests/' + this.currentTest.id + '/cancel', undefined)
      .subscribe(
        res => {
          this.testClosed = true;
          this.getTestById(this.currentTest.id);
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

  private getTestById(testId: string) {
    this.http.get<TestModel>('/api/tests/' + testId)
      .subscribe(
        res => this.currentTest = res,
        error => console.log(error) //TODO: handle error
      );
  }

  public getTestsList(): Observable<TestModel[]> {
    return this.http.get<TestModel[]>('/api/tests');
  }

  public getTestsListPageble(page: number, pageSize: number): Observable<TestModel[]> {
    return this.http.get<TestModel[]>('/api/tests?page=' + page + '&page-size=' + pageSize);
  }

  public getTestsCount(): Observable<number> {
    return this.http.get<number>('/api/tests/count');
  }

  private incrementTimer() {
    if (this.testClosed) {
      this.timer = 0;
      return;
    }
    setTimeout(()=>{
      this.timer++;
      console.log(this.timer);
      this.incrementTimer();
    }, 1000);

  }

  getTimer(): number {
    return this.timer;
  }

}