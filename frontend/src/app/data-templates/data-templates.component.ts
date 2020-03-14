import { Component, OnInit } from '@angular/core';
import {DataTemplateModel} from "../data-template/data-template.model";
import {DataTemplateService} from "./data-template.service";
import {ModalDialogService} from "../modal-dialog/modal-dialog.service";
import {AuthorModel} from '../data-template/author.model';

@Component({
  selector: 'app-data-templates',
  templateUrl: './data-templates.component.html',
  styleUrls: ['./data-templates.component.css']
})
export class DataTemplatesComponent implements OnInit {

  constructor(private templateService: DataTemplateService, private modalDialogService: ModalDialogService) { }

  ngOnInit(): void {
  }

  getTemplates(): DataTemplateModel[] {
    return this.templateService.getTemplates();
  }

  create() {
    this.modalDialogService.open(this.templateService.getDataTemplateDialogId());
  }

  update() {

  }


}
