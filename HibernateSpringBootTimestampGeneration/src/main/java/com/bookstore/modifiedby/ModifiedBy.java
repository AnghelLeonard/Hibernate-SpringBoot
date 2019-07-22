package com.bookstore.modifiedby;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.hibernate.annotations.ValueGenerationType;

@ValueGenerationType(generatedBy = ModifiedByValueGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModifiedBy {
}
