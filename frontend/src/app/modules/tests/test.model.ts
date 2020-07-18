import { IdentifiableModel } from '../../model/identifiable.model';

export enum TestStatus {
    STARTED = 'STARTED',
    COMPLETED = 'COMPLETED',
    CANCELED = 'CANCELED'
}

export class TestMeasurements {
    avgCreateTimeMs: number;
    avgFindByIndexedFieldTimeMs: number;
    avgFindByNoIndexedFieldTimeMs: number;
    avgRetrieveFullData: number;
    avgUpdateTimeMs: number;
    avgDeleteTimeMs: number;
}

export class TestModel extends IdentifiableModel {
    startDate: Date;
    endDate: Date;
    requestsCount: number;
    successCount: number;
    failedCount: number;
    durationSec: number;
    backendClusterSize: number;
    mongoInitialDataCount: number;
    sqlInitialDataCount: number;
    mongoDbMeasurements: TestMeasurements;
    sqlMeasurements: TestMeasurements;
    status: TestStatus;
}