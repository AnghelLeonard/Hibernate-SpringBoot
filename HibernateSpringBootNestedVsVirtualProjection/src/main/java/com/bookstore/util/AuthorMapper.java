package com.bookstore.util;

import com.bookstore.dto.AuthorClassDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    
    public AuthorClassDto buildAuthorDto(String genre, String name) {
        AuthorClassDto authorClassDto = new AuthorClassDto();
        authorClassDto.setName(name);
        authorClassDto.setGenre(genre);
        
        return authorClassDto;
    }
}
