package com.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OneToOneMapsIdApplication {

    private static final Logger logger = 
            Logger.getLogger(OneToOneMapsIdApplication.class.getName());        

    @Autowired
    CompanyService companyService;

    public static void main(String[] args) {
        SpringApplication.run(OneToOneMapsIdApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            companyService.newEmployee();
            companyService.newDeskForEmployee();
            
            Desk desk = companyService.findDeskByEmployeeId();
            logger.info(() -> "Desk id and position: " + desk.getId() + ", " + desk.getPosition());
        };
    }
}
