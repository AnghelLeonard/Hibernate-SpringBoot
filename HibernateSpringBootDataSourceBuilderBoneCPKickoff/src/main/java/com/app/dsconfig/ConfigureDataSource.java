package com.app.dsconfig;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigureDataSource {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource")
    public BoneCPDataSource  dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(BoneCPDataSource.class)                
                .build();
    }
}
