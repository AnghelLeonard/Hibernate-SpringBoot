package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class BookstoreService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // benefit of the global TransactionProfiler
    @Transactional
    public void updateAuthor1() {
        Author author = authorRepository.findById(1L).orElseThrow();

        author.setAge(49);
    }

    // benefit of the global TransactionProfiler and local profiler
    @Transactional
    public void updateAuthor2() {

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
            @Override
            public void afterCompletion(int status) {
                logger.info("After completion (method) ...");
            }

            @Override
            public void afterCommit() {
                logger.info("After commit (method) ...");
            }

            @Override
            public void beforeCompletion() {
                logger.info("Before completion (method) ...");
            }

            @Override
            public void beforeCommit(boolean readOnly) {
                logger.info("Before commit (method) ...");
            }
        });

        Author author = authorRepository.findById(1L).orElseThrow();

        author.setAge(51);
    }
}
