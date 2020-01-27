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

    @Transactional(readOnly = true)
    public void fetchBookByTitle() {
        Paperback paperback = paperbackRepository.findByTitle("The beatles anthology");
        System.out.println(paperback);

        Ebook ebook = ebookRepository.findByTitle("Anthology myths");
        System.out.println(ebook);
    }

    @Transactional(readOnly = true)
    public void fetchBookByIsbn() {
        Paperback paperback = paperbackRepository.fetchByIsbn("002-AT");
        System.out.println(paperback);

        Ebook ebook = ebookRepository.fetchByIsbn("003-AT");
        System.out.println(ebook);
    }
}
