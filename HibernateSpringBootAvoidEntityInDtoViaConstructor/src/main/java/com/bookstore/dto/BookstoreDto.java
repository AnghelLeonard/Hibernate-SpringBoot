package com.bookstore.dto;

import com.bookstore.entity.Author;

public class BookstoreDto {

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
