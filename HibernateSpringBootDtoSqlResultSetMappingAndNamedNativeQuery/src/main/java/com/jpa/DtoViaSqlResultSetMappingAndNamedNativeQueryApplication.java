package com.jpa;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DtoViaSqlResultSetMappingAndNamedNativeQueryApplication {
    
    private static final Logger logger
            = Logger.getLogger(DtoViaSqlResultSetMappingAndNamedNativeQueryApplication.class.getName());
    
    @Autowired
    private CarService carService;
    
    public static void main(String[] args) {
        SpringApplication.run(DtoViaSqlResultSetMappingAndNamedNativeQueryApplication.class, args);
    }
    
    @Bean
    public ApplicationRunner init() {
        return args -> {
            
            carService.populateCars();
            List<CarDto> cars = carService.fetchCars();
            
            cars.forEach((e) -> logger.info(() -> "Car: " + e));
        };
    }
}
