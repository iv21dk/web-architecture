import { Injectable } from "@angular/core";
import { SimpleChartOptions } from "../model/simple-chart-options.model";
import { ChartMeasurementsModel } from "../modules/reports/chart-measurements.model";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
export class ChartService {

constructor(private http: HttpClient){
}

  createSimpleChartOptionsToMeasurementCompare(
      xAxisData: any[], data1: number[], data2: number[]): SimpleChartOptions {
    
    const series1: string = "MongoDB";
    const series2: string = "SQL";

    return {
        legend: {
          data: [series1, series2],
          align: 'left',
        },
        tooltip: {},
        xAxis: {
          data: xAxisData,
          silent: false,
          splitLine: {
            show: false,
          },
        },
        yAxis: {},
        series: [
          {
            name: series1,
            type: 'bar',
            data: data1,
            animationDelay: (idx) => idx * 10,
          },
          {
            name: series2,
            type: 'bar',
            data: data2,
            animationDelay: (idx) => idx * 10 + 100,
          },
        ],
        animationEasing: 'elasticOut',
        animationDelayUpdate: (idx) => idx * 5,
      };
  }  

  readChartMeasurements(): Observable<ChartMeasurementsModel[]> {
    return this.http.get<ChartMeasurementsModel[]>('/api/charts/measurements');
  }

}