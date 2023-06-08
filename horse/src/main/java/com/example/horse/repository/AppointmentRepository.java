package com.example.horse.repository;

import com.example.horse.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    @Query("from Appointment as a where a.customer.id = :customer_id  and a.horse.id = :horse_id")
    public Optional<Appointment> findByCustomerHorseId(@Param("customer_id") Long customer_id, @Param("horse_id") Long horse_id);
}
