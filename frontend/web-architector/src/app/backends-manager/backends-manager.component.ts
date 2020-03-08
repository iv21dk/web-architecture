import {Component, OnInit} from '@angular/core';
import {NodeStatus} from "../java-server/java-server.component";

export interface IJavaServer {
  host: string;
  port: number;
  created: boolean;
  status: NodeStatus;
}

@Component({
  selector: 'app-backends-manager',
  templateUrl: './backends-manager.component.html',
  styleUrls: ['./backends-manager.component.css']
})
export class BackendsManagerComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.javaServers = [];
    this.javaServers.push({host:"localhost", port:8080, created:true, status:NodeStatus.STOPPED},
      {host:"129.165.12.345", port:8081, created: true, status:NodeStatus.STARTED})
  }

  javaServers: IJavaServer[];

  createServer() {
    let server: IJavaServer;
    server = new class implements IJavaServer {
      created: boolean;
      host: string;
      port: number;
      status: NodeStatus;
    }
    server.created = false;
    this.javaServers.push(server);
  }

}
