import { Component, OnInit } from '@angular/core';
import { SimpleChartOptions } from '../../../../model/simple-chart-options.model';
import { TestService } from '../../../../services/tests.service';
import { TestModel } from '../../../tests/test.model';
import { ChartService } from '../../../../services/chart.service';

@Component({
  selector: 'app-measurement-charts',
  templateUrl: './measurement-charts.component.html',
  styleUrls: ['./measurement-charts.component.css']
})
export class MeasurementChartsComponent implements OnInit {

  createOptions: SimpleChartOptions;
  updateOptions: SimpleChartOptions;
  deleteOptions: SimpleChartOptions;
  indexedSearchOptions: SimpleChartOptions;
  notIndexedSearchOptions: SimpleChartOptions;
  retrieveFullObjectOptions: SimpleChartOptions;

  constructor(
    private testService: TestService, 
    private charService: ChartService
  ) { }

  ngOnInit(): void {
    this.testService.readTestsList().subscribe(
      res => {
        this.renderCreate(res);
        this.renderUpdate(res);
        this.renderDelete(res);
        this.renderIndexedSearch(res);
        this.renderNotIndexedSearh(res);
        this.renderRetrieveFullObject(res);
      }
    )
  }

  private renderCreate(data: TestModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.mongoInitialDataCount);
      data1.push(element.mongoDbMeasurements.avgCreateTimeMs);
      data2.push(element.sqlMeasurements.avgCreateTimeMs);
    });
    this.createOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderUpdate(data: TestModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.mongoInitialDataCount);
      data1.push(element.mongoDbMeasurements.avgUpdateTimeMs);
      data2.push(element.sqlMeasurements.avgUpdateTimeMs);
    });
    this.updateOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderDelete(data: TestModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.mongoInitialDataCount);
      data1.push(element.mongoDbMeasurements.avgDeleteTimeMs);
      data2.push(element.sqlMeasurements.avgDeleteTimeMs);
    });
    this.deleteOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderIndexedSearch(data: TestModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.mongoInitialDataCount);
      data1.push(element.mongoDbMeasurements.avgFindByIndexedFieldTimeMs);
      data2.push(element.sqlMeasurements.avgFindByIndexedFieldTimeMs);
    });
    this.indexedSearchOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderNotIndexedSearh(data: TestModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.mongoInitialDataCount);
      data1.push(element.mongoDbMeasurements.avgFindByNoIndexedFieldTimeMs);
      data2.push(element.sqlMeasurements.avgFindByNoIndexedFieldTimeMs);
    });
    this.notIndexedSearchOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderRetrieveFullObject(data: TestModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.mongoInitialDataCount);
      data1.push(element.mongoDbMeasurements.avgRetrieveFullData);
      data2.push(element.sqlMeasurements.avgRetrieveFullData);
    });
    this.retrieveFullObjectOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

}
