package com.app.dsconfig;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.vibur.dbcp.ViburDBCPDataSource;

@Configuration
public class ConfigureDataSource {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(initMethod = "start", destroyMethod = "terminate")
    @ConfigurationProperties("app.datasource")
    public ViburDBCPDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(ViburDBCPDataSource.class)
                .build();
    }
}
