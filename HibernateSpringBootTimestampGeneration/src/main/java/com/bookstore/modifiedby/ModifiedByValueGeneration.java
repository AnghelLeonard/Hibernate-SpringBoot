package com.bookstore.modifiedby;

import com.bookstore.service.UserService;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class ModifiedByValueGeneration
        implements AnnotationValueGeneration<ModifiedBy> {

    private final ModifiedByValueGenerator generator
            = new ModifiedByValueGenerator(new UserService());

    @Override
    public void initialize(ModifiedBy mby, Class<?> clazz) {
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
