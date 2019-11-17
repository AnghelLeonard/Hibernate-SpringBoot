package com.bookstore.dto;

import java.io.Serializable;

public class BookDto implements Serializable {

    private static final long serialVersionUID = 1L;    

    private Long bookId;
    private String title;    

    public BookDto() {}

    public BookDto(Long bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public Long getId() {
        return bookId;
    }

    public void setId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }        

    @Override
    public String toString() {
        return "BookDto{" + "bookId=" 
                + bookId + ", title=" + title + '}';
    }        
}
