package com.jpa;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TupleAndSqlApplication {
    
    private static final Logger logger
            = Logger.getLogger(TupleAndSqlApplication.class.getName());
    
    @Autowired
    private CarService carService;
    
    public static void main(String[] args) {
        SpringApplication.run(TupleAndSqlApplication.class, args);
    }
    
    @Bean
    public ApplicationRunner init() {
        return args -> {
            
            carService.populateCars();
            List<Tuple> cars = carService.fetchCars();
            
            cars.forEach((e) -> logger.info(() -> "Car: " + e.get("name") + "," + e.get("color")));
        };
    }
}
