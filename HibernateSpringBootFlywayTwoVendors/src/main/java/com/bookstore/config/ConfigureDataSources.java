package com.bookstore.config;

import com.bookstore.fllyway.propeties.FlywayDs1Properties;
import com.bookstore.fllyway.propeties.FlywayDs2Properties;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigureDataSources {

    // setting MySQL data source and Flyway migration for "authorsdb"
    @Primary
    @Bean(name = "configMySql")
    @ConfigurationProperties("app.datasource.ds1")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "configFlywayMySql")
    public FlywayDs1Properties firstFlywayProperties() {
        return new FlywayDs1Properties();
    }

    @Primary
    @Bean(name = "dataSourceMySql")
    @ConfigurationProperties("app.datasource.ds1")
    public HikariDataSource firstDataSource(@Qualifier("configMySql") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Primary
    @FlywayDataSource
    @Bean(initMethod = "migrate")
    public Flyway primaryFlyway(@Qualifier("dataSourceMySql") HikariDataSource primaryDataSource,
            @Qualifier("configFlywayMySql") FlywayDs1Properties properties) {

        return Flyway.configure()
                .dataSource(primaryDataSource)
                .locations(properties.getLocation())
                .load();
    }

    // configure PostgreSQL data source and Flyway migration for "booksdb"         
    @Bean(name = "configPostgreSql")
    @ConfigurationProperties("app.datasource.ds2")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "configFlywayPostgreSql")
    public FlywayDs2Properties secondFlywayProperties() {
        return new FlywayDs2Properties();
    }

    @Bean(name = "dataSourcePostgreSql")
    @ConfigurationProperties("app.datasource.ds2")
    public HikariDataSource secondDataSource(@Qualifier("configPostgreSql") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }
 
    @FlywayDataSource
    @Bean(initMethod = "migrate")
    public Flyway secondFlyway(@Qualifier("dataSourcePostgreSql") HikariDataSource secondDataSource,
            @Qualifier("configFlywayPostgreSql") FlywayDs2Properties properties) {
        return Flyway.configure()
                .dataSource(secondDataSource)
                .schemas(properties.getSchema())
                .locations(properties.getLocation())
                .load();
    }
}
