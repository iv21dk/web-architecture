import { Component, OnInit } from '@angular/core';
import { TestModel } from '../../test.model';
import { TestService } from '../../../../services/tests.service';

@Component({
  selector: 'app-current-test',
  templateUrl: './current-test.component.html',
  styleUrls: ['./current-test.component.css']
})
export class CurrentTestComponent implements OnInit {

  constructor(private testService: TestService) { }

  ngOnInit(): void {
  }

  getCurrentTest(): TestModel {
    return this.testService.getCurentTest();
  }

  isTestClosed(): boolean {
    return this.testService.isTestClosed();
  }

  getTimer(): number {
    return this.testService.getTimer();
  }

}
