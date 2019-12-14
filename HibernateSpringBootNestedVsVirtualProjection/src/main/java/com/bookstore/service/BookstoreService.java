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
import org.hibernate.engine.spi.EntityKey;

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

    @Transactional(readOnly = true)
    public List<Object[]> fetchBooksWithAuthorsViaArrayOfObjects() {
        List<Object[]> books = bookRepository.findByViaQueryArrayOfObjects();

        System.out.println("\nResult set:");
        books.forEach(b -> System.out.println(Arrays.toString(b)));

        briefOverviewOfPersistentContextContent();

        return books;
    }

    private void briefOverviewOfPersistentContextContent() {
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();

        int managedEntities = persistenceContext.getNumberOfManagedEntities();
        int collectionEntriesSize = persistenceContext.getCollectionEntriesSize();

        System.out.println("\n-----------------------------------");
        System.out.println("Total number of managed entities: " + managedEntities);
        System.out.println("Total number of collection entries: " + collectionEntriesSize + "\n");

        // getEntitiesByKey() will be removed and probably replaced with #iterateEntities() 
        Map<EntityKey, Object> entitiesByKey = persistenceContext.getEntitiesByKey();
        entitiesByKey.forEach((key, value) -> System.out.println(key + ":" + value));

        for (Object entry : entitiesByKey.values()) {
            EntityEntry ee = persistenceContext.getEntry(entry);
            System.out.println(
                    "Entity name: " + ee.getEntityName()
                    + " | Status: " + ee.getStatus()
                    + " | State: " + Arrays.toString(ee.getLoadedState()));
        };

        System.out.println("\n-----------------------------------\n");
    }

    private org.hibernate.engine.spi.PersistenceContext getPersistenceContext() {

        SharedSessionContractImplementor sharedSession = entityManager.unwrap(
                SharedSessionContractImplementor.class
        );

        return sharedSession.getPersistenceContext();
    }

}
