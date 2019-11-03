package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.dto.AuthorDto;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> fetchAll() {
        List<AuthorDto> authors = authorRepository.fetchAll();

        return authors;
    }
    
    @Transactional(readOnly = true)
    public List<AuthorDto> fetchAgeNameGenre() {
        List<AuthorDto> authors = authorRepository.fetchAgeNameGenre();

        return authors;
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> fetchNameEmail() {
        List<AuthorDto> authors = authorRepository.fetchNameEmail();

        return authors;
    }

}
