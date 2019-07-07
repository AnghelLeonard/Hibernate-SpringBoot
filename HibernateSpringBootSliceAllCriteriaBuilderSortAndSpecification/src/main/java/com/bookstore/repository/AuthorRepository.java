package com.bookstore.repository;

import com.bookstore.entity.Author;
import com.bookstore.repository.impl.SlicePagingRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository extends SlicePagingRepositoryImplementation<Author> {

    public AuthorRepository() {
        super(Author.class);
    }
}
