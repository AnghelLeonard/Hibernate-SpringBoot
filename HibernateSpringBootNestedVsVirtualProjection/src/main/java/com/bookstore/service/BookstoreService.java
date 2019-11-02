package com.bookstore.service;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.SimpleBookDto;
import com.bookstore.dto.VirtualBookDto;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.BookRepository;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public BookstoreService(BookRepository bookRepository, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<BookDto> fetchBooksWithAuthorsQueryBuilderMechanism() {
        List<BookDto> books = bookRepository.findBy();

        System.out.println("\nResult set:");
        books.forEach(b -> System.out.println(b.getTitle() + ", "
                + b.getAuthor().getName() + ", " + b.getAuthor().getGenre()));

        briefOverviewOfPersistentContextContent();

        return books;
    }

    @Transactional(readOnly = true)
    public List<BookDto> fetchBooksWithAuthorsViaQuery() {
        List<BookDto> books = bookRepository.findByViaQuery();

        System.out.println("\nResult set:");
        books.forEach(b -> System.out.println(b.getTitle() + ", "
                + b.getAuthor().getName() + ", " + b.getAuthor().getGenre()));

        briefOverviewOfPersistentContextContent();

        return books;
    }

    @Transactional(readOnly = true)
    public List<SimpleBookDto> fetchBooksWithAuthorsViaQuerySimpleDto() {
        List<SimpleBookDto> books = bookRepository.findByViaQuerySimpleDto();

        System.out.println("\nResult set:");
        books.forEach(b -> System.out.println(b.getTitle() + ", "
                + b.getName() + ", " + b.getGenre()));

        briefOverviewOfPersistentContextContent();

        return books;
    }

    @Transactional(readOnly = true)
    public List<VirtualBookDto> fetchBooksWithAuthorsViaQueryVirtualDto() {
        List<VirtualBookDto> books = bookRepository.findByViaQueryVirtualDto();

        System.out.println("\nResult set:");
        books.forEach(b -> System.out.println(b.getTitle() + ", "
                + b.getAuthor().getName() + ", " + b.getAuthor().getGenre()));

        briefOverviewOfPersistentContextContent();

        return books;
    }

    private void briefOverviewOfPersistentContextContent() {
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();

        int managedEntities = persistenceContext.getNumberOfManagedEntities();
        Map collections = persistenceContext.getCollectionEntries();

        System.out.println("\n-----------------------------------");
        System.out.println("Total number of managed entities: " + managedEntities);
        if (collections != null) {
            System.out.println("Total number of managed collections: "
                    + (managedEntities - collections.values().size()));
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
