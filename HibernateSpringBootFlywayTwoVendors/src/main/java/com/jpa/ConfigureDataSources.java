package com.jpa;

import com.jpa.fllyway.propeties.FlywayDs1Properties;
import com.jpa.fllyway.propeties.FlywayDs2Properties;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
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

    // setting MySQL data source and Flyway migration for "players_db"
    @Bean(name = "configMySql")
    @Primary
    @ConfigurationProperties("app.datasource.ds1")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "configFlywayMySql")
    @Primary
    @ConfigurationProperties("app.flyway.ds1")
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
    public Flyway primaryFlyway(@Qualifier("dataSourceMySql") DataSource primaryDataSource,
            @Qualifier("configFlywayMySql") FlywayDs1Properties properties) {

        return Flyway.configure()
                .dataSource(primaryDataSource)
                .locations(properties.getLocation())
                .load();
    }

    // configure PostgreSQL data source and Flyway migration for "coaches_db"         
    @Bean(name = "configPostgreSql")
    @ConfigurationProperties("app.datasource.ds2")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "configFlywayPostgreSql")
    @ConfigurationProperties("app.flyway.ds2")
    public FlywayDs2Properties secondFlywayProperties() {
        return new FlywayDs2Properties();
    }   

    @Bean(name = "dataSourcePostgreSql")
    @DependsOn("flywayPostgreSql")
    @ConfigurationProperties("app.datasource.ds2")
    public HikariDataSource secondDataSource(
            @Qualifier("configPostgreSql") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }
    
    @FlywayDataSource
    @Bean(name = "flywayPostgreSql", initMethod = "migrate")
    public Flyway secondFlyway(@Qualifier("configFlywayPostgreSql") FlywayDs2Properties properties) {
        return Flyway.configure()
                .dataSource(properties.getUrl(), properties.getUser(), properties.getPassword())
                .schemas(properties.getSchema())
                .locations(properties.getLocation())
                .load();
    }
}
