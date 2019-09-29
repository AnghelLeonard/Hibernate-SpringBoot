package com.bookstore.validation;

import com.bookstore.entity.Review;
import java.util.Objects;
import java.util.stream.Stream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JustOneOfManyValidator implements ConstraintValidator<JustOneOfMany, Review> {

    @Override
    public boolean isValid(Review review, ConstraintValidatorContext ctx) {

        return Stream.of(review.getBook(), review.getArticle(), review.getMagazine())
                .filter(Objects::nonNull)
                .count() == 1;
    }

}
