package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Page<Author> fetchPageOfAuthorsWithBooksByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        Page<Long> pageOfIds = authorRepository.fetchPageOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooks(pageOfIds.getContent());
        Page<Author> pageOfAuthors = new PageImpl(listOfAuthors, pageable, pageOfIds.getTotalElements());

        return pageOfAuthors;
    }

    @Transactional
    public Slice<Author> fetchSliceOfAuthorsWithBooksByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        Slice<Long> pageOfIds = authorRepository.fetchSliceOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooks(pageOfIds.getContent());
        Slice<Author> sliceOfAuthors = new SliceImpl(listOfAuthors, pageable, pageOfIds.hasNext());

        return sliceOfAuthors;
    }

    @Transactional
    public List<Author> fetchListOfAuthorsWithBooksByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        List<Long> listOfIds = authorRepository.fetchListOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooks(listOfIds);

        return listOfAuthors;
    }
}
