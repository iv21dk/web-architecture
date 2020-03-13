import {NodeStatus} from "../component/java-server/java-server.component";
import {BackendDto} from "../dto/backend.dto";

export class BackendModel {
  host: string;
  port: number;
  status: NodeStatus;

  constructor(dto: BackendDto) {
    if (dto !== undefined) {
      //TODO: use mapper
      this.host = dto.host;
      this.port = dto.port;
      this.status = dto.status;
    }
  }
}
