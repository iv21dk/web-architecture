import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ReportsComponent } from "./components/reports/reports.component";
import { TestsListComponent } from './components/tests-list/tests-list.component';
import * as echarts from 'echarts';
import { NgxEchartsModule } from 'ngx-echarts';
import { MeasurementChartsComponent } from "./components/measurement-charts/measurement-charts.component";

@NgModule({
    imports: [
      BrowserModule, 
      FormsModule, 
      ReactiveFormsModule,
      NgbModule,
      NgxEchartsModule.forRoot({
        echarts
      })
    ],
    exports: [ReportsComponent],
    declarations: [
      ReportsComponent, 
      TestsListComponent, 
      MeasurementChartsComponent],
    providers: [  ],
  })
  export class ReportsModule {
  }