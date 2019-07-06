package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
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

    public List<AuthorDto> fetchNextPageNative(int page, int size) {

        return authorRepository.fetchAllNative(page, size);
    }

    public List<AuthorDto> fetchNextPageJpql(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age"));

        return authorRepository.fetchAllJpql(pageable);
    }
}
