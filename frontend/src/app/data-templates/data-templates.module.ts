import { NgModule } from "@angular/core";
import { DataTemplatesComponent } from "./data-templates.component";
import { DataTemplateComponent } from "../data-template/data-template.component";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [
      BrowserModule, 
      FormsModule, 
      ReactiveFormsModule,
      NgbModule],
    exports: [DataTemplatesComponent],
    declarations: [DataTemplateComponent, DataTemplatesComponent],
    providers: [  ],
  })
  export class DataTemplatesModule {
  }