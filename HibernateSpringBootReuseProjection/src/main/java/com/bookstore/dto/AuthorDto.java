package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface AuthorDto {

    public Integer getAge();
    public String getName();
    public String getGenre();
    public String getEmail();
    public String getAddress();
}
