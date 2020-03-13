import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BackendModel} from '../model/backend.model';
import {NodeStatus} from '../component/java-server/java-server.component';
import {BackendDto} from "../dto/backend.dto";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  constructor(private http: HttpClient) {
    this.backends = [];
    this.getBackendsFromServer();
  }

  selectedBackend: BackendModel;
  private backends: BackendModel[];

  getBackends(): BackendModel[] {
    return this.backends;
  }

  getBackendsFromServer() {
    this.http.get<BackendDto[]>('http://localhost:8080/api/backends')
      .pipe(map(backends => backends.map(dto => new BackendModel(dto))))
      .subscribe(
        // items => {
        //   this.backends = items;
        //   // this.backends = [];
        //   // items.forEach(item => this.backends.push(item));
        // },
        items => this.backends = items,
        error => console.log(error) //TODO: handle error
      );
  }

  createServer() {
    this.backends.push(undefined);
  }

  startBackend(backend: BackendModel) {
    backend.status = NodeStatus.STARTED;
  }

  stopBackend(backend: BackendModel) {
    backend.status = NodeStatus.STOPPED;
  }

  isSelectedBackend(backend: BackendModel): boolean {
    return this.selectedBackend === backend;
  }

  selectDeselectBackend(backend: BackendModel) {
    if (this.isSelectedBackend(backend)) {
      this.selectedBackend = undefined;
    } else {
      this.selectedBackend = backend;
    }
  }

  isSelectedStoppedBackend(): boolean {
    return (this.selectedBackend !== undefined
      && this.selectedBackend.status.valueOf() === NodeStatus.STOPPED.valueOf());
  }

  isSelectedStartedBackend(): boolean {
    return (this.selectedBackend !== undefined
      && this.selectedBackend.status === NodeStatus.STARTED);
  }

  removeServer() {
    if (this.isSelectedStoppedBackend()) {
       this.backends.splice(
         this.backends.findIndex(e => e === this.selectedBackend),
         1);
    }
  }
}
