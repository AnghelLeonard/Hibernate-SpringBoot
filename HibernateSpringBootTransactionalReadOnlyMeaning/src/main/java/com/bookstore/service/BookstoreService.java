package com.bookstore.service;

import com.bookstore.AuthorDto;
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
        Author author = authorRepository.findById(1L).orElseThrow();

        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();
        System.out.println("Has only non read entities : " + persistenceContext.hasNonReadOnlyEntities());

        EntityEntry entityEntryAfterFetch = persistenceContext.getEntry(author);
        Object[] loadedStateAfterFetch = entityEntryAfterFetch.getLoadedState();
        Status statusAfterFetch = entityEntryAfterFetch.getStatus();

        displayInformation("After fetch", author, statusAfterFetch, loadedStateAfterFetch);
        System.out.println("Entity entry : " + entityEntryAfterFetch);

        author.setAge(40);
        author.setGenre("Horror");

        EntityEntry entityEntryAfterUpdate = persistenceContext.getEntry(author);
        Object[] loadedStateAfterUpdate = entityEntryAfterUpdate.getLoadedState();
        Status statusAfterUpdate = entityEntryAfterUpdate.getStatus();

        displayInformation("After update", author, statusAfterUpdate, loadedStateAfterUpdate);
        System.out.println("Entity entry : " + entityEntryAfterUpdate);

        authorRepository.flush();

        EntityEntry entityEntryAfterFlush = persistenceContext.getEntry(author);
        Object[] loadedStateAfterFlush = entityEntryAfterFlush.getLoadedState();
        Status statusAfterFlush = entityEntryAfterFlush.getStatus();

        displayInformation("After flush", author, statusAfterFlush, loadedStateAfterFlush);
        System.out.println("Entity entry : " + entityEntryAfterFlush);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorReadOnlyMode() {
        Author author = authorRepository.findById(1L).orElseThrow();

        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();
        System.out.println("Has only non read entities : " + persistenceContext.hasNonReadOnlyEntities());

        EntityEntry entityEntryAfterFetch = persistenceContext.getEntry(author);
        Object[] loadedStateAfterFetch = entityEntryAfterFetch.getLoadedState();
        Status statusAfterFetch = entityEntryAfterFetch.getStatus();
        
        displayInformation("After fetch", author, statusAfterFetch, loadedStateAfterFetch);
        System.out.println("Entity entry : " + entityEntryAfterFetch);        

        author.setAge(40);
        author.setGenre("Horror");

        EntityEntry entityEntryAfterUpdate = persistenceContext.getEntry(author);        
        Object[] loadedStateAfterUpdate = entityEntryAfterUpdate.getLoadedState();
        Status statusAfterUpdate = entityEntryAfterUpdate.getStatus();

        displayInformation("After update", author, statusAfterUpdate, loadedStateAfterUpdate);
        System.out.println("Entity entry : " + entityEntryAfterUpdate);

        authorRepository.flush();

        EntityEntry entityEntryAfterFlush = persistenceContext.getEntry(author);
        Object[] loadedStateAfterFlush = entityEntryAfterFlush.getLoadedState();
        Status statusAfterFlush = entityEntryAfterFlush.getStatus();

        displayInformation("After flush", author, statusAfterFlush, loadedStateAfterFlush);
        System.out.println("Entity entry : " + entityEntryAfterFlush);
    }
    
    @Transactional
    public void fetchAuthorDtoReadWriteMode() {
        AuthorDto authorDto = authorRepository.findFirstByGenre("Anthology");

        System.out.println("Author DTO: " + authorDto.getName() + ", " + authorDto.getAge());
        
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getPersistenceContext();       
        System.out.println("No of managed entities : " + persistenceContext.getNumberOfManagedEntities());
    }
    
    @Transactional(readOnly = true)
    public void fetchAuthorDtoReadOnlyMode() {
        AuthorDto authorDto = authorRepository.findFirstByGenre("Anthology");

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

    private void displayInformation(String phase, Author author, Status status, Object[] loadedState) {
        System.out.println("\n-------------------------------------");
        System.out.println("Phase:" + phase);
        System.out.println("Entity: " + author);        
        System.out.println("Status:" + status);
        System.out.println("Loaded state: " + Arrays.toString(loadedState));
    }        
}
