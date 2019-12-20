package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsertSecondAuthorService {

    private final AuthorRepository authorRepository;

    public InsertSecondAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Calling this method will cause:
    // org.springframework.transaction.NestedTransactionNotSupportedException: 
    // JpaDialect does not support savepoints - check your JPA provider's capabilities
    // This is happening because Hibernate doesn't support nested transactions
    // A solution is to use JdbcTemplate or to switch to a JPA provider that supports nested transactions
    @Transactional(propagation = Propagation.NESTED)
    public void insertSecondAuthor() {

        Author author = new Author();
        author.setName("Alicia Tom");

        authorRepository.save(author);
        
        if(new Random().nextBoolean()) {
            throw new RuntimeException("DummyException");
        }
    }
}
