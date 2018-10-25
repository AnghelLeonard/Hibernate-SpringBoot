package com.jpa;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    private final Dao dao;

    public CarService(Dao dao) {
        this.dao = dao;
    }

    @Transactional
    public void populateCars() {

        Car car_1=new Car();
        car_1.setName("Dacia");
        car_1.setEngine("V8");
        car_1.setColor("red");
        
        Car car_2=new Car();
        car_2.setName("BMW");
        car_2.setEngine("V6");
        car_2.setColor("blue");
        
        dao.persist(car_1);
        dao.persist(car_2);
    }

    public List<CarDto> fetchCars() {
        return dao.fetchCars();
    }
}
