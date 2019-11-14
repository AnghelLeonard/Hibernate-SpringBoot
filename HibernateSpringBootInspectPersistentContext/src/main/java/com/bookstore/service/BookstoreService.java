package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    @PersistenceContext
    private final EntityManager entityManager;
    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository, EntityManager entityManager) {

        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public void sqlOperations() {
        briefOverviewOfPersistentContextContent();

        Author author = authorRepository.findByName("Joana Nimar");
        briefOverviewOfPersistentContextContent();

        author.getBooks().get(0).setIsbn("not available");
        briefOverviewOfPersistentContextContent();

        authorRepository.delete(author);
        authorRepository.flush();
        briefOverviewOfPersistentContextContent();

        Author newAuthor = new Author();
        newAuthor.setName("Alicia Tom");
        newAuthor.setAge(38);
        newAuthor.setGenre("Anthology");

        Book book = new Book();
        book.setIsbn("001-AT");
        book.setTitle("The book of swords");

        newAuthor.addBook(book); // use addBook() helper

        authorRepository.saveAndFlush(newAuthor);
        briefOverviewOfPersistentContextContent();
    }

    private void briefOverviewOfPersistentContextContent() {

        System.out.println("\n-----------------------------------------------------");
        
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();

        int managedEntities = persistenceContext.getNumberOfManagedEntities();
        Map collections = persistenceContext.getCollectionEntries();

        System.out.println("Total number of managed entities: "
                + managedEntities);

        if (collections != null) {
            System.out.println("Collection entries: " + collections.values());
        } 
        
        System.out.println("-----------------------------------------------------\n");
    }

    private org.hibernate.engine.spi.PersistenceContext getPersistenceContext() {

        SharedSessionContractImplementor sharedSession = entityManager.unwrap(
                SharedSessionContractImplementor.class
        );

        return sharedSession.getPersistenceContext();
    }

}
