import { TestMeasurements } from "../tests/test-measurements.model";

export class ChartMeasurementsModel {
    displayDataCount: string;
    mongoDbMeasurements: TestMeasurements;
    sqlMeasurements: TestMeasurements;
}