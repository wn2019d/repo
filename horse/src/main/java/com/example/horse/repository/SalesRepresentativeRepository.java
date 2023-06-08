package com.example.horse.repository;

import com.example.horse.model.SalesRepresentative;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepresentativeRepository extends CrudRepository<SalesRepresentative, Long> {
}

