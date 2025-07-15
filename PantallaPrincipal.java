package com.mycompany.main.vista;

import com.mycompany.main.vista.VentanaMenu;

import javax.swing.*;
import java.awt.*;

public class PantallaPrincipal extends JFrame {

    public PantallaPrincipal() {
        setTitle("Autoatención Restaurante");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Bienvenido al autoservicio", SwingConstants.CENTER);
        JButton verMenu = new JButton("Ver Menú");
        JButton salir = new JButton("Salir");

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(2, 1, 10, 10));
        centro.add(verMenu);
        centro.add(salir);
        add(centro, BorderLayout.CENTER);

        verMenu.addActionListener(e -> {
            new VentanaMenu();
            dispose();
        });

        salir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
