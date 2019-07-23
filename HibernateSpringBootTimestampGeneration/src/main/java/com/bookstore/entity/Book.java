package com.bookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Book extends BaseEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String isbn;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title
                + ", isbn=" + isbn + ", price=" + price
                + ", created=" + created
                + ", createdBy=" + createdBy
                + ", lastModified=" + lastModified
                + ", lastModifiedBy=" + lastModifiedBy + '}';
    }
}
