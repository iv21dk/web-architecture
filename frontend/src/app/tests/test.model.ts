import { IdentifiableModel } from '../model/identifiable.model';

export enum TestStatus {
    STOPPED = 'STOPPED',
    STARTED = 'STARTED'
}

export class TestModel extends IdentifiableModel {
    startDate: Date;
    requestsCount: number;
}