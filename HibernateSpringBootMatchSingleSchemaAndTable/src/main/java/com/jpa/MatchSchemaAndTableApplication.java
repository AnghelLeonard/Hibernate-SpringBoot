package com.jpa;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MatchSchemaAndTableApplication {

    private final CarRepository carRepository;
    
    public MatchSchemaAndTableApplication(CarRepository carRepository) {
        this.carRepository = carRepository;    
    }

    public static void main(String[] args) {
        SpringApplication.run(MatchSchemaAndTableApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            Car car = new Car();            
            car.setName("Toyota");           
                                
            carRepository.save(car);           
        };
    }
}
