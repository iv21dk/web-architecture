import {Component, Input, OnInit} from '@angular/core';
import {DataTemplateService} from '../data-templates/data-template.service';
import {ModalDialogService} from '../modal-dialog/modal-dialog.service';
import {AuthorModel} from './author.model';
import {DataTemplateModel} from './data-template.model';
import {isEmpty} from "rxjs/operators";

@Component({
  selector: 'app-data-template',
  templateUrl: './data-template.component.html',
  styleUrls: ['./data-template.component.css']
})
export class DataTemplateComponent implements OnInit {

  constructor(private templateService: DataTemplateService, private modalDialogService: ModalDialogService) { }

  @Input() editTemplate: DataTemplateModel = new DataTemplateModel();
  @Input() authorId: string;
  authorName: string;

  ngOnInit(): void {
     if (this.editTemplate !== undefined) {
    //   if (this.editTemplate.author !== null && this.editTemplate.author !== undefined) {
    //     this.authorId = this.editTemplate.author.id;
    //   }
     } else {
       this.editTemplate = new DataTemplateModel();
     }
  }

  getAuthors(): AuthorModel[] {
    return this.templateService.getAuthors();
  }

  closeModal(save: boolean) {
    if (save === true) {
      this.editTemplate.author = new AuthorModel();
      if (this.authorId === 'new') {
        this.editTemplate.author.name = this.authorName;
      } else {
        this.editTemplate.author.id = this.authorId;
      }
      if (this.editTemplate.id === undefined || this.editTemplate.id === '') {
        this.templateService.createTemplate(this.editTemplate);
      } else {
        this.templateService.updateTemplate(this.editTemplate);
      }

      this.editTemplate = new DataTemplateModel();
    }
    this.modalDialogService.close(this.templateService.getDataTemplateDialogId());
  }
}
