package com.bookstore.service;

import static com.bookstore.builder.Condition.LogicalPointerType.AND;
import static com.bookstore.builder.Condition.LogicalPointerType.END;
import static com.bookstore.builder.Condition.OperationType.EQUAL;
import static com.bookstore.builder.Condition.OperationType.GREATER_THAN;
import static com.bookstore.builder.Condition.OperationType.LESS_THAN;
import com.bookstore.builder.SpecificationBuilder;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void fetchAuthors() {
        SpecificationBuilder<Author> specBuilder = new SpecificationBuilder();

        Specification<Author> specAuthor = specBuilder
                .with("age", "40", GREATER_THAN, AND)
                .with("genre", "Anthology", EQUAL, END)
                .build();

        List<Author> authors = authorRepository.findAll(specAuthor);

        System.out.println(authors);
    }

    public void fetchBooksPage(int page, int size) {
        SpecificationBuilder<Book> specBuilder = new SpecificationBuilder();

        Specification<Book> specBook = specBuilder
                .with("price", "60", LESS_THAN, END)
                .build();

        Pageable pageable = PageRequest.of(page, size,
                new Sort(Sort.Direction.ASC, "title"));

        Page<Book> books = bookRepository.findAll(specBook, pageable);

        System.out.println(books);
        books.forEach(System.out::println);
    }
}
