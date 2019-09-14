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
import com.bookstore.dto.AuthorBookDto;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Page<Author> fetchAuthorsWithBooksByGenre1(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        Page<Long> pageOfIds = authorRepository.fetchPageOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooks(pageOfIds.getContent());
        Page<Author> pageOfAuthors = new PageImpl(listOfAuthors, pageable, pageOfIds.getTotalElements());

        return pageOfAuthors;
    }

    @Transactional
    public Slice<Author> fetchAuthorsWithBooksByGenre2(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        Slice<Long> pageOfIds = authorRepository.fetchSliceOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooks(pageOfIds.getContent());
        Slice<Author> sliceOfAuthors = new SliceImpl(listOfAuthors, pageable, pageOfIds.hasNext());

        return sliceOfAuthors;
    }

    @Transactional(readOnly = true)
    public Page<AuthorBookDto> fetchAuthorsDtoWithBooksDtoByGenre1(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));
        Page<AuthorBookDto> pageOfAuthors = authorRepository.fetchPageOfDtoWithBooksDto("Anthology", pageable);

        return pageOfAuthors;
    }

    @Transactional(readOnly = true)
    public Slice<AuthorBookDto> fetchAuthorsDtoWithBooksDtoByGenre2(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));
        Slice<AuthorBookDto> sliceOfAuthors = authorRepository.fetchSliceOfDtoWithBooksDto("Anthology", pageable);

        return sliceOfAuthors;
    }

    @Transactional(readOnly = true)
    public Page<AuthorBookDto> fetchNativeAuthorsDtoWithBooksDtoByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        List<AuthorBookDto> listOfAuthors
                = authorRepository.fetchNativeDtoWithBooksDto("Anthology", pageable);
        Page<AuthorBookDto> pageOfAuthors
                = new PageImpl(listOfAuthors, pageable,
                        listOfAuthors.isEmpty() ? 0 : listOfAuthors.get(0).getTotal());

        return pageOfAuthors;
    }

    @Transactional(readOnly = true)
    public List<AuthorBookDto> fetchAuthorsNativeDtoWithBooksDtoViaDenseRank(int start, int end) {

        List<AuthorBookDto> listOfAuthors
                = authorRepository.fetchNativeDtoWithBooksDtoViaDenseRank("Anthology", start, end);

        return listOfAuthors;
    }
}
