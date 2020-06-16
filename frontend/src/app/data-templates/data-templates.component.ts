import { Component, OnInit } from '@angular/core';
import { DataTemplateModel } from '../data-template/data-template.model';
import { DataTemplateService } from './data-template.service';
import { ModalDialogService } from '../modal-dialog/modal-dialog.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorModel } from '../data-template/author.model';

@Component({
  selector: 'app-data-templates',
  templateUrl: './data-templates.component.html',
  styleUrls: ['./data-templates.component.css']
})
export class DataTemplatesComponent implements OnInit {

  constructor(private templateService: DataTemplateService, 
    private modalDialogService: ModalDialogService,
    private modalService: NgbModal) { }

  editTemplate: DataTemplateModel;
  editAuthorId: string;
  authorName: string;

  ngOnInit(): void {
  }

  getTemplates(): DataTemplateModel[] {
    return this.templateService.getTemplates();
  }

  create(dataTemplateDialog) {
    this.editTemplate = new DataTemplateModel();
    // this.editAuthorId = undefined;
    // this.modalDialogService.open(this.templateService.getDataTemplateDialogId());
    this.modalService.open(dataTemplateDialog, { 
      scrollable: true, 
      size: 'lg',
      centered: true});
  }

  update(dataTemplate: DataTemplateModel, dataTemplateDialog) {
    this.editTemplate = dataTemplate;
    if (this.editTemplate.author !== undefined && this.editTemplate.author !== null) {
      this.editAuthorId = dataTemplate.author.id;
    } else {
      this.editAuthorId = undefined;
    }
    //this.modalDialogService.open(this.templateService.getDataTemplateDialogId());
    //TODO: extract to method
    this.modalService.open(dataTemplateDialog, { 
      scrollable: true, 
      size: 'lg', 
      centered: true});
  }

  closeModal(save: boolean) {
    if (save === true) {
      this.editTemplate.author = new AuthorModel();
      if (this.editAuthorId === 'new') {
        this.editTemplate.author.name = this.authorName;
      } else {
        this.editTemplate.author.id = this.editAuthorId;
      }
      if (this.editTemplate.id === undefined || this.editTemplate.id === '') {
        this.templateService.createTemplate(this.editTemplate);
      } else {
        this.templateService.updateTemplate(this.editTemplate);
      }

      this.editTemplate = new DataTemplateModel();
    }
    //this.modalDialogService.close(this.templateService.getDataTemplateDialogId());
    
  }

  getAuthors(): AuthorModel[] {
    return this.templateService.getAuthors();
  }

}
