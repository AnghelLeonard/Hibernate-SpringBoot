package com.bookstore.service;

import java.util.List;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import com.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> fetchAllAuthorsDesc() {

        return authorRepository.fetchAllDesc();
    }

    public List<Author> fetchAllAuthorsSorted() {

        return authorRepository.fetchAllSorted(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Page<Author> fetchAuthorsPageDesc() {

        return authorRepository.fetchPageDesc(
                PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "name")));
    }

    public Author fetchAuthorByNameAndAge() {

        return authorRepository.fetchByNameAndAge("Joana Nimar", 34);
    }

    public List<Author> fetchAllAuthorsDescNative() {

        return authorRepository.fetchAllDescNative();
    }

    public Author fetchAuthorByNameAndAgeNative() {

        return authorRepository.fetchByNameAndAgeNative("Joana Nimar", 34);
    }
}
