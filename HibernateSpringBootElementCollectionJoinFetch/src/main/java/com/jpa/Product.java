package com.jpa;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }        

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", price=" + price + '}';
    }        
}
