import { Component, OnInit } from '@angular/core';
import { TestService } from './tests.service';
import { TestModel, TestStatus } from './test.model';

@Component({
  selector: 'app-tests',
  templateUrl: './tests.component.html',
  styleUrls: ['./tests.component.css']
})
export class TestsComponent implements OnInit {

  constructor(private testService: TestService) { }

  ngOnInit(): void {
  }

  startTest() {
    this.testService.startTest();
  }

  getCurrentTestId(): String {
    let test: TestModel = this.testService.getCurentTest();
    return test === undefined ? "" : test.id;
  }

  getCurrentTestStartTime(): String {
    let test: TestModel = this.testService.getCurentTest();
    return test === undefined ? undefined : (new Date(test.startDate)).toString();
  }

  getCurrentTestRequestsCount(): number {
    let test: TestModel = this.testService.getCurentTest();
    return test === undefined ? undefined : test.requestsCount;
  }

  getTestStatus(): TestStatus {
    return this.testService.getTestStatus();
  }

  stopTest() {
    this.testService.stopTest();
  }

}
