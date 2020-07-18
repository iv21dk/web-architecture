import { Injectable } from "@angular/core";
import { SimpleChartOptions } from "../model/simple-chart-options.model";

@Injectable({
    providedIn: 'root'
  })
export class ChartService {

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


}