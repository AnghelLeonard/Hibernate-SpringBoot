package com.bookstore.interfacebased.dto;

import java.util.List;

public interface MirrorAuthorDto {

    public Long getId();
    public String getName();
    public String getGenre();
    public int getAge();
    public List<MirrorBookDto> getBooks();
}
