package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.dto.SimpleAuthorDto;
import com.bookstore.repository.AuthorRepository;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public BookstoreService(AuthorRepository authorRepository, EntityManager entityManager) {
        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> fetchAuthorsWithBooksQueryBuilderMechanism() {
        List<AuthorDto> authors = authorRepository.findBy();

        System.out.println("\nResult set:");
        authors.forEach(a -> {
            System.out.println("\n\n" + a.getName() + ", " + a.getGenre());
            a.getBooks().forEach(b -> System.out.print(b.getTitle() + ", "));
        });

        briefOverviewOfPersistentContextContent();

        return authors;
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> fetchAuthorsWithBooksViaQuery() {
        List<AuthorDto> authors = authorRepository.findByViaQuery();

        System.out.println("\nResult set:");
        authors.forEach(a -> {
            System.out.println("\n\n" + a.getName() + ", " + a.getGenre());
            a.getBooks().forEach(b -> System.out.print(b.getTitle() + ", "));
        });

        briefOverviewOfPersistentContextContent();

        return authors;
    }

    @Transactional(readOnly = true)
    public List<SimpleAuthorDto> fetchAuthorsWithBooksViaQuerySimpleDto() {
        List<SimpleAuthorDto> authors = authorRepository.findByViaQuerySimpleDto();

        System.out.println("\nResult set:");
        authors.forEach(a -> {
            System.out.println(a.getName() + ", " + a.getGenre() + ", " + a.getTitle());
        });

        briefOverviewOfPersistentContextContent();

        return authors;
    }

    private void briefOverviewOfPersistentContextContent() {
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();

        int managedEntities = persistenceContext.getNumberOfManagedEntities();
        Map collectionEntries = persistenceContext.getCollectionEntries();

        System.out.println("\n-----------------------------------");
        System.out.println("Total number of managed entities: " + managedEntities);
        if (collectionEntries != null) {
            System.out.println("Total number of collection entries: "
                    + (managedEntities - collectionEntries.values().size()));
        } else {
            System.out.println("No managed collections");
        }

        Map entities = persistenceContext.getEntitiesByKey();
        entities.forEach((key, value) -> System.out.println(key + ":" + value));

        entities.values().forEach(entry
                -> {
            EntityEntry ee = persistenceContext.getEntry(entry);
            System.out.println(
                    "Entity name: " + ee.getEntityName()
                    + " | Status" + ee.getStatus()
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
