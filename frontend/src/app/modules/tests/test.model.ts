import { IdentifiableModel } from '../../model/identifiable.model';
import { TestMeasurements } from './test-measurements.model';

export enum TestStatus {
    STARTED = 'STARTED',
    COMPLETED = 'COMPLETED',
    CANCELED = 'CANCELED'
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