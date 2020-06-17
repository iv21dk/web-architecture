import { NgModule } from "@angular/core";
import { DataTemplatesComponent } from "./data-templates.component";
import { DataTemplateComponent } from "../data-template/data-template.component";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";

@NgModule({
    imports: [BrowserModule, FormsModule],
    exports: [DataTemplatesComponent],
    declarations: [DataTemplateComponent, DataTemplatesComponent],
    providers: []
  })
  export class DataTemplatesModule {
  }