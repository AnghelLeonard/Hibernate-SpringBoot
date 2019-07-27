package com.bookstore.config;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "ds2EntityManagerFactory",
        transactionManagerRef = "ds2TransactionManager",
        basePackages = "com.bookstore.ds2"
)
@EnableTransactionManagement
public class SecondEntityManagerFactory {

    @Bean
    public LocalContainerEntityManagerFactoryBean ds2EntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("dataSourceAuthorsDb") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages(packagesToScan())
                .persistenceUnit("ds2-pu")
                .properties(hibernateProperties())
                .build();
    }

    @Bean
    public PlatformTransactionManager ds2TransactionManager(
            @Qualifier("ds2EntityManagerFactory") EntityManagerFactory secondEntityManagerFactory) {
        return new JpaTransactionManager(secondEntityManagerFactory);
    }

    protected String[] packagesToScan() {
        return new String[]{
            "com.bookstore.ds2"
        };
    }

    protected Map<String, String> hibernateProperties() {
        return new HashMap<String, String>() {
            {
                put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");               
            }
        };
    }
}
