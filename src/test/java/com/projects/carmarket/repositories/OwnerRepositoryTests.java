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
public class OwnerRepositoryTests {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void saveOwner() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        testEntityManager.persistAndFlush(owner);
        assertThat(owner.getId()).isNotNull();
    }

    @Test
    void deleteAll() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        Owner owner2 = new Owner("Bill", "Gates", "billgates@gmail.com");
        testEntityManager.persistAndFlush(owner);
        testEntityManager.persistAndFlush(owner2);
        ownerRepository.deleteAll();
        assertThat(ownerRepository.findAll()).isEmpty();
    }

    @Test
    void count() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        Owner owner2 = new Owner("Bill", "Gates", "billgates@gmail.com");
        ownerRepository.deleteAll();
        testEntityManager.persistAndFlush(owner);
        testEntityManager.persistAndFlush(owner2);
        assertEquals(2, ownerRepository.count());
    }

    @Test
    void findById() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        testEntityManager.persistAndFlush(owner);
        assertEquals("Musk", ownerRepository.findById(owner.getId()).get().getLastName());
    }

    @Test
    void updateOwner() {
        Owner owner = new Owner("Elon", "Musk", "elonmusk@gmail.com");
        testEntityManager.persistAndFlush(owner);
        owner.setFirstName("Tali");
        ownerRepository.save(owner);
        assertEquals("Tali", ownerRepository.findById(owner.getId()).get().getFirstName());
    }
}
