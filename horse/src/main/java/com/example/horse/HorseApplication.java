package com.example.horse;

import com.example.horse.gui.controllers.MainWindowController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class HorseApplication {
    public static void main(String[] args) {
        //SpringApplication.run(MasFinalApplication.class, args);
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(HorseApplication.class).headless(false)
                .run(args);

        SwingUtilities.invokeLater(() -> ctx.getBean(MainWindowController.class).showGUI());
    }
}
