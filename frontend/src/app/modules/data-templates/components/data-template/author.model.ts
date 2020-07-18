import { IdentifiableModel } from "../../../../model/identifiable.model";

export class AuthorModel extends IdentifiableModel {
  name: string;

  toString(): string {
    return this.name;
  }
}
