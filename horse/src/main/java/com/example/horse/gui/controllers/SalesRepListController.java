package com.example.horse.gui.controllers;

import com.example.horse.gui.view.SalesRepListView;
import com.example.horse.model.Appointment;
import com.example.horse.model.AppointmentState;
import com.example.horse.model.Customer;
import com.example.horse.model.Horse;
import com.example.horse.repository.service.AppointmentService;
import com.example.horse.repository.service.CustomerService;
import com.example.horse.repository.service.HorseService;
import com.example.horse.repository.service.SalesRepService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class SalesRepListController {

    private final SalesRepListView view;
    private final HorseService horseService;
    private final CustomerService customerService;
    private final AppointmentService appointmentService;
    private final SalesRepService salesRepService;
    private Long repId;

    public void showGUI(MainWindowController mainWindowController){
        mainWindowController.showView(view.getMainPanel());
    }

    @PostConstruct
    public void initView(){
        view.createUIComponents();
        view.getRepTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(!event.getValueIsAdjusting()){
                    resetFilter();

                    int row = view.getRepTable().getSelectedRow();
                    String fullName = "" + view.getRepTable().getModel().getValueAt(row, 1);

                    view.getSelectedSalesRepValue().setText(fullName);

                    view.clearDatesList();

                    view.clearBookAppointment();

                    view.setCurrentHorse(null);


                    try{
                        repId = (Long) view.getRepTable().getModel().getValueAt(row, 0);
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    List<Horse> allHorses = horseService.getHorsesBySalesrepId(repId);
                    view.populateHorseTable(allHorses);
                }
            }
        });


///comment this?
        view.getHorseTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(!event.getValueIsAdjusting()){
                    resetFilter();

                    checkAndUpdateDates();

                    view.clearBookAppointment();
                }
            }
        });
//comment end??

        view.getFilterButton().setBackground(Color.CYAN);
        view.getFilterButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        view.getFilterButton().addActionListener(e -> {

            view.clearHorseTable();
            try {
                int height = Integer.parseInt(view.getMinHeightValue().getText());
                int age = Integer.parseInt(view.getMinAgeValue().getText());

                resetFilter();

                List<Horse> horses = horseService.getHorsesByHeightAndAge(repId, height, age);

                view.filterHorseTable(horses);
            }
            catch (Exception ex) {
                System.out.println("Exception this one: " + ex);
            }
        });

        view.getBookAppointmentButton().setBackground(Color.CYAN);
        view.getBookAppointmentButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        view.getBookAppointmentButton().addActionListener(e -> {
            LocalDate date = (LocalDate)view.getDateList().getSelectedValue();
            if(date != null){
                Horse horse = view.getCurrentHorse();

                Optional<Customer> customer = customerService.getCustomersById(3L);
                Customer cus = customer.get();

                Optional<Appointment> appointmentFound = appointmentService.findexisting(cus.getId(), horse.getId());

                if(appointmentFound.isEmpty()){
                    Appointment app1 = Appointment.builder()
                            .stable("Sunny")
                            .date(date)
                            .build();

                    app1.setAppointmentState(AppointmentState.Booked);

                    app1.setHorse(horse);

                    app1.setCustomer(cus);

                    appointmentService.saveApt(app1);

                    horse.getAppointments().add(app1);
                    horseService.saveHorse(horse);

                    cus.getAppointments().add(app1);
                    customerService.saveCus(cus);

                    view.bookAppointment(date);
                }
            }
        });

        view.getDateList().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                view.clearBookAppointment();
            }
        });
    }

    private void resetFilter() {
        view.getMinHeightValue().setText("");
        view.getMinAgeValue().setText("");
    }

    private void checkAndUpdateDates() {
        int row = view.getHorseTable().getSelectedRow();

        if(row != -1){
            Long horseId = (Long) view.getHorseTable().getModel().getValueAt(row, 0);

            if(horseId != null){
                Optional<Horse> horse = horseService.getHorseById(horseId);
                view.setCurrentHorse(horse.get());
                view.updateDateList();
            }
        }else{
            view.setCurrentHorse(null);
        }
    }


}
