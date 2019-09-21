package com.bookstore.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = "com.bookstore.*"
)
@EnableTransactionManagement
public class EntityManagerFactoryConfig {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages(packagesToScan())
                .persistenceUnit("ds-pu")
                .properties(hibernateProperties())
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    protected String[] packagesToScan() {
        return new String[]{
            "com.bookstore.*"
        };
    }

    protected Map<String, Object> hibernateProperties() {
        return new HashMap<String, Object>() {
            {
                put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                put("hibernate.hbm2ddl.auto", "create");
                put("hibernate.integrator_provider",
                        (IntegratorProvider) () -> Collections.singletonList(
                                DatabaseTableMetadataExtractor.EXTRACTOR
                        ));
            }
        };
    }
}
