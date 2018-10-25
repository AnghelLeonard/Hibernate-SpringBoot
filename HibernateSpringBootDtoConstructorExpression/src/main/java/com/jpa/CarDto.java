package com.jpa;

import java.io.Serializable;

public class CarDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String color;

    public CarDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }    

    @Override
    public String toString() {
        return "CarDto{" + "name=" + name + ", color=" + color + '}';
    }        
}
