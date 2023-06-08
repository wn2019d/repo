package com.example.horse.repository.service;

import com.example.horse.model.Appointment;
import com.example.horse.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public Appointment saveApt(Appointment appointment){return appointmentRepository.save(appointment);}
    public Optional<Appointment> findexisting (Long customer_id, Long horse_id){return appointmentRepository.findByCustomerHorseId(customer_id, horse_id);}
}
