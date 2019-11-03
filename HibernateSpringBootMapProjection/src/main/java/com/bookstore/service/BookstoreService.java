package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

    public BookstoreService(AuthorRepository authorRepository,
            EntityManager entityManager) {

        this.authorRepository = authorRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> fetchAgeNameGenre() {
        List<Map<String, Object>> results = authorRepository.fetchAgeNameGenre();

        List<AuthorDto> authors = results.stream()
                .map(result -> new AuthorDto(result))
                .collect(Collectors.toList());
        
        authors.forEach(System.out::println);

        briefOverviewOfPersistentContextContent();
        
        return authors;
    }
    
    @Transactional(readOnly = true)
    public List<AuthorDto> fetchNameEmail() {
        List<Map<String, Object>> results = authorRepository.fetchNameEmail();

        List<AuthorDto> authors = results.stream()
                .map(result -> new AuthorDto(result))
                .collect(Collectors.toList());
        
        authors.forEach(System.out::println);

        briefOverviewOfPersistentContextContent();
        
        return authors;
    }

    private void briefOverviewOfPersistentContextContent() {
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();

        int managedEntities = persistenceContext.getNumberOfManagedEntities();

        System.out.println("\n-----------------------------------");
        System.out.println("Total number of managed entities: " + managedEntities);

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
