package com.example.horse.model;


import com.example.horse.model.exception.ModelValidationException;
import com.example.horse.repository.CustomerRepository;
import com.example.horse.repository.HorsesRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SalesRepresentative extends Person {

    @Min(0)
    private Double appointmentPrice;

    @NotNull
    private Boolean isAdmin;

    @ManyToOne
    @JoinColumn(name = "salesforce_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SalesForce employedBy;

    @OneToMany(mappedBy = "advertisedBy", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Horse> horses = new HashSet<>();

    //allows to add a horse advertisement
    // @param horse - horse that is getting the advertisement
    public void addHorseAdvertisement(Horse horse, HorsesRepository horsesRepository){
        horsesRepository.save(horse);
    }

    //allows to add customer account
    // @param customer - customer that is getting added
    public void addCustomerAccount(Customer customer, CustomerRepository customerRepository){
        if(!isAdmin){
            throw new ModelValidationException("must be admin to add a customer");
        }

        customerRepository.save(customer);
    }




}
