package com.bookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="paperback_book_id")
public class Paperback extends Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sizeIn;
    private String weightLbs;

    public String getSizeIn() {
        return sizeIn;
    }

    public void setSizeIn(String sizeIn) {
        this.sizeIn = sizeIn;
    }

    public String getWeightLbs() {
        return weightLbs;
    }

    public void setWeightLbs(String weightLbs) {
        this.weightLbs = weightLbs;
    }

    @Override
    public String toString() {
        return super.toString() + ", Paperback{" + "sizeIn=" + sizeIn + ", weightLbs=" + weightLbs + '}';
    }

}
