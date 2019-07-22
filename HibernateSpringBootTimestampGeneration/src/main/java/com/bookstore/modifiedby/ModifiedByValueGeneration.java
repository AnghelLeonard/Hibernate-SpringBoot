package com.bookstore.modifiedby;

import org.hibernate.Session;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

public class ModifiedByValueGeneration
        implements AnnotationValueGeneration<ModifiedBy> {

    private final ValueGenerator<String> generator
            = (Session session, Object owner) -> "leonarda" 

            // lookup for UserService (typical in Spring Security)
            // UserService userService = ((SessionImplementor) session).getFactory()
            //        .getServiceRegistry()
            //        .getService(UserService.class);
            // return userService.getCurrentUserName();
            ;

    @Override
    public void initialize(ModifiedBy arg0, Class<?> arg1) {
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
