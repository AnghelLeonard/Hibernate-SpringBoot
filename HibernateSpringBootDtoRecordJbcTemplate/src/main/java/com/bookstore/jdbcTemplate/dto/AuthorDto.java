package com.bookstore.jdbcTemplate.dto;

import java.util.List;
import java.io.Serializable;

public record AuthorDto(Long id, String name, int age, List books) implements Serializable {

  public void addBook(BookDto book) {
    books().add(book);
  }
}
