package com.projects.carmarket.repositories;

import com.projects.carmarket.entities.Car;
import com.projects.carmarket.entities.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CarRepositoryTests {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void saveCar() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        Car car = new Car("Tesla", "Model S", 2021, 45000, owner);
        testEntityManager.persistAndFlush(owner);
        testEntityManager.persistAndFlush(car);
        assertThat(car.getId()).isNotNull();
    }

    @Test
    void deleteAll() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        Car car = new Car("Tesla", "Model S", 2021, 45000, owner);
        Car car2 = new Car("Tesla", "Model x", 2020, 65000, owner);
        testEntityManager.persistAndFlush(owner);
        testEntityManager.persistAndFlush(car);
        testEntityManager.persistAndFlush(car2);
        carRepository.deleteAll();
        assertThat(carRepository.findAll()).isEmpty();
    }

    @Test
    void count() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        Car car = new Car("Tesla", "Model S", 2021, 45000, owner);
        Car car2 = new Car("Tesla", "Model x", 2020, 65000, owner);
        carRepository.deleteAll();
        testEntityManager.persistAndFlush(owner);
        testEntityManager.persistAndFlush(car);
        testEntityManager.persistAndFlush(car2);
        assertEquals(2, carRepository.count());
    }

    @Test
    void findById() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        Car car = new Car("Tesla", "Model S", 2021, 45000, owner);
        testEntityManager.persistAndFlush(owner);
        testEntityManager.persistAndFlush(car);
        assertEquals("Model S", carRepository.findById(car.getId()).get().getModel());
    }

    @Test
    void updateCar() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        Car car = new Car("Tesla", "Model S", 2021, 45000, owner);
        testEntityManager.persistAndFlush(owner);
        testEntityManager.persistAndFlush(car);
        car.setPrice(80000);
        carRepository.save(car);
        assertEquals(80000, carRepository.findById(car.getId()).get().getPrice());
    }
}
