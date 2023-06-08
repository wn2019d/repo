package com.example.horse.model;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * this class stores racing horses
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Racing extends Horse {

    @Min(0)
    private double highestObstacleJumped;

    @Min(0)
    private double highestSpeed;

}
