package com.bookstore.obj;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;  

    private String title;
    private String isbn; 
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }        

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", isbn=" + isbn + ", price=" + price + '}';
    }        
}