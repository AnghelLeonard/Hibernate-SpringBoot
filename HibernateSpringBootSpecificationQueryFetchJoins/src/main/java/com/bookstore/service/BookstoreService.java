package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.specs.JoinFetchInIdsSpecification;
import com.bookstore.specs.JoinFetchSpecification;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<Author> fetchViaJoinFetchSpecification(int page, int size) {

        Pageable pageable = PageRequest.of(page, size,
                new Sort(Sort.Direction.ASC, "name"));

        Page<Author> pageOfAuthors = authorRepository
                .findAll(new JoinFetchSpecification("Anthology"), pageable);

        return pageOfAuthors;
    }

    @Transactional(readOnly = true)
    public Page<Author> fetchViaJoinFetchInIdsSpecification(int page, int size) {

        Pageable pageable = PageRequest.of(page, size,
                new Sort(Sort.Direction.ASC, "name"));

        Page<Long> pageOfIds = authorRepository.fetchPageOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.findAll(
                new JoinFetchInIdsSpecification(pageOfIds.getContent()));
        Page<Author> pageOfAuthors = new PageImpl(
                listOfAuthors, pageable, pageOfIds.getTotalElements());
        
        return pageOfAuthors;
    }
}
