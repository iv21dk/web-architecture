import {NodeStatus} from "../component/java-server/java-server.component";

export class BackendDto {
  host: string;
  port: number;
  status: NodeStatus;
}
