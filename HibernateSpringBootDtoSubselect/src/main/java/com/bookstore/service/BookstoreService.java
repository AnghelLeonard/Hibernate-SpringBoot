package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Book;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.AuthorDtoRepository;

@Service
public class BookstoreService {

    private final AuthorDtoRepository authorDtoRepository;

    public BookstoreService(AuthorDtoRepository authorDtoRepository) {
        this.authorDtoRepository = authorDtoRepository;
    }

    @Transactional(readOnly = true)
    public void fetchAuthorWithBooksByGenre(String genre) {

        List<AuthorDto> authors = authorDtoRepository.findAll();
        for (AuthorDto author : authors) {
            System.out.println("Author: " + author.getName());

            if (author.getGenre().equals(genre)) {
                // lazy loading the books of this author
                Set<Book> books = author.getBooks();
                books.forEach((b) -> System.out.println("Book: " 
                        + b.getTitle() + "(" + b.getIsbn() + ")"));
            }
        }
    }
}
