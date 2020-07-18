import {Component, Input, OnInit, TemplateRef, ViewContainerRef, AfterContentInit, ContentChild, ViewChild} from '@angular/core';
import {DataTemplateService} from '../data-templates/data-template.service';
import {AuthorModel} from './author.model';
import {DataTemplateModel} from './data-template.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-data-template',
  templateUrl: './data-template.component.html',
  styleUrls: ['./data-template.component.css']
})
export class DataTemplateComponent implements OnInit {

  constructor(private templateService: DataTemplateService, 
    private modalService: NgbModal) { }

  @Input() editTemplate: DataTemplateModel = new DataTemplateModel();
  authorId: string;
  authorName: string;
  
  AUTHOR_ID_NEW: string = 'new';

  ngOnInit(): void {
    if (this.editTemplate.author !== undefined && this.editTemplate.author !== null) {
      this.authorId = this.editTemplate.author.id;
    } else {
      this.authorId = this.AUTHOR_ID_NEW;
    }
  }

  getAuthors(): AuthorModel[] {
    return this.templateService.getAuthors();
  }

  closeModal(save: boolean) {
    if (save === true) {
      this.editTemplate.author = new AuthorModel();
      if (this.authorId === this.AUTHOR_ID_NEW) {
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
    this.modalService.dismissAll();
  }
}
