package com.example.horse.gui.controllers;

import com.example.horse.gui.view.MainWindowView;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;

@Controller
@RequiredArgsConstructor
public class MainWindowController {
    private final MainWindowView view;
    private final SalesRepListController salesRepListController;

    public void showGUI(){
        view.setVisible(true);
    }

    @PostConstruct
    private void initGui(){
        salesRepListController.showGUI(this);
    }

    public void showView(JPanel viewToShow){
        view.getContentPane().removeAll();
        view.getContentPane().add(viewToShow);
        view.revalidate();
    }
}
