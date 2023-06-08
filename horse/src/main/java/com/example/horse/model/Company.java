package com.example.horse.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * this class stores company
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // @NotBlank
    private String name;

    @NotNull
    private LocalDate dateFounded;

    @OneToMany(mappedBy = "belongsTo", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SalesForce> salesForces = new HashSet<>();

    public Set<SalesForce> getSalesForces() {
        return salesForces;
    }


}
