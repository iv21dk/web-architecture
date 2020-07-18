import { NgModule } from "@angular/core";
import { DataTemplatesComponent } from "./components/data-templates/data-templates.component";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DataTemplateComponent } from "./components/data-template/data-template.component";
import { NgbdSortableHeader } from "./components/data-templates/sortable.directive";

@NgModule({
    imports: [
      BrowserModule, 
      FormsModule, 
      ReactiveFormsModule,
      NgbModule],
    exports: [DataTemplatesComponent],
    declarations: [DataTemplateComponent, DataTemplatesComponent, NgbdSortableHeader],
    providers: [  ],
  })
  export class DataTemplatesModule {
  }