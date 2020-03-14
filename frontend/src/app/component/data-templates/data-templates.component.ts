import { Component, OnInit } from '@angular/core';
import {DataTemplateModel} from "../../model/data-template.model";
import {DataTemplateService} from "../../service/data-template.service";

@Component({
  selector: 'app-data-templates',
  templateUrl: './data-templates.component.html',
  styleUrls: ['./data-templates.component.css']
})
export class DataTemplatesComponent implements OnInit {

  constructor(private templateService: DataTemplateService) { }

  ngOnInit(): void {
  }

  getTemplates(): DataTemplateModel[] {
    return this.templateService.getTemplates();
  }

  create() {

  }

  update() {

  }
}
