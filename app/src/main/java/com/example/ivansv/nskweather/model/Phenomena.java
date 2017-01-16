package com.example.ivansv.nskweather.model;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

public class Phenomena implements Serializable {
    @Attribute(name = "cloudiness")
    private String cloudiness;
    @Attribute(name = "precipitation")
    private String precipitation;
    @Attribute(name = "rpower")
    private String rpower;
    @Attribute(name = "spower")
    private String spower;

    public String getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(String cloudiness) {
        this.cloudiness = cloudiness;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getRpower() {
        return rpower;
    }

    public void setRpower(String rpower) {
        this.rpower = rpower;
    }

    public String getSpower() {
        return spower;
    }

    public void setSpower(String spower) {
        this.spower = spower;
    }
}
