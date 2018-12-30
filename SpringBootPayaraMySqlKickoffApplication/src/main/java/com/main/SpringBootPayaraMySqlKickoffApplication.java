package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.jpa.model")   
@ComponentScan({"com.http.requests", "com.services"})
@EnableJpaRepositories("com.jpa.queries")
public class SpringBootPayaraMySqlKickoffApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootPayaraMySqlKickoffApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPayaraMySqlKickoffApplication.class, args);
    }
}
