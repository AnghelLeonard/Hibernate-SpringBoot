package com.bookstore.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {JustOneOfManyValidator.class})
public @interface JustOneOfMany {
 
    String message() default "A review can be associated with either a book, a magazine or an article";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
