package com.bookstore.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("2")
public class Ebook extends Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return super.toString() + ", Ebook{" + "format=" + format + '}';
    }

}
