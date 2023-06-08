package com.example.horse.model;

import com.example.horse.model.exception.ModelValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"customer_id", "horse_id"})
})
public class Appointment{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)

    @NotNull
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "horse_id", nullable = false)

    @NotNull
    private Horse horse;

    @NotNull
    private String stable;

    @NotNull
    private LocalDate date;

    private Enum appointmentState;

    public Enum getAppointmentState() {
        return appointmentState;
    }

    /**
     * Method checks if appointment state is valid before setting new appointment state
     * @param appointmentState new appointment state to be set
     */

    public void setAppointmentState(Enum appointmentState) {
        if(appointmentState == null){
            throw new ModelValidationException("Appointment state cannot be null");
        }

        if(appointmentState != AppointmentState.Archived
                && appointmentState != AppointmentState.Booked
                && appointmentState != AppointmentState.NotBooked
                && appointmentState != AppointmentState.Cancelled
                && appointmentState != AppointmentState.Completed
                && appointmentState != AppointmentState.InProgress){
            throw new ModelValidationException("appointment state must be either booked, notbooked, archived, cancelled, completed or inprogress");
        }

        this.appointmentState = appointmentState;
    }

}

