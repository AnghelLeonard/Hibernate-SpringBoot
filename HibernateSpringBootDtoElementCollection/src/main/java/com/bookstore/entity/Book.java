package com.bookstore.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
  
    private String title;
    private String isbn;
    private String genre;
    private int price;  

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", isbn=" + isbn 
                + ", genre=" + genre + ", price=" + price + '}';
    }   
}
