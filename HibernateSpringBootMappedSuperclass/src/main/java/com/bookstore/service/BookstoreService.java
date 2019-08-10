package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Paperback;
import com.bookstore.entity.Ebook;
import com.bookstore.repository.EbookRepository;
import org.springframework.stereotype.Service;
import com.bookstore.repository.PaperbackRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final PaperbackRepository paperbackRepository;
    private final EbookRepository ebookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            PaperbackRepository paperbackRepository,
            EbookRepository ebookRepository) {

        this.authorRepository = authorRepository;
        this.paperbackRepository = paperbackRepository;
        this.ebookRepository = ebookRepository;
    }

    @Transactional
    public void persistAuthorWithBooks() {

        Author author = new Author();
        author.setName("Alicia Tom");
        author.setAge(38);
        author.setGenre("Anthology");

        Paperback paperback = new Paperback();
        paperback.setIsbn("002-AT");
        paperback.setTitle("The beatles anthology");
        paperback.setSizeIn("7.5 x 1.3 x 9.2");
        paperback.setWeightLbs("2.7");
        paperback.setAuthor(author);

        Ebook ebook = new Ebook();
        ebook.setIsbn("003-AT");
        ebook.setTitle("Anthology myths");
        ebook.setFormat("kindle");
        ebook.setAuthor(author);

        authorRepository.save(author);
        paperbackRepository.save(paperback);
        ebookRepository.save(ebook);
    }

    public void fetchPaperback() {
        Paperback p1 = paperbackRepository.findByTitle("Anthology myths"); // this is a Ebook
        System.out.println(p1); // prints null

        Paperback p2 = paperbackRepository.findByTitle("The beatles anthology"); // this is a Paperback
        System.out.println(p2);
    }

    public void fetchEbook() {
        Ebook e1 = ebookRepository.findByTitle("The beatles anthology"); // this is a Paperback
        System.out.println(e1); // prints null

        Ebook e2 = ebookRepository.findByTitle("Anthology myths"); // this is a Ebook
        System.out.println(e2);
    }

    public void fetchEbookByAuthorId() {
        Ebook ebook = ebookRepository.fetchByAuthorId(1L);
        System.out.println(ebook);
    }

    public void fetchPaperbackByAuthorId() {
        Paperback paperback = paperbackRepository.fetchByAuthorId(1L);
        System.out.println(paperback);
    }
}
