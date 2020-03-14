import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DataTemplateModel} from "../data-template/data-template.model";
import {AuthorModel} from "../data-template/author.model";

@Injectable({
  providedIn: 'root'
})
export class DataTemplateService {

  constructor(private http: HttpClient) {
    let a: AuthorModel;
    a = new AuthorModel();
    a.name = "Пушкин А.С.";

    let a2: AuthorModel;
    a2 = new AuthorModel();
    a2.name = "Лермонтов М.Ю.";

    let t: DataTemplateModel;
    t = new DataTemplateModel();
    t.name = "Золотая рыбка";
    t.text = "Жыли были дед да баба ...";
    t.author = a;

    let t2: DataTemplateModel;
    t2 = new DataTemplateModel();
    t2.name = "Евгений Онегин";
    t2.text = "Мой дядя самых честных правил...";

    t2.author = a;

    this.templates.push(t, t2);
    this.authors.push(a, a2);
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
    let res: any;
    res = this.http.get<DataTemplateModel[]>('http://localhost:8080/api/data-templates')
      .subscribe(
        items => this.templates = items,
        error => console.log(error) //TODO: handle error
      );
  }

  getDataTemplateDialogId(): string {
    return 'data-template';
  }
}
