package com.mycompany.main.vista;

import com.mycompany.main.modelo.Producto;
import com.mycompany.main.DAO.ProductoDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaMenu extends JFrame {
    public VentanaMenu() {
        setTitle("Menú del Restaurante");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ProductoDAO dao = new ProductoDAO();
        List<Producto> productos = dao.listarProductos();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Producto p : productos) {
            panel.add(new JLabel(p.getNombre() + " - S/ " + p.getPrecio()));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }
}
