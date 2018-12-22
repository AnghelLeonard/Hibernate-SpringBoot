package com.jpa;

import com.vladmihalcea.hibernate.type.basic.YearMonthIntegerType;
import java.io.Serializable;
import java.time.YearMonth;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(
        typeClass = YearMonthIntegerType.class, // or, YearMonthDateType
        defaultForType = YearMonth.class
)
public class Royalty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float amount;
    private YearMonth payedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public YearMonth getPayedOn() {
        return payedOn;
    }

    public void setPayedOn(YearMonth payedOn) {
        this.payedOn = payedOn;
    }

}
