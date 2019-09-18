package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.engine.spi.Status;
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
    public void fetchAuthorReadWriteMode() {
        Author author = authorRepository.findFirstByGenre("Anthology");

        displayInformation("After Fetch", author);

        author.setAge(40);
     
        displayInformation("After Update Entity", author);

        authorRepository.flush();

        displayInformation("After Flush", author);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorReadOnlyMode() {
        Author author = authorRepository.findFirstByGenre("Anthology");

        displayInformation("After Fetch", author);

        author.setAge(40);
     
        displayInformation("After Update Entity", author);

        authorRepository.flush();

        displayInformation("After Flush", author);
    }

    @Transactional
    public void fetchAuthorDtoReadWriteMode() {
        AuthorDto authorDto = authorRepository.findTopByGenre("Anthology");

        System.out.println("Author DTO: " + authorDto.getName() + ", " + authorDto.getAge());

        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();
        System.out.println("No of managed entities : " + persistenceContext.getNumberOfManagedEntities());
    }

    @Transactional(readOnly = true)
    public void fetchAuthorDtoReadOnlyMode() {
        AuthorDto authorDto = authorRepository.findTopByGenre("Anthology");

        System.out.println("Author DTO: " + authorDto.getName() + ", " + authorDto.getAge());

        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();
        System.out.println("No of managed entities : " + persistenceContext.getNumberOfManagedEntities());
    }

    private org.hibernate.engine.spi.PersistenceContext getPersistenceContext() {
        SharedSessionContractImplementor sharedSession = entityManager.unwrap(
                SharedSessionContractImplementor.class
        );

        return sharedSession.getPersistenceContext();
    }

    private void displayInformation(String phase, Author author) {

        System.out.println("\n-------------------------------------");
        System.out.println("Phase:" + phase);
        System.out.println("Entity: " + author);
        System.out.println("-------------------------------------");

        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();
        System.out.println("Has only non read entities : " + persistenceContext.hasNonReadOnlyEntities());

        EntityEntry entityEntry = persistenceContext.getEntry(author);
        Object[] loadedState = entityEntry.getLoadedState();
        Status status = entityEntry.getStatus();

        System.out.println("Entity entry : " + entityEntry);        
        System.out.println("Status:" + status);
        System.out.println("Loaded state: " + Arrays.toString(loadedState));
    }
}
