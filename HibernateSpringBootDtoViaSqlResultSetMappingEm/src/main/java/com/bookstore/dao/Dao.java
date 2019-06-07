package com.bookstore.dao;

import com.bookstore.dto.AuthorDto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class Dao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AuthorDto> fetchNameAndAge() {

        Query query = entityManager.createNativeQuery(
                "SELECT name, age FROM author", "AuthorDtoMapping");
        List<AuthorDto> authors = query.getResultList();

        return authors;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
