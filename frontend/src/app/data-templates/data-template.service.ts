import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DataTemplateModel} from "../data-template/data-template.model";
import {AuthorModel} from "../data-template/author.model";

@Injectable({
  providedIn: 'root'
})
export class DataTemplateService {

  constructor(private http: HttpClient) {
    this.getTemplatesFromServer();
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

  getTemplatesFromServer() {
    this.http.get<DataTemplateModel[]>('/api/data-templates')
      .subscribe(
        items => this.templates = items,
        error => console.log(error) //TODO: handle error
      );
  }

  createTemplate(template: DataTemplateModel) {
    this.http.post('/api/data-templates', template)
      .subscribe(
        res => {
          this.getTemplatesFromServer();
          this.getAuthorsFromServer();
        },
        error => console.log(error) //TODO: handle error
      );
  }

  updateTemplate(template: DataTemplateModel) {
    this.http.put('/api/data-templates/' + template.id, template)
      .subscribe(
        res => {
          this.getTemplatesFromServer();
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
