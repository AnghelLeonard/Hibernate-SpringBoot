package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.AuthorId;
import com.bookstore.entity.Book;
import com.bookstore.entity.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.PublisherRepository;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookstoreService(AuthorRepository authorRepository,
            PublisherRepository publisherRepository) {

        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public void addPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("GreatBooks Ltd");
        publisher.setUrc(92284434);

        publisherRepository.save(publisher);
    }

    @Transactional
    public void addAuthorsWithBooks() {
        
        Publisher publisher = publisherRepository.findByUrc(92284434);

        Author author1 = new Author();
        author1.setId(new AuthorId(publisher, "Alicia Tom"));
        author1.setGenre("Anthology");

        Author author2 = new Author();
        author2.setId(new AuthorId(publisher, "Joana Nimar"));
        author2.setGenre("History");

        Book book1 = new Book();
        book1.setIsbn("001-AT");
        book1.setTitle("The book of swords");

        Book book2 = new Book();
        book2.setIsbn("002-AT");
        book2.setTitle("Anthology of a day");

        Book book3 = new Book();
        book3.setIsbn("003-AT");
        book3.setTitle("Anthology today");

        author1.addBook(book1); // use addBook() helper
        author1.addBook(book2);
        author2.addBook(book3);

        authorRepository.save(author1);
        authorRepository.save(author2);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByName() {
        Author author = authorRepository.fetchByName("Alicia Tom");

        System.out.println(author);
    }

    @Transactional
    public void removeBookOfAuthor() {

        Publisher publisher = publisherRepository.findByUrc(92284434);
        Author author = authorRepository.fetchWithBooks(new AuthorId(publisher, "Alicia Tom"));

        author.removeBook(author.getBooks().get(0));
    }

    @Transactional
    public void removeAuthor() {
        Publisher publisher = publisherRepository.findByUrc(92284434);
        authorRepository.deleteById(new AuthorId(publisher, "Alicia Tom"));
    }
}
