import {Component, OnInit} from '@angular/core';
import {BackendService} from './backend.service';
import {BackendModel} from '../backend/backend.model';

@Component({
  selector: 'app-backends-manager',
  templateUrl: './backends-manager.component.html',
  styleUrls: ['./backends-manager.component.css']
})
export class BackendsManagerComponent implements OnInit {

  constructor(private backendService: BackendService) { }

  ngOnInit(): void {
  }

  getBackends(): BackendModel[] {
    return this.backendService.getBackends();
  }

  createServer() {
    this.backendService.createServer();
  }

  isSelectedStoppedBackend(): boolean {
    return this.backendService.isSelectedStoppedBackend();
  }

  isSelectedStartedBackend(): boolean {
    return this.backendService.isSelectedStartedBackend();
  }

  startServer() {
    if (this.isSelectedStoppedBackend()) {
      this.backendService.startBackend(this.backendService.selectedBackend);
    }
  }

  stopServer() {
    if (this.isSelectedStartedBackend()) {
      this.backendService.stopBackend(this.backendService.selectedBackend);
    }
  }

  removeServer() {
    this.backendService.removeServer();
  }
}
