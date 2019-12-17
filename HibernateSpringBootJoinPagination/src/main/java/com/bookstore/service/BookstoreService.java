package com.bookstore.service;

import com.bookstore.dto.AuthorBookDto;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public Page<AuthorBookDto> fetchPageOfAuthorsWithBooksDtoByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<AuthorBookDto> pageOfAuthors = authorRepository.fetchPageOfDto("Anthology", pageable);

        return pageOfAuthors;
    }

    @Transactional(readOnly = true)
    public Slice<AuthorBookDto> fetchSliceOfAuthorsWithBooksDtoByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Slice<AuthorBookDto> sliceOfAuthors = authorRepository.fetchSliceOfDto("Anthology", pageable);

        return sliceOfAuthors;
    }
    
    @Transactional(readOnly = true)
    public List<AuthorBookDto> fetchListOfAuthorsWithBooksDtoByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        List<AuthorBookDto> listOfAuthors = authorRepository.fetchListOfDto("Anthology", pageable);

        return listOfAuthors;
    }

    @Transactional(readOnly = true)
    public Page<AuthorBookDto> fetchPageOfAuthorsWithBooksDtoByGenreNative(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));

        List<AuthorBookDto> listOfAuthors
                = authorRepository.fetchListOfDtoNative("Anthology", pageable);
        Page<AuthorBookDto> pageOfAuthors
                = new PageImpl(listOfAuthors, pageable,
                        listOfAuthors.isEmpty() ? 0 : listOfAuthors.get(0).getTotal());

        return pageOfAuthors;
    }

    @Transactional(readOnly = true)
    public List<AuthorBookDto> fetchListOfAuthorsWithBooksDtoNativeDenseRank(int start, int end) {

        List<AuthorBookDto> listOfAuthors
                = authorRepository.fetchListOfDtoNativeDenseRank("Anthology", start, end);

        return listOfAuthors;
    }
}
