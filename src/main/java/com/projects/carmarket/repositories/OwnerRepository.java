package com.projects.carmarket.repositories;

import com.projects.carmarket.entities.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
