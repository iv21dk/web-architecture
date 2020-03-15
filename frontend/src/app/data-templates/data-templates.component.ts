import { Component, OnInit } from '@angular/core';
import {DataTemplateModel} from '../data-template/data-template.model';
import {DataTemplateService} from './data-template.service';
import {ModalDialogService} from '../modal-dialog/modal-dialog.service';

@Component({
  selector: 'app-data-templates',
  templateUrl: './data-templates.component.html',
  styleUrls: ['./data-templates.component.css']
})
export class DataTemplatesComponent implements OnInit {

  constructor(private templateService: DataTemplateService, private modalDialogService: ModalDialogService) { }

  editTemplate: DataTemplateModel;
  editAuthorId: string;

  ngOnInit(): void {
  }

  getTemplates(): DataTemplateModel[] {
    return this.templateService.getTemplates();
  }

  create() {
    this.editTemplate = new DataTemplateModel();
    this.editAuthorId = undefined;
    this.modalDialogService.open(this.templateService.getDataTemplateDialogId());
  }

  update(dataTemplate: DataTemplateModel) {
    this.editTemplate = dataTemplate;
    if (this.editTemplate.author !== undefined && this.editTemplate.author !== null) {
      this.editAuthorId = dataTemplate.author.id;
    } else {
      this.editAuthorId = undefined;
    }
    this.modalDialogService.open(this.templateService.getDataTemplateDialogId());
  }


}
