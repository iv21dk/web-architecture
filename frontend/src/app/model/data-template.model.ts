import {AuthorModel} from "./author.model";
import {IdentifiableModel} from "./identifiable.model";

export class DataTemplateModel extends IdentifiableModel {
  author: AuthorModel;
  text: string;
}
