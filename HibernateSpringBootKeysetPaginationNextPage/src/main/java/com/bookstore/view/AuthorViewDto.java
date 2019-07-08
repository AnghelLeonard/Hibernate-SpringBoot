package com.bookstore.view;

import com.bookstore.dto.AuthorDto;
import java.util.List;

public class AuthorViewDto {

    private final List<AuthorDto> authors;
    private final boolean last;

    public AuthorViewDto(List<AuthorDto> authors, boolean last) {
        this.authors = authors;
        this.last = last;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public boolean isLast() {
        return last;
    }
}
