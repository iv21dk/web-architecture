import { Component, OnInit } from '@angular/core';
import {DataTemplateModel} from "../model/data-template.model";

@Component({
  selector: 'app-modal-dialog',
  templateUrl: './modal-dialog.component.html',
  styleUrls: ['./modal-dialog.component.css']
})
export class ModalDialogComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  dataTemplate: DataTemplateModel;

  

}
