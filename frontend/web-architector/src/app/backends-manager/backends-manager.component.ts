import {Component, OnInit} from '@angular/core';
import {IJavaServer, JavaServerComponent, NodeStatus} from '../java-server/java-server.component';

@Component({
  selector: 'app-backends-manager',
  templateUrl: './backends-manager.component.html',
  styleUrls: ['./backends-manager.component.css']
})
export class BackendsManagerComponent implements OnInit {

  constructor() { }

  javaServers: IJavaServer[];
  selectedJavaServers: IJavaServer[];
  stoppedSelected: boolean;
  startedSelected: boolean;

  ngOnInit(): void {
    this.javaServers = [];
    this.javaServers.push(
      {host: 'localhost', port: 8080, created: true, status: NodeStatus.STOPPED, selected: false},
      {host: '129.165.12.345', port: 8081, created: true, status: NodeStatus.STARTED, selected: false});
    this.stoppedSelected = false;
    this.startedSelected = false;
    this.selectedJavaServers = [];
  }

  createServer() {
    let server: IJavaServer;
    server = {
      host: undefined,
      port: undefined,
      created: false,
      status: undefined,
      selected: false
    };
    this.javaServers.push(server);
  }

  onSelectJavaServer(javaServer: IJavaServer) {
    this.calculateSelectedServers();
  }

  onDeselectJavaServer(javaServer: IJavaServer) {
    this.calculateSelectedServers();
  }

  calculateSelectedServers() {
    this.selectedJavaServers = [] as IJavaServer[];
    this.stoppedSelected = false;
    this.startedSelected = false;
    this.javaServers.forEach(
      function(value: IJavaServer) {
        if (value.selected === true) {
          if (value.status === NodeStatus.STARTED) {
            this.startedSelected = true;
          }
          if (value.status === NodeStatus.STOPPED) {
            this.stoppedSelected = true;
          }
          this.selectedJavaServers.push(value);
        }
      }, this);
  }

  startServers() {
    this.selectedJavaServers.forEach(
      function(value: IJavaServer) {
        if (value.status === NodeStatus.STOPPED) {
          alert('started');
        }
      }, this);
  }
}
