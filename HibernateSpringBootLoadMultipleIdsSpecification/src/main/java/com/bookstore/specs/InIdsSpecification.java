package com.bookstore.specs;

import com.bookstore.entity.Book;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class InIdsSpecification implements Specification<Book> {

    private final List<Long> ids;

    public InIdsSpecification(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, 
            CriteriaQuery<?> cquery, CriteriaBuilder cbuilder) {

        return root.in(ids);

        // or
        // Expression<String> expression = root.get("id");
        // return expression.in(ids);
    }
}
