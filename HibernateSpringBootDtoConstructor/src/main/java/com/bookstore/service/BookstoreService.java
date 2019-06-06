package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import java.util.List;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDto> fetchByGenre() {

        return authorRepository.findByGenre("Anthology");
    }
}
