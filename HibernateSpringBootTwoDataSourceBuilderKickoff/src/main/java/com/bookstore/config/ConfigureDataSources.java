package com.bookstore.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigureDataSources {

    // first database, authorsdb
    @Primary
    @Bean(name = "configAuthorsDb")
    @ConfigurationProperties("app.datasource.ds1")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "dataSourceAuthorsDb")
    @ConfigurationProperties("app.datasource.ds1")
    public HikariDataSource firstDataSource(@Qualifier("configAuthorsDb") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    // second database, booksdb
    @Bean(name = "configBooksDb")
    @ConfigurationProperties("app.datasource.ds2")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "dataSourceBooksDb")
    @ConfigurationProperties("app.datasource.ds2")
    public HikariDataSource secondDataSource(@Qualifier("configBooksDb") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }
}
