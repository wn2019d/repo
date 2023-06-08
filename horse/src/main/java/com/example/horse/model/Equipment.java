package com.example.horse.model;


import com.example.horse.model.exception.ModelValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Entity
public class Equipment implements Protectors, Reins, Saddle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false, updatable = false)
    private Casual owner;


    private String brand; //every equipment
    private Boolean hasAdditionalStrings; //reins
    private Integer size; //saddle
    private Boolean isDouble; //saddle
    private String material; //protectors
    private String bodyPart; //protectors


    //////////////////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    @CollectionTable(name = "equipment_type", joinColumns = @JoinColumn(name = "equipment_id"))
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull
    private Enum equipmentType;

    public Equipment() {
    }

    public Equipment(Casual owner, String brand, Boolean hasAdditionalStrings, Integer size, Boolean isDouble, String material, String bodyPart, EquipmentType equipmentType) {
        setOwner(owner);
        setBrand(brand);
        setType(equipmentType);
        if(isProtectors()) {
            setMaterial(material);
            setBodyPart(bodyPart);
        }
        if(isSaddle()) {
            setSize(size);
            setIsDouble(isDouble);
        }
        if(isReins()) {
            setHasAdditionalStrings(hasAdditionalStrings);
        }
    }

    public void setType(Enum equipmentType) {
            if (equipmentType == null) {
                throw new ModelValidationException("type cannot be null");
            }

            if (equipmentType != EquipmentType.Reins
                    && equipmentType != EquipmentType.Protectors
                    && equipmentType != EquipmentType.Saddle) {
                throw new ModelValidationException("equipmentType must be reins, protectors or saddle");
        }

        this.equipmentType = equipmentType;
    }


    private void setBrand(String brand) {
        if(brand == null) {
            throw new ModelValidationException("brand cannot be null");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Casual getOwner() {
        return owner;
    }

    public void setOwner(Casual owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }


    ///////////////////////////////////////////////////for reins


    @Override
    public Boolean getHasAdditionalStrings() {
        if(!isReins()){
            throw new ModelValidationException("Must be reins");
        }
        return hasAdditionalStrings;
    }

    @Override
    public void setHasAdditionalStrings(Boolean hasAdditionalStrings) {
        if(!isReins()) {
            throw new ModelValidationException("Must be reins");
        }
        if(hasAdditionalStrings == null){
            throw new ModelValidationException("hasAdditionalStrings cannot be null");
        }

        this.hasAdditionalStrings = hasAdditionalStrings;
    }


    ///////////////////////////////////////////////////for saddle

    @Override
    public Integer getSize() {
        if(!isSaddle()){
            throw new ModelValidationException("Must be saddle");
        }
        return size;
    }


    @Override
    public void setSize(Integer size) {
        if(!isSaddle()){
            throw new ModelValidationException("Must be saddle");
        }
        if(size == null){
            throw new ModelValidationException("size cannot be null");
        }
        this.size = size;
    }


    @Override
    public Boolean getIsDouble() {
        if(!isSaddle()){
            throw new ModelValidationException("Must be saddle");
        }
        return isDouble;
    }

    @Override
    public void setIsDouble(Boolean isDouble) {
        if(!isSaddle()){
            throw new ModelValidationException("Must be saddle");
        }
        if(isDouble == null){
            throw new ModelValidationException("isDouble cannot be null");
        }
        this.isDouble=isDouble;
    }

    ///////////////////////////////////////////////////for protectors

    @Override
    public String getMaterial() {
        if(!isProtectors()){
            throw new ModelValidationException("Must be protectors");
        }
        return material;
    }

    @Override
    public void setMaterial(String material) {
        if(!isProtectors()){
            throw new ModelValidationException("Must be protectors");
        }
        if(material == null){
            throw new ModelValidationException("material cannot be null");
        }
        this.material = material;
    }

    @Override
    public String getBodyPart() {
        if(!isProtectors()){
            throw new ModelValidationException("Must be protectors");
        }
        return bodyPart;
    }

    @Override
    public void setBodyPart(String bodyPart) {
        if(!isProtectors()){
            throw new ModelValidationException("Must be protectors");
        }
        if(bodyPart == null){
            throw new ModelValidationException("bodyPart cannot be null");
        }
        this.bodyPart = bodyPart;
    }

    public boolean isReins(){
        if(equipmentType == EquipmentType.Reins) {
            return true;
        }
        return false;
    }

    public boolean isSaddle() {
        if(equipmentType == EquipmentType.Saddle) {
            return true;
        }
        return false;
    }

    public boolean isProtectors() {
        if(equipmentType == EquipmentType.Protectors) {
            return true;
        }
        return false;
    }


}
