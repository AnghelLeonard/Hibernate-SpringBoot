package com.jpa;

import com.jpa.flyway.propeties.FlywayCoachesProperties;
import com.jpa.flyway.propeties.FlywayPlayersProperties;
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

    // first schema, players_db
    
    @Primary
    @Bean(name = "configPlayersdb")    
    @ConfigurationProperties("app.datasource.ds1")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Primary
    @Bean(name = "configFlywayPlayersdb")    
    @ConfigurationProperties("app.flyway.ds1")
    public FlywayPlayersProperties firstFlywayProperties() {
        return new FlywayPlayersProperties();
    }
    
    @Primary
    @FlywayDataSource
    @Bean(name = "flywayPlayersdb", initMethod = "migrate")
    public Flyway firstFlyway(@Qualifier("configFlywayPlayersdb") FlywayPlayersProperties properties) {
        return Flyway.configure()
                .dataSource(properties.getUrl(), properties.getUser(), properties.getPassword())
                .schemas(properties.getSchema())
                .locations(properties.getLocation())
                .load();
    }
  
    @Primary
    @Bean(name = "dataSourcePlayersdb")
    @DependsOn("flywayPlayersdb")    
    @ConfigurationProperties("app.datasource.ds1")
    public HikariDataSource firstDataSource(@Qualifier("configPlayersdb") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    // second schema, coaches_db
    
    @Bean(name = "configCoachesdb")    
    @ConfigurationProperties("app.datasource.ds2")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }
        
    @Bean(name = "configFlywayCoachesdb")    
    @ConfigurationProperties("app.flyway.ds2")
    public FlywayCoachesProperties secondFlywayProperties() {
        return new FlywayCoachesProperties();
    }
        
    @FlywayDataSource
    @Bean(name = "flywayCoachesdb", initMethod = "migrate")
    public Flyway secondFlyway(@Qualifier("configFlywayCoachesdb") FlywayCoachesProperties properties) {
        return Flyway.configure()
                .dataSource(properties.getUrl(), properties.getUser(), properties.getPassword())
                .schemas(properties.getSchema())
                .locations(properties.getLocation())
                .load();
    }
      
    @Bean(name = "dataSourceCoachesdb")
    @DependsOn("flywayCoachesdb")    
    @ConfigurationProperties("app.datasource.ds2")
    public HikariDataSource secondDataSource(@Qualifier("configCoachesdb") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }
}