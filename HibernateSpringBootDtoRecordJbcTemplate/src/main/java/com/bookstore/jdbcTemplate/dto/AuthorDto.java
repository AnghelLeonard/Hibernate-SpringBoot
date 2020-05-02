package com.bookstore.jdbcTemplate.dto;

import java.util.List;

public record AuthorDto(Long id, String name, int age, List books) {

  public void addBook(BookDto book) {
    books().add(book);
  }
}
