package com.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarViewRepository carViewRepository;

    public CarService(CarRepository carRepository, CarViewRepository carViewRepository) {
        this.carRepository = carRepository;
        this.carViewRepository = carViewRepository;
    }

    @Transactional
    public void populateCars() {

        Car car_1 = new Car();
        car_1.setName("Dacia");
        car_1.setEngine("V8");
        car_1.setColor("red");

        Car car_2 = new Car();
        car_2.setName("BMW");
        car_2.setEngine("V6");
        car_2.setColor("blue");

        carRepository.save(car_1);
        carRepository.save(car_2);
    }

    public Iterable<CarView> fetchAllCars() {
        return carViewRepository.findAll();
    }
}
