package com.bookstore.projection;

import com.bookstore.entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;

public interface AuthorInfo {
    
    public Author getAuthor();
    @JsonIgnore
    public long getTotal();
}
