package com.bookstore.dao;

import com.bookstore.entity.Author;
import com.bookstore.entity.Author_;
import com.bookstore.entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class Dao {

    @PersistenceContext
    private EntityManager em;

    public List<Author> fetchAuthors() {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Author> criteria = builder.createQuery(Author.class);
        Root<Author> author = criteria.from(Author.class);
        Join<Author, Book> book = author.join(Author_.BOOKS);

        criteria.where(
                builder.gt(author.get("age"), 40), // this is rendered inline by default
                author.get("genre").in(List.of("Horror", "History")) // this is rendered inline via setting in application.properties
        );

        return em.createQuery(criteria).getResultList();
    }
}
