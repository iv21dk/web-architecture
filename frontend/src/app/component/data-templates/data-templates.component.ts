import { Component, OnInit } from '@angular/core';
import {DataTemplateModel} from "../../model/data-template.model";
import {DataTemplateService} from "../../service/data-template.service";
import {ModalDialogService} from "../../modal-dialog/modal-dialog.service";
import {AuthorModel} from "../../model/author.model";

@Component({
  selector: 'app-data-templates',
  templateUrl: './data-templates.component.html',
  styleUrls: ['./data-templates.component.css']
})
export class DataTemplatesComponent implements OnInit {

  editTemplate: DataTemplateModel = new DataTemplateModel();

  const dataTemplateDialogId: string = "data-template";

  constructor(private templateService: DataTemplateService, private modalDialogService: ModalDialogService) { }

  ngOnInit(): void {
  }

  getTemplates(): DataTemplateModel[] {
    return this.templateService.getTemplates();
  }

  getAuthors(): AuthorModel[]{
    return this.templateService.getAuthors();
  }

  create() {
    this.modalDialogService.open(this.dataTemplateDialogId);
  }

  update() {

  }

  closeModal(save: boolean) {



    this.modalDialogService.close(this.dataTemplateDialogId);
  }
}
