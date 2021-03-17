package com.projects.carmarket;

import com.projects.carmarket.entities.Car;
import com.projects.carmarket.entities.Owner;
import com.projects.carmarket.repositories.CarRepository;
import com.projects.carmarket.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarMarketApplication {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CarMarketApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Owner owner1 = new Owner("Ayoub", "Benzzine", "ayoubbenzzine@gmail.com");
            Owner owner2 = new Owner("Khadija", "Karim", "khdidoujti@gmail.com");
            Owner owner3 = new Owner("Latif", "Benzzine", "lbenzzine@gmail.com");
            Owner owner4 = new Owner("Mariam", "Makhtari", "makhtarimariam@gmail.com");
            ownerRepository.save(owner1);
            ownerRepository.save(owner2);
            ownerRepository.save(owner3);
            ownerRepository.save(owner4);
            carRepository.save(new Car("Hyundai", "Elantra", 2018, 15000, false, owner1));
            carRepository.save(new Car("Dacia", "Duster", 2017, 20000, false, owner2));
            carRepository.save(new Car("Opel", "Astra", 2020, 24000, false, owner4));
            carRepository.save(new Car("Honda", "CRV", 2016, 30000, false, owner3));
        };
    }
}
