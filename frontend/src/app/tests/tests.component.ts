import { Component, OnInit } from '@angular/core';
import { TestService } from './tests.service';
import { TestModel, TestStatus } from './test.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-tests',
  templateUrl: './tests.component.html',
  styleUrls: ['./tests.component.css']
})
export class TestsComponent implements OnInit {

  constructor(private testService: TestService) { }

  //currentTest$: Observable<TestModel>;

  ngOnInit(): void {
    //this.currentTest$ = this.testService.getActiveTest();
  }

  getCurrentTest(): TestModel {
    return this.testService.getCurentTest();
  }

  isTestClosed(): boolean {
    return this.testService.isTestClosed();
  }
  

  startTest() {
    this.testService.startTest();
  }

  // getCurrentTestRequestsCount(): number {
  //   let test: TestModel = this.testService.getCurentTest();
  //   return test === undefined ? undefined : test.requestsCount;
  // }

  stopTest() {
    this.testService.stopTest();
  }

  getTimer(): number {
    return this.testService.getTimer();
  }

}
