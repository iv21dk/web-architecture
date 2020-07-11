import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DataTemplateModel} from "../data-template/data-template.model";
import {AuthorModel} from "../data-template/author.model";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataTemplateService {

  constructor(private http: HttpClient) {
    this.getAllTemplatesFromServer();
    this.getAuthorsFromServer();
  }

  private templates: DataTemplateModel[] = [];
  private authors: AuthorModel[] = [];

  getTemplates(): DataTemplateModel[] {
    return this.templates;
  }

  getAuthors(): AuthorModel[] {
    return this.authors;
  }

  getAllTemplatesFromServer() {
    this.http.get<DataTemplateModel[]>('/api/data-templates')
      .subscribe(
        items => this.templates = items,
        error => console.log(error) //TODO: handle error
      );
  }

  getTemplatesObservable(query: string): Observable<DataTemplateModel[]> {
    return this.http.get<DataTemplateModel[]>('/api/data-templates?query=' + query);
  }

  createTemplate(template: DataTemplateModel) {
    this.http.post('/api/data-templates', template)
      .subscribe(
        res => {
          this.getAllTemplatesFromServer();
          this.getAuthorsFromServer();
        },
        error => console.log(error) //TODO: handle error
      );
  }

  updateTemplate(template: DataTemplateModel) {
    this.http.put('/api/data-templates/' + template.id, template)
      .subscribe(
        res => {
          this.getAllTemplatesFromServer();
          this.getAuthorsFromServer();
        },
        error => console.log(error) //TODO: handle error
      );
  }

  getAuthorsFromServer() {
    this.http.get<AuthorModel[]>('/api/data-templates/authors')
      .subscribe(
        items => this.authors = items,
        error => console.log(error) //TODO: handle error
      );
  }

  getDataTemplateDialogId(): string {
    return 'data-template';
  }
}
