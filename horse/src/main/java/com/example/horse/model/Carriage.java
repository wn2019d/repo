package com.example.horse.model;

import com.example.horse.model.exception.ModelValidationException;
import jakarta.persistence.*;

@Entity
public class Carriage implements AgricultureCarriage,  PeopleCarriage{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false, updatable = false)
    private Harness owner;

    private int width; //all
    private int length; //all
    private String kindOfWork; //agriculture carriage
    private Integer numberOfSeats; //people carriage
    private Boolean hasCeiling; //people carriage

    private Enum type;


    public Carriage() {

    }

    public Carriage(Harness owner, int width, int length, String kindOfWork, Integer numberOfSeats, Boolean hasCeiling, Enum type) {
        setOwner(owner);
        setType(type);
        setWidth(width);
        setLength(length);
        if(isAgriculture()) {
            setKindOfWork(kindOfWork);
        }
        if(isPeopleCarriage()) {
            setNumberOfSeats(numberOfSeats);
            setHasCeiling(hasCeiling);
        }
    }

    public Harness getOwner() {
        return owner;
    }

    public void setOwner(Harness owner) {
        this.owner = owner;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length){
        this.length = length;
    }

    ///////checks the types

    public boolean isAgriculture(){
        return this.type == CarriageType.Agriculture;
    }

    public boolean isPeopleCarriage() {
        return this.type == CarriageType.People;
    }



    //////////////////////////////////if agriculture carriage, what about override?
    @Override
    public String getKindOfWork() {
        if(!isAgriculture()) {
            throw new ModelValidationException("must be agriculture carriage");
        }
        return kindOfWork;
    }
    @Override
    public void setKindOfWork(String kindOfWork) {
        if(!isAgriculture()) {
            throw new ModelValidationException("must be agriculture carriage");
        }
        if(kindOfWork == null || kindOfWork.isBlank()) {
            throw new ModelValidationException("kind of work cannot be null or blank");
        }
        this.kindOfWork = kindOfWork;
    }

    //////////////////////////////people carriage
    @Override
    public Integer getNumberOfSeats() {
        if(!isPeopleCarriage()) {
            throw new ModelValidationException("must be people carriage");
        }
        return numberOfSeats;
    }
    @Override
    public void setNumberOfSeats(Integer numberOfSeats) {
        if(!isPeopleCarriage()) {
            throw new ModelValidationException("must be people carriage");
        }
        if(numberOfSeats < 0) {
            throw new ModelValidationException("seats cannot be minus");
        }
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public void setHasCeiling(Boolean hasCeiling) {
        if(!isPeopleCarriage()) {
            throw new ModelValidationException("must be people carriage");
        }
        if(hasCeiling == null) {
            throw new ModelValidationException("hasCeiling cannot be null");
        }
    }

    @Override
    public Boolean getHasCeiling() {
        if(!isPeopleCarriage()) {
            throw new ModelValidationException("must be people carriage");
        }
        return hasCeiling;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        if(type == null || type != CarriageType.Agriculture && type != CarriageType.People){
            throw new ModelValidationException("Type cannot be null and must be people or agriculture carriage");
        }

        this.type = type;
    }


    public void becomeAgriculture(String kindOfWork){
        this.setType(CarriageType.Agriculture);
        setKindOfWork(kindOfWork);
    }

    public void becomePeopleCarriage(Integer numberOfSeats, Boolean hasCeiling) {
        this.setType(CarriageType.People);
        setNumberOfSeats(numberOfSeats);
        setHasCeiling(hasCeiling);
    }







}
