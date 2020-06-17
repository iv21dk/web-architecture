import { Component, ViewChildren, QueryList } from "@angular/core";
import { NgbdSortableHeader, SortEvent } from "./sortable.directive";

@Component({
    selector: 'ngbd-table-sortable',
    templateUrl: './table-sortable.html'
  })
  export class NgbdTableSortable {
  
    //countries = COUNTRIES;
  
    @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;
  
    onSort({column, direction}: SortEvent) {
  
      // resetting other headers
      this.headers.forEach(header => {
        if (header.sortable !== column) {
          header.direction = '';
        }
      });
  
      // sorting countries
      if (direction === '' || column === '') {
        this.countries = COUNTRIES;
      } else {
        this.countries = [...COUNTRIES].sort((a, b) => {
          const res = compare(`${a[column]}`, `${b[column]}`);
          return direction === 'asc' ? res : -res;
        });
      }
    }
  
  }