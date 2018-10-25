package com.jpa;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ResultTransformerApplication {

    private static final Logger logger
            = Logger.getLogger(ResultTransformerApplication.class.getName());

    @Autowired
    private CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(ResultTransformerApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            carService.populateCars();

            List<CarDtoNoSetters> carsNoSetters = carService.fetchCarsNoSetters();
            logger.info("carsNoSetters ...");
            carsNoSetters.forEach((e) -> logger.info(() -> "Car: " + e));

            List<CarDtoWithSetters> carsWithSetters = carService.fetchCarsWithSetters();
            logger.info("carsWithSetters ...");
            carsWithSetters.forEach((e) -> logger.info(() -> "Car: " + e));
        };
    }
}
