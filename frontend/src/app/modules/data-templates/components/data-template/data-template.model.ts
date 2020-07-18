import {AuthorModel} from "./author.model";
import { IdentifiableModel } from "../../../../model/identifiable.model";

export class DataTemplateModel extends IdentifiableModel {

  constructor() {
    super();
    if (this.author === null) {
      this.author = undefined;
    }
  }

  author: AuthorModel;
  name: string;
  text: string;
}
