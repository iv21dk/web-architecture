import { Component, OnInit, ViewChildren, QueryList, PipeTransform } from '@angular/core';
import { DataTemplateModel } from '../data-template/data-template.model';
import { DataTemplateService } from './data-template.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DataTemplateComponent } from '../data-template/data-template.component';
import { SortEvent, NgbdSortableHeader } from '../table-sortable/sortable.directive';
import { FormControl } from '@angular/forms';
import { DecimalPipe } from '@angular/common';
import { Observable } from 'rxjs';
import { map, startWith, debounceTime } from 'rxjs/operators';

const compare = (v1: string, v2: string) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

@Component({
  selector: 'app-data-templates',
  templateUrl: './data-templates.component.html',
  styleUrls: ['./data-templates.component.css']
})
export class DataTemplatesComponent implements OnInit {

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  templates$: Observable<DataTemplateModel[]>;
  filter: FormControl = new FormControl('');
  filter$: Observable<string>;

  constructor(private templateService: DataTemplateService, 
    private modalService: NgbModal) {

      // this.templates$ = this.filter.valueChanges.pipe(
      //   startWith(''),
      //   map(text => this.search(text, pipe))
      // );
    }

  ngOnInit(): void {
    //this.templates$ = ;
    //this.templates$ = 
    //this.templates$ = this.filter.valueChanges.pipe(
    //  this.templateService.getTemplatesFromServer(this.filter.value));
    this.filter.valueChanges.pipe(
      startWith(''),
      debounceTime(1000),
    ).subscribe(
      text => this.templates$ = this.templateService.getTemplatesObservable(text));
  }

  getTemplates(): DataTemplateModel[] {
    return this.templateService.getTemplates();
  }

  // getTemplatesFromServer(query: string): Observable<DataTemplateModel[]> {
  //   return this.templateService.getTemplatesObservable(query);
  // }

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

  onSort({column, direction}: SortEvent) {
    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    // sorting countries
    // if (direction === '' || column === '') {
    //   this.templates = this.getTemplates();
    // } else {
    //   this.templates = [...this.getTemplates()].sort((a, b) => {
    //     const res = compare(`${a[column]}`, `${b[column]}`);
    //     return direction === 'asc' ? res : -res;
    //   });
    // }
  }

  search(text: string, pipe: PipeTransform): DataTemplateModel[] {
    return this.getTemplates().filter(template => {
      const term = text.toLowerCase();
      return template.name.toLowerCase().includes(term)
          || template.text.toLowerCase().includes(term)
          || template.author.name.toLowerCase().includes(term);
    });
  }

}
