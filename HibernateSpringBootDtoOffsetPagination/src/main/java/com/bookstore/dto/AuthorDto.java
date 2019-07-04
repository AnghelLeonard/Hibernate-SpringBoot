package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AuthorDto {

    public String getName();

    public int getAge();
    
    @JsonIgnore
    public long getTotal();
}
