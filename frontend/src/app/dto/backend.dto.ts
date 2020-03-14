import {NodeStatus} from "../backend/backend.component";

export class BackendDto {
  host: string;
  port: number;
  status: NodeStatus;
}
