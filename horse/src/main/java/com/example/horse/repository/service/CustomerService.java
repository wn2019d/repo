package com.example.horse.repository.service;

import com.example.horse.model.Customer;
import com.example.horse.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Optional<Customer> getCustomersById(long customer_Id){return customerRepository.findById(customer_Id);}

    public Customer saveCus(Customer customer){return customerRepository.save(customer);}
}
