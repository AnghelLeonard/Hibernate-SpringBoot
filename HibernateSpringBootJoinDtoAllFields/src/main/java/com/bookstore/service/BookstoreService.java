package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.EntityKey;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public BookstoreService(AuthorRepository authorRepository,
            EntityManager entityManager) {

        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void fetchAuthorAsReadOnlyEntities() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(a -> System.out.println(a));

        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly = true)
    public void fetchAuthorAsArrayOfObject() {
        List<Object[]> authors = authorRepository.fetchAsArray();
        authors.forEach(a -> System.out.println(Arrays.toString(a)));

        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly = true)
    public void fetchAuthorAsArrayOfObjectColumns() {
        List<Object[]> authors = authorRepository.fetchAsArrayColumns();
        authors.forEach(a -> System.out.println(Arrays.toString(a)));

        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly = true)
    public void fetchAuthorAsArrayOfObjectNative() {
        List<Object[]> authors = authorRepository.fetchAsArrayNative();
        authors.forEach(a -> System.out.println(Arrays.toString(a)));

        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly = true)
    public void fetchAuthorAsArrayOfObjectQueryBuilderMechanism() {
        List<Object[]> authors = authorRepository.findFirstBy();
        authors.forEach(a -> System.out.println(Arrays.toString(a)));

        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly = true)
    public void fetchAuthorAsDtoClass() {
        List<AuthorDto> authors = authorRepository.fetchAsDto();
        authors.forEach(System.out::println);

        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly = true)
    public void fetchAuthorAsDtoClassColumns() {
        List<AuthorDto> authors = authorRepository.fetchAsDtoColumns();
        authors.forEach(a -> System.out.println("Author{id=" + a.getId()
                + ", name=" + a.getName() + ", genre=" + a.getGenre()
                + ", age=" + a.getAge() + "}"));

        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly = true)
    public void fetchAuthorAsDtoClassNative() {
        List<AuthorDto> authors = authorRepository.fetchAsDtoNative();
        authors.forEach(a -> System.out.println("Author{id=" + a.getId()
                + ", name=" + a.getName() + ", genre=" + a.getGenre()
                + ", age=" + a.getAge() + "}"));

        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByGenreAsDtoClassQueryBuilderMechanism() {
        List<AuthorDto> authors = authorRepository.findBy();
        authors.forEach(a -> System.out.println("Author{id=" + a.getId()
                + ", name=" + a.getName() + ", genre=" + a.getGenre()
                + ", age=" + a.getAge() + "}"));

        briefOverviewOfPersistentContextContent();
    }

    private void briefOverviewOfPersistentContextContent() {
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();

        int managedEntities = persistenceContext.getNumberOfManagedEntities();

        System.out.println("\n-----------------------------------");
        System.out.println("Total number of managed entities: " + managedEntities);

        // getEntitiesByKey() will be removed and probably replaced with #iterateEntities() 
        Map<EntityKey, Object> entitiesByKey = persistenceContext.getEntitiesByKey();
        entitiesByKey.forEach((key, value) -> System.out.println(key + ": " + value));

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
