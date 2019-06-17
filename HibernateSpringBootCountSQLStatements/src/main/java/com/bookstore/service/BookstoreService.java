package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertDeleteCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertUpdateCount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void authorOperationsWithoutTransactional() {
        Author author = new Author();

        author.setName("Mark Janel");
        author.setGenre("Anthology");
        author.setAge(23);

        SQLStatementCountValidator.reset();

        authorRepository.save(author);   // 1 insert
        author.setGenre("History");
        authorRepository.save(author);   // 1 update
        authorRepository.delete(author); // 1 delete

        // at this point there is no transaction running
        // a total of 5 statements, not very good
        assertInsertCount(1);
        assertUpdateCount(1);
        assertDeleteCount(1);
        assertSelectCount(2);
    }

    @Transactional
    public void authorOperationsWithTransactional() {
        Author author = new Author();

        author.setName("Mark Janel");
        author.setGenre("Anthology");
        author.setAge(23);

        authorRepository.save(author);   // 1 insert
        author.setGenre("History");
        authorRepository.save(author);   // update not triggered since a delete follows
        authorRepository.delete(author); // 1 delete        
    }

}
