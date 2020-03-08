import {Component, Input, OnInit} from '@angular/core';
import {IJavaServer} from "../backends-manager/backends-manager.component";

export enum NodeStatus {
  STOPPED ='Stopped',
  STARTED ='Started'
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
    //this.created = false;
  }

  // host: string;
  // port: number;
  // status: NodeStatus;
  // created: boolean;

  createJavaServer(javaServer: IJavaServer) {
    javaServer.created = true;
    javaServer.status = NodeStatus.STOPPED;
  }

}
