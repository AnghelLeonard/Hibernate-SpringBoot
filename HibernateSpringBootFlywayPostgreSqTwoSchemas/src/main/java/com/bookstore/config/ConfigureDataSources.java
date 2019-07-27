package com.bookstore.config;

import com.bookstore.flyway.propeties.FlywayAuthorProperties;
import com.bookstore.flyway.propeties.FlywayBookProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigureDataSources {

    // first schema, books
    @Primary
    @Bean(name = "configBooksSchema")
    @ConfigurationProperties("app.datasource.ds1")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "configFlywayBooksSchema")
    @ConfigurationProperties("app.flyway.ds1")
    public FlywayBookProperties firstFlywayProperties() {
        return new FlywayBookProperties();
    }

    @Primary
    @FlywayDataSource
    @Bean(name = "flywayBooksSchema", initMethod = "migrate")
    public Flyway firstFlyway(@Qualifier("configFlywayBooksSchema") FlywayBookProperties properties) {
        return Flyway.configure()
                .dataSource(properties.getUrl(), properties.getUser(), properties.getPassword())
                .schemas(properties.getSchema())
                .locations(properties.getLocation())
                .load();
    }

    @Primary
    @Bean(name = "dataSourceBooksSchema")
    @DependsOn("flywayBooksSchema")
    @ConfigurationProperties("app.datasource.ds1")
    public HikariDataSource firstDataSource(@Qualifier("configBooksSchema") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    // second schema, authors
    @Bean(name = "configAuthorsSchema")
    @ConfigurationProperties("app.datasource.ds2")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "configFlywayAuthorsSchema")
    public FlywayAuthorProperties secondFlywayProperties() {
        return new FlywayAuthorProperties();
    }

    @FlywayDataSource
    @Bean(name = "flywayAuthorsSchema", initMethod = "migrate")
    public Flyway secondFlyway(@Qualifier("configFlywayAuthorsSchema") FlywayAuthorProperties properties) {
        return Flyway.configure()
                .dataSource(properties.getUrl(), properties.getUser(), properties.getPassword())
                .schemas(properties.getSchema())
                .locations(properties.getLocation())
                .load();
    }

    @Bean(name = "dataSourceAuthorsSchema")
    @DependsOn("flywayAuthorsSchema")
    @ConfigurationProperties("app.datasource.ds2")
    public HikariDataSource secondDataSource(@Qualifier("configAuthorsSchema") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }
}
