package com.jpa;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlywayKickoffApplication {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    public FlywayKickoffApplication(CarRepository carRepository, DriverRepository driverRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FlywayKickoffApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            Car car = new Car();            
            car.setName("Toyota");
            
            Driver driver = new Driver();
            driver.setName("Mark F");
                                
            carRepository.save(car);
            driverRepository.save(driver);
        };
    }
}
