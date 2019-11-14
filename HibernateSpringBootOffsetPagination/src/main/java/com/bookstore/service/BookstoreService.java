package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<Author> fetchNextPage(int page, int size) {

        return authorRepository.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPageByGenre(int page, int size) {

        return authorRepository.fetchByGenre("History",
                PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));
    }
    
    public Page<Author> fetchNextPageByGenreExplicitCount(int page, int size) {

        return authorRepository.fetchByGenreExplicitCount("History",
                PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));
    }
    
    public Page<Author> fetchNextPageByGenreNative(int page, int size) {

        return authorRepository.fetchByGenreNative("History",
                PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));
    }
    
    public Page<Author> fetchNextPageByGenreNativeExplicitCount(int page, int size) {

        return authorRepository.fetchByGenreNativeExplicitCount("History",
                PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));
    }

    public Page<Author> fetchNextPagePageable(Pageable pageable) {

        return authorRepository.findAll(pageable);
    }
}
