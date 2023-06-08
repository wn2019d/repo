package com.example.horse.repository;

import com.example.horse.model.SalesForce;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesforceRepository extends CrudRepository<SalesForce, Long> {
}
