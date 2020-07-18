
export class Legend {
    data: string [];
    align: string
}

export class XAxis {
    data: any[];
    silent: boolean;
    splitLine: SplitLine;
}

export class SplitLine {
    show: boolean;
}

export class Series {
    name: string;
    type: string;
    data: number [];
    animationDelay: any;
}

export class SimpleChartOptions {
    legend: Legend;
    tooltip: any;
    xAxis: XAxis;
    yAxis: any;
    series: Series[];
    animationEasing: string;
    animationDelayUpdate: any;
}