package com.bookstore.by;

import com.bookstore.service.UserService;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class CreatedByValueGeneration
        implements AnnotationValueGeneration<CreatedBy> {

    private final ByValueGenerator generator
            = new ByValueGenerator(new UserService());

    @Override
    public void initialize(CreatedBy mby, Class<?> clazz) {
    }

    @Override
    public GenerationTiming getGenerationTiming() {
        return GenerationTiming.ALWAYS;
    }

    @Override
    public ValueGenerator<?> getValueGenerator() {
        return generator;
    }

    @Override
    public boolean referenceColumnInSql() {
        return false;
    }

    @Override
    public String getDatabaseGeneratedReferencedColumnValue() {
        return null;
    }
}
