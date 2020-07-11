import { IdentifiableModel } from '../model/identifiable.model';

export enum TestStatus {
    STOPPED = 'STOPPED',
    STARTED = 'STARTED'
}

export class TestMeasurements {
    avgCreateTimeMs: number;
    avgFindByIndexedFieldTimeMs: number;
    avgFindByNoIndexedFieldTimeMs: number;
    avgUpdateTimeMs: number;
    avgDeleteTimeMs: number;
}

export class TestModel extends IdentifiableModel {
    startDate: Date;
    requestsCount: number;
    successCount: number;
    failedCount: number;
    durationSec: number;
    backendClusterSize: number;
    mongoInitialDataCount: number;
    sqlInitialDataCount: number;
    mongoDbMeasurements: TestMeasurements;
    sqlMeasurements: TestMeasurements;
}