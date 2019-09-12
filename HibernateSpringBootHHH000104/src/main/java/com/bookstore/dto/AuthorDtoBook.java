package com.bookstore.dto;

import com.bookstore.entity.Book;
import java.util.List;

public interface AuthorDtoBook {

    public String getName();

    public int getAge();

    public List<Book> getBooks();
}
