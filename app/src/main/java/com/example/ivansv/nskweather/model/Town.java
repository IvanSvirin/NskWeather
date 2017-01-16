package com.example.ivansv.nskweather.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Town {
    @Attribute(name = "index")
    private String index;
    @Attribute(name = "sname")
    private String sname;
    @Attribute(name = "latitude")
    private String latitude;
    @Attribute(name = "longitude")
    private String longitude;
    @ElementList(name = "FORECAST", inline = true)
    private List<Forecast> forecastList;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }
}
