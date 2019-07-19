package com.bookstore.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.NaturalId;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int price;

    @NaturalId(mutable = false)
    @Column(nullable = false, updatable = false, unique = true, length = 50)
    private String isbn;    

    // @NaturalId(mutable = false)
    // @Column(nullable = false, updatable = false, unique = true)
    // private Long sku;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /*
    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }
    */

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        
        if (getClass() != o.getClass()) {
            return false;
        }
        
        Book other = (Book) o;
        return Objects.equals(isbn, other.getIsbn());
        // including sku 
        // return Objects.equals(isbn, other.getIsbn())
        //        && Objects.equals(sku, other.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
        // including sku
        // return Objects.hash(isbn, sku);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", isbn=" + isbn + ", price=" + price + '}';
        // including sku
        //return "Book{" + "id=" + id + ", title=" + title 
        //         + ", isbn=" + isbn + ", price=" + price + ", sku=" + sku + '}';
    }
}
