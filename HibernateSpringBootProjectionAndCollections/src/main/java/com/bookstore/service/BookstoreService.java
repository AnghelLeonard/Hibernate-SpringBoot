package com.bookstore.service;

import com.bookstore.spring.dto.AuthorDto;
import com.bookstore.spring.dto.SimpleAuthorDto;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.transform.dto.AuthorTransformer;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final AuthorTransformer authorTransformer;

    @PersistenceContext
    private final EntityManager entityManager;

    public BookstoreService(AuthorRepository authorRepository,
            AuthorTransformer authorTransformer,
            EntityManager entityManager) {

        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
        this.authorTransformer = authorTransformer;
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
    public Set<AuthorDto> fetchAuthorsWithBooksViaJoinFetch() {
        Set<AuthorDto> authors = authorRepository.findByJoinFetch();

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

    @Transactional(readOnly = true)
    public List<Object[]> fetchAuthorsWithBooksViaArrayOfObjects() {
        List<Object[]> authors = authorRepository.findByViaArrayOfObjects();

        System.out.println("\nResult set:");
        authors.forEach(a -> System.out.println(Arrays.toString(a)));

        briefOverviewOfPersistentContextContent();

        return authors;
    }

    @Transactional(readOnly = true)
    public List<com.bookstore.transform.dto.AuthorDto> fetchAuthorsWithBooksViaArrayOfObjectsAndTransformToDto() {
        
        List<Object[]> authors = authorRepository.findByViaArrayOfObjectsWithIds();
        List<com.bookstore.transform.dto.AuthorDto> authorsDto = authorTransformer.transform(authors);

        System.out.println("\nResult set:");
        authors.forEach(a -> System.out.println(Arrays.toString(a)));

        briefOverviewOfPersistentContextContent();

        return authorsDto;
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
