package com.example.horse.model;

import com.example.horse.model.exception.ModelValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Entity
public class Breeding extends Horse implements PublicBreeding, PrivateBreeding{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String lineage;
    private int numberOfChildren;
    private String parentsAchievements;
    private Integer priceForMating;

    @ElementCollection
    @CollectionTable(name = "breeding_types", joinColumns = @JoinColumn(name = "breeding_id"))
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull
    private Set<BreedingType> types;

    public Breeding() {

    }

    public Breeding(String lineage, int numberOfChildren, Integer priceForMating, String parentsAchievements) {
        setLineage(lineage);
        setNumberOfChildren(numberOfChildren);
        setTypes(types);
        if(isPublicBreeding()) {
            setPriceForMating(priceForMating);
        }
        if(isPrivateBreeding()) {
            setParentsAchievements(parentsAchievements);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineage() {
        return lineage;
    }

    public void setLineage(String lineage) {
        if(lineage.isBlank() || lineage == null) {
            throw new ModelValidationException("lineage cannot be null");
        }
        this.lineage = lineage;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }



    ///////////these methods only work if the object is of given breeding, object can be of both

    /////public breeding

    @Override
    public void setPriceForMating(Integer priceForMating) {
        if(!isPublicBreeding()) {
            throw new ModelValidationException("Must be public breeding");
        }
        if(priceForMating == null){
            throw new ModelValidationException("priceForMating cannot be null");
        }
        this.priceForMating = priceForMating;

    }

    @Override
    public Integer getPriceForMating() {
        if(!isPublicBreeding()) {
            throw new ModelValidationException("Must be public breeding");
        }
        return priceForMating;
    }


    ///// private breeding

    @Override
    public void setParentsAchievements(String parentsAchievements) {
        if(!isPrivateBreeding()) {
            throw new ModelValidationException("Must be private breeding");
        }
        if(parentsAchievements == null){
            throw new ModelValidationException("parentsAchievements cannot be null");
        }
        this.parentsAchievements = parentsAchievements;
    }

    @Override
    public String getParentsAchievements() {
        if(!isPrivateBreeding()) {
            throw new ModelValidationException("Must be private breeding");
        }
        return parentsAchievements;
    }




    public boolean isPrivateBreeding(){
        return this.types.contains(BreedingType.PrivateBreeding.PrivateBreeding);
    }

    public boolean isPublicBreeding() {
        return this.types.contains(BreedingType.PrivateBreeding.PublicBreeding);
    }



    //////////////sets types
    public void setTypes(Set<BreedingType> types) {
        if(types.isEmpty()){
            throw new ModelValidationException("Types cannot be empty");
        }

        for(BreedingType type : types){
            if((type != BreedingType.PrivateBreeding) && (type != BreedingType.PublicBreeding)){
                throw new ModelValidationException("type must be either publicbreeding or private breeding");
            }
        }

        this.types = types;
    }








}
