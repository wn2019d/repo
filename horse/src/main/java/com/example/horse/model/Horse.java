package com.example.horse.model;

import com.example.horse.model.exception.ModelValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Horse {

    @NotBlank
    private String horseName;

    private Enum commercialType; // w/e it belongs to stable its stationed at or its private owned horse

    private Enum gender;

    private Enum purposeCategory; // if its for rent or sell

    @Min(0)
    private double serviceCharge;

    @Min(0)
    private double price;

    @Min(0)
    private double height;

    @Min(0)
    private double weight;

    @Min(0)
    private int age;

    @Min(0)
    private static int maximumAge;

    @NotBlank
    @NotNull
    private String race;

    private Integer boxNumber; //only filled if horse is private
    private String currentOwner; //only needs to be filled if horse is private
    private Integer birthCertificateNumber; //only if horse belongs to stable

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "horse", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> appointments;

    @ManyToOne
    @JoinColumn(name = "salesrep_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private SalesRepresentative advertisedBy;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "appointment_dates", joinColumns = @JoinColumn(name = "horse_id"))
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<LocalDate> appointmentDates = new ArrayList<>();

    public List<LocalDate> getAppointmentDates() {
        return appointmentDates;
    }

    public void setAppointmentDates(List<LocalDate> appointmentDates) {
        this.appointmentDates = appointmentDates;
    }


    //check if object is private, return the box number
    // @return boxNumber
    public Integer getBoxNumber() {
        if(!isPrivate()){
            throw new ModelValidationException("Must be private");
        }

        return boxNumber;
    }

    //check if horse is private, set the box number
    // @param boxNumber
    public void setBoxNumber(Integer boxNumber) {
        if(!isPrivate()){
            throw new ModelValidationException("Must be private");
        }
        if(boxNumber == null){
            throw new ModelValidationException("boxNumber cannot be null");
        }

        this.boxNumber = boxNumber;
    }

    //check if horse is private
    // @return currentOwner of the horse
    public String getCurrentOwner() {
        if(!isPrivate()){
            throw new ModelValidationException("Must be private");
        }
        return currentOwner;
    }

    //check if horse is private
    // @param currentOwner
    public void setCurrentOwner(String currentOwner) {
        if(!isPrivate()){
            throw new ModelValidationException("Must be private");
        }
        if(currentOwner == null) {
            throw new ModelValidationException("currentOwner cannot be null");
        }

        this.currentOwner = currentOwner;

    }

    //check if horse (belongs to) stable
    // @return birthCertificate number
    public Integer getBirthCertificateNumber() {
        if(!isStable()){
            throw new ModelValidationException("Must be stable horse");
        }
        return birthCertificateNumber;
    }

    //check if horse (belongs to) stable
    // @param birthCertificateNumber
    public void setBirthCertificateNumber(Integer birthCertificateNumber) {
        if(!isStable()){
            throw new ModelValidationException("Must be stable horse");
        }
        if(birthCertificateNumber == null){
            throw new ModelValidationException("birthCertificateNumber cannot be null");
        }
        this.birthCertificateNumber = birthCertificateNumber;
    }

    public Enum getPurposeCategory() {
        return purposeCategory;
    }

    public void setPurposeCategory(Enum purposeCategory) {
        if(purposeCategory == null || (purposeCategory != PurposeCategoryType.Rent && purposeCategory != PurposeCategoryType.Sell)){
            throw new ModelValidationException("Must be either rent or sell");
        }

        this.purposeCategory = purposeCategory;
    }

    public Enum getCommercialType() {
        return commercialType;
    }

    public void setCommercialType(Enum commercialType) {
        if(commercialType == null || (commercialType != CommercialType.Stable && commercialType != CommercialType.Private)){
            throw new ModelValidationException("Must be either stable or private");
        }

        this.commercialType = commercialType;
    }

    public boolean isPrivate(){
        return this.commercialType == CommercialType.Private;
    }

    public boolean isStable(){
        return this.commercialType == CommercialType.Stable;
    }


    //calculates the derived attribute of max weight of the rider for the given horse
    // @return maxRiderWeight
    public double calculateMaxRiderWeight(){
        return 0.25* weight;
    }









    //missing max rider height, maximum age for rent/sell horse, list of appointment dates
    //////////////////////////////////////////////////////////////////change birth certificate to birth certificate number
    //////////////////////////////////////////////////////////////////change max rider height to max rider weight







}
