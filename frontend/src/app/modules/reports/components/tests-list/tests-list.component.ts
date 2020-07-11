import { Component, OnInit } from '@angular/core';
import { TestService } from '../../../../tests/tests.service';
import { Observable } from 'rxjs';
import { TestModel } from '../../../../tests/test.model';

@Component({
  selector: 'app-tests-list',
  templateUrl: './tests-list.component.html',
  styleUrls: ['./tests-list.component.css']
})
export class TestsListComponent implements OnInit {

  constructor(private testService: TestService) { }

  tests$: Observable<TestModel[]>

  ngOnInit(): void {
    this.tests$ = this.testService.getTestsList();
  }

}
