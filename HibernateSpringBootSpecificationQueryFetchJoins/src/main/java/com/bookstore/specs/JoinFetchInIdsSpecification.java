package com.bookstore.specs;

import com.bookstore.entity.Author;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class JoinFetchInIdsSpecification implements Specification<Author> {   
    
    private final List<Long> ids;

    public JoinFetchInIdsSpecification(List<Long> ids) {
        this.ids = ids;
    }
            
    @Override
    public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> cquery, CriteriaBuilder cbuilder) {
                           
        root.fetch("books", JoinType.LEFT);
        cquery.distinct(true);
       
        // in case you need to add order by via Specification
        //cquery.orderBy(cbuilder.asc(root.get("...")));
        
        Expression<String> expression = root.get("id");
        return expression.in(ids);
    }
}
