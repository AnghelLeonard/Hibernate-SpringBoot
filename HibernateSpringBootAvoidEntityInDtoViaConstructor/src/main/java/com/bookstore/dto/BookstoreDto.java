package com.bookstore.dto;

import com.bookstore.entity.Author;
import java.io.Serializable;

public class BookstoreDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Author author;
    private final String title;

    public BookstoreDto(Author author, String title) {
        this.author = author;
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
