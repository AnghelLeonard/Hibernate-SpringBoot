package com.app.dto;

import java.io.Serializable;

public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    String namet;
    String namem;
    String nameb;

    public CategoryDto(String namet, String namem, String nameb) {
        this.namet = namet;
        this.namem = namem;
        this.nameb = nameb;
    }

    public String getNamet() {
        return namet;
    }

    public void setNamet(String namet) {
        this.namet = namet;
    }

    public String getNamem() {
        return namem;
    }

    public void setNamem(String namem) {
        this.namem = namem;
    }

    public String getNameb() {
        return nameb;
    }

    public void setNameb(String nameb) {
        this.nameb = nameb;
    }        

    @Override
    public String toString() {
        return "CategoryDto{" + "namet=" + namet 
                + ", namem=" + namem + ", nameb=" + nameb + '}';
    }        
}
