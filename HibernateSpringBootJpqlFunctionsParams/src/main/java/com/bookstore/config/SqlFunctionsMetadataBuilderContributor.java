package com.bookstore.config;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class SqlFunctionsMetadataBuilderContributor
        implements MetadataBuilderContributor {

    @Override
    public void contribute(
            MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction(
                "concat_ws",
                new SQLFunctionTemplate(
                        StandardBasicTypes.STRING,
                        "concat_ws('  ', ?1, ?2, ?3, ?4)"
                )
        );
    }

}
