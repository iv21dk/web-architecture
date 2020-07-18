import { Component, OnInit } from '@angular/core';
import { TestService } from '../../../tests/tests.service';
import { Observable } from 'rxjs';
import { TestModel } from '../../../tests/test.model';

@Component({
  selector: 'app-tests-list',
  templateUrl: './tests-list.component.html',
  styleUrls: ['./tests-list.component.css']
})
export class TestsListComponent implements OnInit {

  constructor(private testService: TestService) { }

  tests$: Observable<TestModel[]>
  page: number = 1;
  pageSize: number = 10;
  contentSize: number;

  ngOnInit(): void {
    this.testService.getTestsCount().subscribe(
      count => this.contentSize = count 
    );
    this.refreshTests();   
  }

 refreshTests() {
  this.tests$ = this.testService.getTestsListPageble(this.page, this.pageSize);  
  // this.countries = COUNTRIES
    //   .map((country, i) => ({id: i + 1, ...country}))
    //   .slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

}
