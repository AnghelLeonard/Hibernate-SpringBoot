package com.jpa;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DtoViaSqlResultSetMappingApplication {
    
    private static final Logger logger
            = Logger.getLogger(DtoViaSqlResultSetMappingApplication.class.getName());
    
    @Autowired
    private CategoryService categoryService;
    
    public static void main(String[] args) {
        SpringApplication.run(DtoViaSqlResultSetMappingApplication.class, args);
    }
    
    @Bean
    public ApplicationRunner init() {
        return args -> {
            
            categoryService.populateCategories();
            List<CategoryDto> categories = categoryService.fetchCategories();
            
            categories.forEach((e) -> logger.info(() -> "Category: " + e));
        };
    }
}
