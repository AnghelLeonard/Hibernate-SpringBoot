package com.jpa;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EntityViewApplication {

    private static final Logger logger
            = Logger.getLogger(EntityViewApplication.class.getName());

    @Autowired
    private CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(EntityViewApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            carService.populateCars();

            Iterable<CarView> cars = carService.fetchAllCars();

            cars.forEach((c) -> logger.info(() -> "Car name: " 
                    + c.getName() + ", color: " + c.getColor()));
        };
    }
}
