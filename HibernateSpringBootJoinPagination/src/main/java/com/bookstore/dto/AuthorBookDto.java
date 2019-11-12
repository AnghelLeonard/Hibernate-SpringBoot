package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AuthorBookDto {

    public String getName();  // of author
    public int getAge();      // of author
    public String getTitle(); // of book
    public String getIsbn();  // of book

    @JsonIgnore
    public long getTotal();
}
