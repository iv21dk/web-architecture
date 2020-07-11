import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ReportsComponent } from "./components/reports/reports.component";
import { TestsListComponent } from './components/tests-list/tests-list.component';

@NgModule({
    imports: [
      BrowserModule, 
      FormsModule, 
      ReactiveFormsModule,
      NgbModule],
    exports: [ReportsComponent],
    declarations: [ReportsComponent, TestsListComponent],
    providers: [  ],
  })
  export class ReportsModule {
  }