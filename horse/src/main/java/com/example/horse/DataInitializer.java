package com.example.horse;


import com.example.horse.model.*;
import com.example.horse.repository.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {
/*
    private final CompanyRepository companyRepository;
    private final SalesforceRepository salesforceRepository;
    private final CustomerRepository customerRepository;
    private final SalesRepresentativeRepository salesRepresentativeRepository;
    private final HorsesRepository horsesRepository;

    private EntityManager entityManager;

    Company c1;
    SalesForce s1;
    Customer cus1;
    Customer cus2;

    SalesRepresentative srep1;
    SalesRepresentative srep2;

    Racing r1;
    Racing r2;
    Racing r3;
    Casual r4;

    @EventListener
    public void atStart(ContextRefreshedEvent ev){
        c1 = Company.builder()
                .dateFounded(LocalDate.of(2011, 04, 03))
                .build();

        s1 = SalesForce.builder()
                .name("Sales force 1")
                .build();

        c1.getSalesForces().add(s1);  ////???
        companyRepository.save(c1);
        s1.setBelongsTo(c1);
        salesforceRepository.save(s1);

        cus1 = Customer.builder()
                .firstName("Weronika")
                .lastName("Nurzynska")
                .dateOfBirth(LocalDate.of(1999,8,3))
                .email("rai@dbd.com")
                .password("raissword")
                .build();

        cus2 = Customer.builder()
                .firstName("Donna")
                .lastName("Monna")
                .dateOfBirth(LocalDate.of(1998,7,8))
                .email("donna@monna.com")
                .password("monasson")
                .build();

        customerRepository.save(cus1);
        customerRepository.save(cus2);

        srep1 = SalesRepresentative.builder()
                .firstName("Meryl")
                .lastName("Mason")
                .dateOfBirth(LocalDate.of(1997,4,4))
                .appointmentPrice(100.0)
                .isAdmin(false)
                .build();

        srep2 = SalesRepresentative.builder()
                .firstName("Olivia")
                .lastName("Brown")
                .dateOfBirth(LocalDate.of(1996,3,3))
                .appointmentPrice(200.0)
                .isAdmin(true)
                .build();

        s1.getSalesRepresentatives().add(srep1);
        srep1.setEmployedBy(s1);
        salesRepresentativeRepository.save(srep1);
        s1.getSalesRepresentatives().add(srep2);
        srep2.setEmployedBy(s1);
        salesRepresentativeRepository.save(srep2);
        salesforceRepository.save(s1);

        r1 = Racing.builder()
                .horseName("Howrse")
                .commercialType(CommercialType.Private)
                .serviceCharge(50.0)
                .price(4000.0)
                .height(190)
                .age(20)
                .highestSpeed(45.0)
                .purposeCategory(PurposeCategoryType.Sell)
                .boxNumber(3)
                .currentOwner("Thomas Sand")
                .highestObstacleJumped(122.0)
                .gender(Gender.female)
                .race("Arabic")
                .build();

        List<LocalDate> r1AppointmentDates = new ArrayList<>();
        r1AppointmentDates.add(LocalDate.of(2024,3,3));
        r1AppointmentDates.add(LocalDate.of(2024,4,4));
        r1AppointmentDates.add(LocalDate.of(2024,5,6));
        r1.setAppointmentDates(r1AppointmentDates);

        r2 = Racing.builder()
                .horseName("Howrse2")
                .commercialType(CommercialType.Private)
                .serviceCharge(50.0)
                .price(10000.0)
                .height(200)
                .age(4)
                .highestSpeed(46.0)
                .purposeCategory(PurposeCategoryType.Sell)
                .boxNumber(5)
                .currentOwner("Thomas Rand")
                .highestObstacleJumped(124.0)
                .gender(Gender.male)
                .race("Kornik")
                .build();

        List<LocalDate> r2AppointmentDates = new ArrayList<>();
        r2AppointmentDates.add(LocalDate.of(2024,7,7));
        r2AppointmentDates.add(LocalDate.of(2024,4,4));
        r2AppointmentDates.add(LocalDate.of(2024,1,5));
        r2.setAppointmentDates(r2AppointmentDates);

        r3 = Racing.builder()
                .horseName("Howrse2")
                .commercialType(CommercialType.Stable)
                .serviceCharge(60.0)
                .price(400.0)
                .height(210)
                .age(5)
                .highestSpeed(47.0)
                .purposeCategory(PurposeCategoryType.Rent)
                .birthCertificateNumber(8546)
                .highestObstacleJumped(128.0)
                .gender(Gender.male)
                .race("Kornik2")
                .build();

        List<LocalDate> r3AppointmentDates = new ArrayList<>();
        r3AppointmentDates.add(LocalDate.of(2024,9,3));
        r3AppointmentDates.add(LocalDate.of(2024,5,7));
        r3AppointmentDates.add(LocalDate.of(2024,8,9));
        r3.setAppointmentDates(r3AppointmentDates);

        Equipment e1;

        Set<Equipment> equipments = new HashSet<Equipment>();

        r4 = Casual.builder()
                .horseName("Howrse3")
                .commercialType(CommercialType.Private)
                .serviceCharge(100.0)
                .price(500.0)
                .height(200)
                .age(4)
                .equipments(equipments)
                .purposeCategory(PurposeCategoryType.Rent)
                .boxNumber(57)
                .currentOwner("Thomas Randall")
                .gender(Gender.male)
                .race("Kornik")
                .build();

        e1 = new Equipment(r4, "Macro Polo", Boolean.FALSE, 20, Boolean.FALSE, "20% leather, 80% cotton", "Knees", EquipmentType.Protectors);
        r4.getEquipments().add(e1);


        List<LocalDate> r4AppointmentDates = new ArrayList<>();
        r4AppointmentDates.add(LocalDate.of(2024,8,12));
        r4AppointmentDates.add(LocalDate.of(2024,3,17));
        r4AppointmentDates.add(LocalDate.of(2024,6,10));
        r4.setAppointmentDates(r4AppointmentDates);

        srep1.getHorses().add(r1);
        r1.setAdvertisedBy(srep1);
        horsesRepository.save(r1);

        srep1.getHorses().add(r2);
        r2.setAdvertisedBy(srep1);
        horsesRepository.save(r2);

        salesRepresentativeRepository.save(srep1);

        srep2.getHorses().add(r3);
        r3.setAdvertisedBy(srep2);
        horsesRepository.save(r3);

        srep2.getHorses().add(r4);
        r4.setAdvertisedBy(srep2);
        horsesRepository.save(r4);
    }

*/

}
