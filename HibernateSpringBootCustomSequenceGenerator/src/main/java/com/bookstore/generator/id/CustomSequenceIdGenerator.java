package com.bookstore.generator.id;

import java.io.Serializable;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.type.Type;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.springframework.data.mapping.MappingException;

public class CustomSequenceIdGenerator extends SequenceStyleGenerator {

    public static final String PREFIX_PARAM = "prefix";
    public static final String PREFIX_DEFAULT_PARAM = "";
    private String prefix;

    public static final String NUMBER_FORMAT_PARAM = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT_PARAM = "%d";
    private String numberFormat;

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
            Object object) throws HibernateException {
        return prefix + String.format(numberFormat, super.generate(session, object));
    }

    @Override
    public void configure(Type type, Properties params,
            ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        prefix = ConfigurationHelper.getString(
                PREFIX_PARAM, params, PREFIX_DEFAULT_PARAM);
        numberFormat = ConfigurationHelper.getString(
                NUMBER_FORMAT_PARAM, params, NUMBER_FORMAT_DEFAULT_PARAM);
    }
}
