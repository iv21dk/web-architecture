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
  backends: BackendModel[];

  getBackends(): BackendModel[] {
    return this.backends;
    // if (this.backends === undefined) {
    //   this.getBackendsFromServer();
    //   return [];
    // } else {
    //   return this.backends;
    // }
  }

  getBackendsFromServer() {
    let res: any;
    res = this.http.get<BackendDto[]>('http://localhost:8080/api/backends')
      .pipe(map(backends => backends.map(dto => new BackendModel(dto))))
      .subscribe(
        res => {
          this.backends = [];
          this.backends.push(res);
          console.log(this.backends); //TODO: remove
        },
        error => console.log(error) //TODO: handle error
      );

    // let result: BackendModel[];
    // result = [];
    // result.push(
    //   {host: 'localhost', port: 8080, status: NodeStatus.STOPPED},
    //   {host: '129.165.12.345', port: 8081, status: NodeStatus.STARTED});
    // return result;
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

  isSelectredBackend(backend: BackendModel): boolean {
    return this.selectedBackend === backend;
  }

  selectDeselectBackend(backend: BackendModel) {
    if (this.isSelectredBackend(backend)) {
      this.selectedBackend = undefined;
    } else {
      this.selectedBackend = backend;
    }
  }

  isSelectedStoppedBackend(): boolean {
    return (this.selectedBackend !== undefined
      && this.selectedBackend.status === NodeStatus.STOPPED);
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
