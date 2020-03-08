import {Component, Input, OnInit} from '@angular/core';

export enum NodeStatus {
  STOPPED ='Stopped',
  STARTED ='Started'
}

export interface IJavaServer {
  host: string;
  port: number;
  created: boolean;
  status: NodeStatus;
}

@Component({
  selector: 'app-java-server',
  templateUrl: './java-server.component.html',
  styleUrls: ['./java-server.component.css']
})
export class JavaServerComponent implements OnInit {

  @Input() javaServer : IJavaServer;

  constructor() { }

  ngOnInit(): void {
  }

  createJavaServer(javaServer: IJavaServer) {
    javaServer.created = true;
    javaServer.status = NodeStatus.STOPPED;
  }

}
