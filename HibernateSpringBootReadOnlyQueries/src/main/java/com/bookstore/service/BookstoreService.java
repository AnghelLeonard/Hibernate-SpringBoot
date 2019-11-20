package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.Arrays;
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
    public Author fetchAuthorReadWriteMode() {
        System.out.println("Persistent Context before fetching read-write entity:");
        briefOverviewOfPersistentContextContent();

        Author author = authorRepository.findByName("Joana Nimar");

        System.out.println("\n\nPersistent Context after fetching read-write entity:");
        briefOverviewOfPersistentContextContent();

        return author;
    }

    @Transactional(readOnly = true)
    public Author fetchAuthorReadOnlyMode() {
        System.out.println("Persistent Context before fetching read-only entity:");
        briefOverviewOfPersistentContextContent();

        Author author = authorRepository.findByName("Joana Nimar");

        System.out.println("\n\nPersistent Context after fetching read-only entity:");
        briefOverviewOfPersistentContextContent();

        return author;
    }
    
    @Transactional
    public void updateAuthor(Author author) {
                
        authorRepository.save(author);
        
        System.out.println("\n\nPersistent Context after update the entity:");
        briefOverviewOfPersistentContextContent();
    }

    private void briefOverviewOfPersistentContextContent() {
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();

        int managedEntities = persistenceContext.getNumberOfManagedEntities();
        Map collectionEntries = persistenceContext.getCollectionEntries();

        System.out.println("\n-----------------------------------");
        System.out.println("Total number of managed entities: " + managedEntities);
        if (collectionEntries != null) {
            System.out.println("Total number of collection entries: "
                    + (collectionEntries.values().size()));
        }

        Map entities = persistenceContext.getEntitiesByKey();
        entities.forEach((key, value) -> System.out.println(key + ":" + value));

        entities.values().forEach(entry
                -> {
            EntityEntry ee = persistenceContext.getEntry(entry);
            System.out.println(
                    "Entity name: " + ee.getEntityName()
                    + " | Status: " + ee.getStatus()
                    + " | State: " + Arrays.toString(ee.getLoadedState()));
        });

        System.out.println("\n-----------------------------------\n");
    }

    private org.hibernate.engine.spi.PersistenceContext getPersistenceContext() {

        SharedSessionContractImplementor sharedSession = entityManager.unwrap(
                SharedSessionContractImplementor.class
        );

        return sharedSession.getPersistenceContext();
    }
}
