package com.jpa;

public class CarDtoWithSetters {
    
    private String name;
    private String color;    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }          

    @Override
    public String toString() {
        return "CarDtoWithSetters{" + "name=" + name + ", color=" + color + '}';
    }        
}
