package com.example.horse.gui.view;

import com.example.horse.model.Horse;
import com.example.horse.model.SalesRepresentative;
import com.example.horse.repository.service.SalesRepService;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

@Component
@Data
public class SalesRepListView{
    private JPanel mainPanel;
    private JLabel selectedSalesRepValue;
    private JTextField minHeightValue; //double > int
    private JButton filterButton;
    private JTextField minAgeValue; // double > int
    private JList dateList;
    private JButton bookAppointmentButton;
    private JLabel appointmentDateValue;
    private JLabel stableValue;  /////////// int > string
    private DefaultListModel listModel;
    private Horse currentHorse;
    private final SalesRepService salesrepService;
    private JTable repTable;
    private JTable horseTable;

    public void createUIComponents() {

       // mainPanel = new JPanel();
        /*
        filterButton = new JButton();
        bookAppointmentButton = new JButton();
        dateList = new JList();
*/

        DefaultTableModel tableModel = new DefaultTableModel();
        repTable = new JTable(tableModel);
        tableModel.addColumn("Id");
        tableModel.addColumn("Name");
        tableModel.addColumn("Appointment Price");

        List<SalesRepresentative> reps = salesrepService.getAllRepresentatives();

        for(SalesRepresentative rep : reps){
            String fullName = rep.getFirstName() + " " + rep.getLastName();

            tableModel.addRow(new Object[]{
                    rep.getId(),
                    fullName,
                    rep.getAppointmentPrice()
            });
        }
        repTable.setModel(tableModel);
    }

    public void clearDatesList(){
        dateList.setModel(new DefaultListModel());
        DefaultListModel listModel1 = (DefaultListModel) dateList.getModel();
        listModel1.removeAllElements();
        dateList.setModel(listModel1);
    }

    public void clearBookAppointment() {
        appointmentDateValue.setText("");
        stableValue.setText("");
    }

    public void clearHorseTable() {
        horseTable.setModel(new DefaultTableModel());
        DefaultTableModel tableModel2= (DefaultTableModel) horseTable.getModel();
        tableModel2.setRowCount(0);
        horseTable.setModel(tableModel2);

    }

    public void filterHorseTable(List<Horse> horses) {
        clearDatesList();
        clearBookAppointment();
        clearHorseTable();

        populateHorseTable(horses);
    }

    public void populateHorseTable(List<Horse> allHorses){
        DefaultTableModel tableModel1 = new DefaultTableModel();
        tableModel1.addColumn("Id");
        tableModel1.addColumn("Name");
        tableModel1.addColumn("Price(USD)");
        tableModel1.addColumn("Age(Years)");
        tableModel1.addColumn("Height(Cm)");
        tableModel1.addColumn("Weight(kg)");
        tableModel1.addColumn("Race");
        tableModel1.addColumn("Max Rider Weight"); ///////////////////////////////////need to add this to GUI
        tableModel1.addColumn("Commercial Type");

        for(Horse horse : allHorses){
            tableModel1.addRow(new Object[]{
                    horse.getId(),
                    horse.getHorseName(),
                    horse.getPrice(),
                    horse.getAge(),
                    horse.getHeight(),
                    horse.getWeight(),
                    horse.getRace(),
                    horse.calculateMaxRiderWeight(),
                    horse.getCommercialType()
            });
        }
        horseTable.setModel(tableModel1);

        //System.out.println(allHorses);
    }


    public void updateDateList(){
        listModel = new DefaultListModel();

        List<LocalDate> horseDates = currentHorse.getAppointmentDates();

        for (LocalDate date : horseDates){
            listModel.addElement(date);
        }

        dateList.setModel(listModel);
    }

    public void bookAppointment(LocalDate date){
        appointmentDateValue.setText(date.toString());
        stableValue.setText("205");     /////////////////////////////???????????????????????????
    }
}
