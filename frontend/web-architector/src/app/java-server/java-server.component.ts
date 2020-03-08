import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

export enum NodeStatus {
  STOPPED = 'Stopped',
  STARTED = 'Started'
}

export interface IJavaServer {
  host: string;
  port: number;
  created: boolean;
  status: NodeStatus;
  selected: boolean;
}

@Component({
  selector: 'app-java-server',
  templateUrl: './java-server.component.html',
  styleUrls: ['./java-server.component.css']
})
export class JavaServerComponent implements OnInit {

  @Input() javaServer: IJavaServer;

  @Output() selectEvent = new EventEmitter <IJavaServer>();
  @Output() deselectEvent = new EventEmitter <IJavaServer>();

  constructor() { }

  ngOnInit(): void {
    console.log(this.javaServer);
  }

  createJavaServer(javaServer: IJavaServer) {
    if (javaServer.host === undefined || javaServer.host === '') {
      alert('server host should be defined');
      return;
    }
    if (javaServer.port === undefined || javaServer.port === 0) {
      alert('server port should be defined');
      return;
    }
    javaServer.created = true;
    javaServer.status = NodeStatus.STOPPED;
    // javaServer.selected = false; //TODO: need to remove selecting via inClick
  }

  onClick(javaServer: IJavaServer) {
    if (javaServer.selected === true) {
      javaServer.selected = false;
      this.deselectEvent.emit(javaServer);
    } else if (javaServer.created === true) {
      javaServer.selected = true;
      this.selectEvent.emit(javaServer);
    }
  }

  startServer(server: IJavaServer) {
    server.status = NodeStatus.STARTED;
  }

}
