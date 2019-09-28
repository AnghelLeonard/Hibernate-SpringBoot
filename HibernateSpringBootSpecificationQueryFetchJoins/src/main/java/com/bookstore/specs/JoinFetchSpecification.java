package com.bookstore.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class JoinFetchSpecification<Author> implements Specification<Author> {   
    
    private final String genre;

    public JoinFetchSpecification(String genre) {
        this.genre = genre;
    }
            
    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> cquery, CriteriaBuilder cbuilder) {

        // This is needed to support Pageable queries
        // This causes pagination in memory (HHH000104)
        Class clazz = cquery.getResultType();
        if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            return null;
        }
       
        root.fetch("books", JoinType.LEFT);
        cquery.distinct(true);
        
        // in case you need to add order by via Specification
        //cquery.orderBy(cbuilder.asc(root.get("...")));
        
        return cbuilder.equal(root.get("genre"), genre);
    }
}
