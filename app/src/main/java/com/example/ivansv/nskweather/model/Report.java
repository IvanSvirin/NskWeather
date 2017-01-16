package com.example.ivansv.nskweather.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Report {
    @Attribute(name = "type")
    private String type;
    @Element(name = "TOWN")
    private Town town;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
