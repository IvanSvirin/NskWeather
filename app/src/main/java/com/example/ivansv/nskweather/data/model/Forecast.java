package com.example.ivansv.nskweather.data.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "FORECAST")
public class Forecast implements Serializable {
    @Attribute(name = "day")
    private String day;
    @Attribute(name = "month")
    private String month;
    @Attribute(name = "year")
    private String year;
    @Attribute(name = "hour")
    private String hour;
    @Attribute(name = "tod")
    private String tod;
    @Attribute(name = "predict")
    private String predict;
    @Attribute(name = "weekday")
    private String weekday;
    @Element(name = "PHENOMENA")
    private Phenomena phenomena;
    @Element(name = "PRESSURE")
    private Pressure pressure;
    @Element(name = "TEMPERATURE")
    private Temperature temperature;
    @Element(name = "WIND")
    private Wind wind;
    @Element(name = "RELWET")
    private Relwet relwet;
    @Element(name = "HEAT")
    private Heat heat;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTod() {
        return tod;
    }

    public void setTod(String tod) {
        this.tod = tod;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public Phenomena getPhenomena() {
        return phenomena;
    }

    public void setPhenomena(Phenomena phenomena) {
        this.phenomena = phenomena;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Relwet getRelwet() {
        return relwet;
    }

    public void setRelwet(Relwet relwet) {
        this.relwet = relwet;
    }

    public Heat getHeat() {
        return heat;
    }

    public void setHeat(Heat heat) {
        this.heat = heat;
    }
}
