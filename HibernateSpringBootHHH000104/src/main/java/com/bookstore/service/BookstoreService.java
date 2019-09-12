package com.bookstore.service;

import com.bookstore.dto.AuthorBookDto;
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
import com.bookstore.dto.AuthorDtoBook;
import com.bookstore.dto.AuthorDtoBookDto;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Page<Author> fetchAuthorsWithBooksByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        Page<Long> pageOfIds = authorRepository.fetchIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooks(pageOfIds.getContent());
        Page<Author> pageOfAuthors = new PageImpl(listOfAuthors, pageable, pageOfIds.getTotalElements());

        return pageOfAuthors;
    }

    @Transactional
    public Page<AuthorBookDto> fetchAuthorsWithBooksDtoByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));
        Page<AuthorBookDto> pageOfAuthors = authorRepository.fetchWithBooksDto("Anthology", pageable);

        // collection of Author is not managed, but dirty checking works for Author
        // the below line will trigger an UPDATE
        // pageOfAuthors.getContent().get(0).getAuthor().setAge(50);
        return pageOfAuthors;
    }

    @Transactional
    public Page<AuthorDtoBook> fetchAuthorsDtoWithBooksByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));
        Page<AuthorDtoBook> pageOfAuthors = authorRepository.fetchDtoWithBooks("Anthology", pageable);

        // collection of Book is not managed, but dirty checking works for Book
        // the below line will trigger an UPDATE
        // pageOfAuthors.getContent().get(0).getBooks().get(0).setIsbn("not available");
        return pageOfAuthors;
    }

    @Transactional(readOnly = true)
    public Page<AuthorDtoBookDto> fetchAuthorsDtoWithBooksDtoByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));
        Page<AuthorDtoBookDto> pageOfAuthors = authorRepository.fetchDtoWithBooksDto("Anthology", pageable);

        return pageOfAuthors;
    }

    @Transactional(readOnly = true)
    public Page<AuthorDtoBookDto> fetchNativeAuthorsDtoWithBooksDtoByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        List<AuthorDtoBookDto> listOfAuthors
                = authorRepository.fetchNativeDtoWithBooksDto("Anthology", pageable);
        Page<AuthorDtoBookDto> pageOfAuthors
                = new PageImpl(listOfAuthors, pageable,
                        listOfAuthors.isEmpty() ? 0 : listOfAuthors.get(0).getTotal());

        return pageOfAuthors;
    }
}
