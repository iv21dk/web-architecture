import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {DataTemplateModel} from "../model/data-template.model";
import {AuthorModel} from "../model/author.model";

@Injectable({
  providedIn: 'root'
})
export class DataTemplateService {

  constructor(private http: HttpClient) {
    let a: AuthorModel;
    a = new AuthorModel();
    a.name = "author 1";

    let a2: AuthorModel;
    a2 = new AuthorModel();
    a2.name = "author 2";

    let t: DataTemplateModel;
    t = new DataTemplateModel();
    t.text = "ddjkghjdf";
    t.name = "name 1";
    t.author = a;

    let t2: DataTemplateModel;
    t2 = new DataTemplateModel();
    t2.text = "ddjkghj sdfsdf sd fsdfsd df";
    t2.name = "name 2";
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
}
