import { BackendDto } from "./backend.dto";

export enum NodeStatus {
  STOPPED = 'STOPPED',
  STARTED = 'STARTED'
}

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
