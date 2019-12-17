package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Slice<Author> fetchNextSlice(int page, int size) {

        return authorRepository.fetchAll(PageRequest.of(page, size,
                Sort.by(Sort.Direction.ASC, "age")));
    }

    public Slice<AuthorDto> fetchNextSliceDto(int page, int size) {

        return authorRepository.fetchAllDto(PageRequest.of(page, size,
                Sort.by(Sort.Direction.ASC, "age")));
    }
}
