import { Component, OnInit } from '@angular/core';
import { SimpleChartOptions } from '../../../../model/simple-chart-options.model';
import { TestService } from '../../../../services/tests.service';
import { TestModel } from '../../../tests/test.model';
import { ChartService } from '../../../../services/chart.service';

@Component({
  selector: 'app-chart-create',
  templateUrl: './chart-create.component.html',
  styleUrls: ['./chart-create.component.css']
})
export class ChartCreateComponent implements OnInit {

  options: SimpleChartOptions;

  constructor(
    private testService: TestService, 
    private charService: ChartService
  ) { }

  ngOnInit(): void {
    this.testService.readTestsList().subscribe(
      res => this.render(res)
    )
  }

  private render(data: TestModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];

    data.forEach(element => {
      xAxisData.push(element.mongoInitialDataCount);
      data1.push(element.mongoDbMeasurements.avgCreateTimeMs);
      data2.push(element.sqlMeasurements.avgCreateTimeMs);
    });
    this.options = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

}
