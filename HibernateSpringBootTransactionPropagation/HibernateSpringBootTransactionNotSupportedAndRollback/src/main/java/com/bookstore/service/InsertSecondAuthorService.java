package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsertSecondAuthorService {

    private final AuthorRepository authorRepository;

    public InsertSecondAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertSecondAuthor() {

        Author author = new Author();
        author.setName("Alicia Tom");

        authorRepository.saveAndFlush(author); // I even flush the insert!

        if (new Random().nextBoolean()) {
            throw new RuntimeException("DummyException: this should cause "
                    + "rollback of the insert triggered in insertFirstAuthor() !");
        }
    }
}
