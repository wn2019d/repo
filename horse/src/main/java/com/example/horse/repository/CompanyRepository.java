package com.example.horse.repository;

import com.example.horse.model.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    public List<Company> findByName(String name);

    @Query("from Company as c left join fetch c.salesForces where c.id = :id")
    public Optional<Company> findById(@Param("id") Long id);
}
