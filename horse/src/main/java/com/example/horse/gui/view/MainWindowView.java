package com.example.horse.gui.view;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Data
@Component
public class MainWindowView extends JFrame {
    public MainWindowView() {
        setTitle("Horse buy and rent");
        setMinimumSize(new Dimension(1250, 800));
        setSize(1300, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
