import { Component, OnInit } from '@angular/core';
import { SimpleChartOptions } from '../../../../model/simple-chart-options.model';
import { ChartService } from '../../../../services/chart.service';
import { ChartMeasurementsModel } from '../../chart-measurements.model';

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
    private charService: ChartService
  ) { }

  ngOnInit(): void {
    this.charService.readChartMeasurements().subscribe(
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

  private renderCreate(data: ChartMeasurementsModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.displayDataCount);
      data1.push(element.mongoDbMeasurements.avgCreateTimeMs);
      data2.push(element.sqlMeasurements.avgCreateTimeMs);
    });
    this.createOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderUpdate(data: ChartMeasurementsModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.displayDataCount);
      data1.push(element.mongoDbMeasurements.avgUpdateTimeMs);
      data2.push(element.sqlMeasurements.avgUpdateTimeMs);
    });
    this.updateOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderDelete(data: ChartMeasurementsModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.displayDataCount);
      data1.push(element.mongoDbMeasurements.avgDeleteTimeMs);
      data2.push(element.sqlMeasurements.avgDeleteTimeMs);
    });
    this.deleteOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderIndexedSearch(data: ChartMeasurementsModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.displayDataCount);
      data1.push(element.mongoDbMeasurements.avgFindByIndexedFieldTimeMs);
      data2.push(element.sqlMeasurements.avgFindByIndexedFieldTimeMs);
    });
    this.indexedSearchOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderNotIndexedSearh(data: ChartMeasurementsModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.displayDataCount);
      data1.push(element.mongoDbMeasurements.avgFindByNoIndexedFieldTimeMs);
      data2.push(element.sqlMeasurements.avgFindByNoIndexedFieldTimeMs);
    });
    this.notIndexedSearchOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

  private renderRetrieveFullObject(data: ChartMeasurementsModel[]) {
    const xAxisData = [];
    const data1 = [];
    const data2 = [];
    data.forEach(element => {
      xAxisData.push(element.displayDataCount);
      data1.push(element.mongoDbMeasurements.avgRetrieveFullData);
      data2.push(element.sqlMeasurements.avgRetrieveFullData);
    });
    this.retrieveFullObjectOptions = this.charService.createSimpleChartOptionsToMeasurementCompare(xAxisData, data1, data2);
  
  }

}
