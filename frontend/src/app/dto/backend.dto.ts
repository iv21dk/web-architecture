import { NodeStatus } from '../backend/backend.model';

export class BackendDto {
  host: string;
  port: number;
  status: NodeStatus;
}
