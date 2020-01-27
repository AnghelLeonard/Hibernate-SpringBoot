package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Paperback;
import com.bookstore.entity.Ebook;
import com.bookstore.repository.EbookRepository;
import org.springframework.stereotype.Service;
import com.bookstore.repository.PaperbackRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PaperbackRepository paperbackRepository;
    private final EbookRepository ebookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository,
            PaperbackRepository paperbackRepository,
            EbookRepository ebookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.paperbackRepository = paperbackRepository;
        this.ebookRepository = ebookRepository;
    }

    public void persistAuthorWithBooks() {

        Author author = new Author();
        author.setName("Alicia Tom");
        author.setAge(38);
        author.setGenre("Anthology");

        Book book = new Book();
        book.setIsbn("001-AT");
        book.setTitle("The book of swords");

        Paperback paperback = new Paperback();
        paperback.setIsbn("002-AT");
        paperback.setTitle("The beatles anthology");
        paperback.setSizeIn("7.5 x 1.3 x 9.2");
        paperback.setWeightLbs("2.7");

        Ebook ebook = new Ebook();
        ebook.setIsbn("003-AT");
        ebook.setTitle("Anthology myths");
        ebook.setFormat("kindle");

        author.addBook(book);
        author.addBook(paperback);
        author.addBook(ebook);

        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public void fetchBookByTitle() {
        Book book = bookRepository.findByTitle("The book of swords");
        System.out.println(book);

        Paperback paperback = paperbackRepository.findByTitle("The beatles anthology");
        System.out.println(paperback);

        Ebook ebook = ebookRepository.findByTitle("Anthology myths");
        System.out.println(ebook);
    }

    @Transactional(readOnly = true)
    public void fetchBookByIsbn() {
        Book book = bookRepository.fetchByIsbn("001-AT");
        System.out.println(book);

        Paperback paperback = paperbackRepository.fetchByIsbn("002-AT");
        System.out.println(paperback);

        Ebook ebook = ebookRepository.fetchByIsbn("003-AT");
        System.out.println(ebook);
    }
}
