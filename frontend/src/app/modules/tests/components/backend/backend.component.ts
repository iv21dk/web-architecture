import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BackendModel, NodeStatus} from './backend.model';
import {BackendService} from "../../../../services/backend.service";
import {strict} from "assert";

// export interface IJavaServer {
//   host: string;
//   port: number;
//   created: boolean;
//   status: NodeStatus;
//   selected: boolean;
// }

@Component({
  selector: 'app-backend',
  templateUrl: './backend.component.html',
  styleUrls: ['./backend.component.css']
})
export class BackendComponent implements OnInit {

  @Input() backendModel: BackendModel;

  // @Output() selectEvent = new EventEmitter <BackendModel>();
  // @Output() deselectEvent = new EventEmitter <BackendModel>();

  created: boolean;
  selected: boolean;

  constructor(private backendService: BackendService) { }

  ngOnInit(): void {
    this.selected = false;
    if (this.backendModel !== undefined) {
      this.created = true;
    } else {
      this.backendModel = new BackendModel(
        {host: '', port: 0, status: NodeStatus.STOPPED});
      this.created = false;
    }
  }

  createJavaServer() {
    if (this.backendModel.host === undefined || this.backendModel.host === '') {
      alert('server host should be defined');
      return;
    }
    if (this.backendModel.port === undefined || this.backendModel.port === 0) {
      alert('server port should be defined');
      return;
    }
    this.created = true;
    this.backendModel.status = NodeStatus.STOPPED;
    // javaServer.selected = false; //TODO: need to remove selecting via inClick
  }

  onClick() {
    if (this.created) {
      this.backendService.selectDeselectBackend(this.backendModel);
    }
  }

  isSelected(): boolean {
    return this.backendService.isSelectedBackend(this.backendModel);
  }
}
