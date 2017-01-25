package com.example.ivansv.nskweather.data.model;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;

public class Temperature implements Serializable {
    @Attribute(name = "min")
    private String min;
    @Attribute(name = "max")
    private String max;

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
