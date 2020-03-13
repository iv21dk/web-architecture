import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {DataTemplateModel} from "../model/data-template.model";
import {BackendDto} from "../dto/backend.dto";
import {map} from "rxjs/operators";
import {BackendModel} from "../model/backend.model";

@Injectable({
  providedIn: 'root'
})
export class DataTemplateService {

  constructor(private http: HttpClient) {
  }

  private templates: DataTemplateModel[];

  getTemplates(): DataTemplateModel[] {
    return this.templates;
  }

  getTemplatesFromServer() {
    let res: any;
    res = this.http.get<DataTemplateModel[]>('http://localhost:8080/api/data-templates')
      .subscribe(
        items => this.templates = items,
        error => console.log(error) //TODO: handle error
      );
  }
}
