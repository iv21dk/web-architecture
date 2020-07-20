import { Component, OnInit } from '@angular/core';
import { TestService } from '../../../../services/tests.service';
import { TestModel, TestStatus } from '../../test.model';
import { Observable } from 'rxjs';

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

  stopTest() {
    this.testService.stopTest();
  }

}
