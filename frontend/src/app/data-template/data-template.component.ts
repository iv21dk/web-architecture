import { Component, OnInit } from '@angular/core';
import {DataTemplateService} from "../data-templates/data-template.service";
import {ModalDialogService} from "../modal-dialog/modal-dialog.service";
import {AuthorModel} from "./author.model";
import {DataTemplateModel} from "./data-template.model";

@Component({
  selector: 'app-data-template',
  templateUrl: './data-template.component.html',
  styleUrls: ['./data-template.component.css']
})
export class DataTemplateComponent implements OnInit {

  constructor(private templateService: DataTemplateService, private modalDialogService: ModalDialogService) { }

  editTemplate: DataTemplateModel = new DataTemplateModel();

  ngOnInit(): void {
  }

  getAuthors(): AuthorModel[]{
    return this.templateService.getAuthors();
  }

  closeModal(save: boolean) {
    if (save === true) {
      this.editTemplate = new DataTemplateModel();
    }
    this.modalDialogService.close(this.templateService.getDataTemplateDialogId());
  }
}
