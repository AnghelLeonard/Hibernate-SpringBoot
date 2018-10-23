package com.jpa;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Subscriber extends TeamCup implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean freeOfTax;
    private int specialRanking;
    private boolean requiredId;

    public boolean isFreeOfTax() {
        return freeOfTax;
    }

    public void setFreeOfTax(boolean freeOfTax) {
        this.freeOfTax = freeOfTax;
    }

    public int getSpecialRanking() {
        return specialRanking;
    }

    public void setSpecialRanking(int specialRanking) {
        this.specialRanking = specialRanking;
    }

    public boolean isRequiredId() {
        return requiredId;
    }

    public void setRequiredId(boolean requiredId) {
        this.requiredId = requiredId;
    }
            
}
