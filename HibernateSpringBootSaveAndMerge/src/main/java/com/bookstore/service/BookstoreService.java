package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    @PersistenceContext
    private final EntityManager entityManager;

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository,
            EntityManager entityManager) {

        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
    }

    public Author fetchAuthorById(long id) {

        return authorRepository.findById(id).orElseThrow();
    }

    public void updateAuthorViaMerge(Author author) {

        authorRepository.save(author);
    }

    @Transactional
    public void updateAuthorViaUpdate(Author author) {

        Session session = entityManager.unwrap(Session.class);
        session.update(author);
    }

}
