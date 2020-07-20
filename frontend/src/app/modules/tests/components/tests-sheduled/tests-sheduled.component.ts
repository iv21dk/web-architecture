import { Component, OnInit } from '@angular/core';
import { TestService } from '../../../../services/tests.service';

@Component({
  selector: 'app-tests-sheduled',
  templateUrl: './tests-sheduled.component.html',
  styleUrls: ['./tests-sheduled.component.css']
})
export class TestsSheduledComponent implements OnInit {

  constructor(private testService: TestService) { }

  ngOnInit(): void {
  }

  private TESTS_INTERVAL_MS: number = 1000 * 60 * 2; // 2 min

  repeatCount: number = 150; //duration 5 hours by default
  started: boolean = false;
  iteration: number;

  startTests() {
    this.started = true;
    this.iteration = 0;
    this.startTestRecursively();  
  }

  startTestRecursively() {
    if (this.iteration >= this.repeatCount || !this.started) {
      this.started = false;
      return;
    }
    this.iteration++;
    this.testService.startTest();
    setTimeout(() => {
        this.startTestRecursively();
    }, this.TESTS_INTERVAL_MS);
    
  }

  stopTests() {
    this.started = false;
  }

}
