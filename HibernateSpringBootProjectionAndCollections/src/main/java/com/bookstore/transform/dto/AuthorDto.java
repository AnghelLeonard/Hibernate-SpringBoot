package com.bookstore.transform.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long authorId;
    private String name;
    private String genre;

    private List<BookDto> books = new ArrayList<>();

    public AuthorDto() {
    }

    public AuthorDto(Long authorId, String name, String genre) {
        this.authorId = authorId;
        this.name = name;
        this.genre = genre;
    }

    public Long getId() {
        return authorId;
    }

    public void setId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public void addBook(BookDto book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "AuthorDto{" + "authorId=" + authorId + ", name=" + name + ", genre=" + genre + '}';
    }
}
