import {AuthorModel} from "./author.model";
import {IdentifiableModel} from "../model/identifiable.model";

export class DataTemplateModel extends IdentifiableModel {
  author: AuthorModel;
  name: string;
  text: string;
}
