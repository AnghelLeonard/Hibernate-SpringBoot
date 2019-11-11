package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.interfacebased.dto.AuthorDto;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import com.bookstore.interfacebased.dto.MirrorAuthorDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
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

    @Transactional(readOnly=true)
    public void fetchAuthorsAsReadOnlyEntities() {

        List<Author> authors = authorRepository.fetchReadOnlyEntities();

        authors.forEach(a -> System.out.println(a + " | " + a.getBooks()));
        
        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly=true)
    public void fetchAuthorsMirrorInterface() {

        List<MirrorAuthorDto> authors = authorRepository.fetchMirrorInterface();

        authors.forEach(a -> System.out.println(a + " | " + a.getBooks()));
        
        briefOverviewOfPersistentContextContent();
    }

    @Transactional(readOnly=true)
    public void fetchAuthorsDtoInterface() {

        List<AuthorDto> authors = authorRepository.fetchDtoInterface();

        authors.forEach(a -> System.out.println(a + " | " + a.getBooks()));
        
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
                    + (managedEntities - collectionEntries.values().size()));
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
