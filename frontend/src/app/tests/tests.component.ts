import { Component, OnInit } from '@angular/core';
import { TestService } from './tests.service';
import { TestModel } from './test.model';

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

  getCurrentTestStartTime(): Date {
    let test: TestModel = this.testService.getCurentTest();
    return test === undefined ? undefined : test.startDate;
  }

  stopTest() {
    this.testService.stopTest();
  }

}
