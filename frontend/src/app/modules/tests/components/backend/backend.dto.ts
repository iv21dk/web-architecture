import { NodeStatus } from "./backend.model";

export class BackendDto {
  host: string;
  port: number;
  status: NodeStatus;
}
