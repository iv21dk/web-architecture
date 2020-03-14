import { Component, OnInit } from '@angular/core';
import {DataTemplateModel} from "../../model/data-template.model";
import {DataTemplateService} from "../../service/data-template.service";
import {ModalDialogService} from "../../modal-dialog/modal-dialog.service";

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
    this.modalDialogService.open("custom-modal-1");
  }

  update() {

  }

  closeModal(id: string) {

  }
}
