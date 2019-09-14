package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AuthorBookDto {

    public String getName();

    public int getAge();

    public String getTitle();

    public String getIsbn();

    // needed for using COUNT OVER(*)
    @JsonIgnore
    public long getTotal();
}
