import {Component, OnInit} from '@angular/core';
import {IJavaServer, NodeStatus} from "../java-server/java-server.component";

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
    server = {host:undefined, port: undefined, created:false, status:undefined}
    this.javaServers.push(server);
  }

}
