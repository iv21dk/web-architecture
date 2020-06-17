import { Component, OnInit, ContentChild, ViewChild, AfterContentInit } from '@angular/core';
import { DataTemplateModel } from '../data-template/data-template.model';
import { DataTemplateService } from './data-template.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DataTemplateComponent } from '../data-template/data-template.component';

@Component({
  selector: 'app-data-templates',
  templateUrl: './data-templates.component.html',
  styleUrls: ['./data-templates.component.css']
})
export class DataTemplatesComponent implements OnInit {

  constructor(private templateService: DataTemplateService, 
    private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  getTemplates(): DataTemplateModel[] {
    return this.templateService.getTemplates();
  }

  create() {
    this.openDataTemplateModal(new DataTemplateModel());
  }

  update(dataTemplate: DataTemplateModel) {
    this.openDataTemplateModal(dataTemplate);
  }

  openDataTemplateModal(dataTemplate: DataTemplateModel) {
    const modalRef = this.modalService.open(DataTemplateComponent, { 
      scrollable: true, 
      size: 'lg',
      centered: true});
    modalRef.componentInstance.editTemplate = dataTemplate;
  }

}
