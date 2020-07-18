import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ReportsComponent } from "./components/reports/reports.component";
import { TestsListComponent } from './components/tests-list/tests-list.component';
import { ChartCreateComponent } from './components/chart-create/chart-create.component';
import * as echarts from 'echarts';
import { NgxEchartsModule } from 'ngx-echarts';

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
      ChartCreateComponent],
    providers: [  ],
  })
  export class ReportsModule {
  }