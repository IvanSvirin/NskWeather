package com.example.ivansv.nskweather.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "MMWEATHER")
public class Weather {
    @Element(name = "REPORT")
    private Report report;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
