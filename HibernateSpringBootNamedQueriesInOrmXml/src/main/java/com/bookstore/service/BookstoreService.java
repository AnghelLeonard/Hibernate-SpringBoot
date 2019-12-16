package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import java.util.List;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import com.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> fetchAllAuthorsDesc() {

        return authorRepository.fetchAllDesc();
    }

    public Page<Author> fetchAuthorsPageDesc() {

        return authorRepository.fetchPageDesc(PageRequest.of(0, 2));
    }

    public Author fetchAuthorByNameAndAge() {
        return authorRepository.fetchByNameAndAge("Joana Nimar", 34);
    }

    public List<AuthorDto> fetchAuthorsNamesAndAges() {

        return authorRepository.fetchNameAndAge();
    }
}
