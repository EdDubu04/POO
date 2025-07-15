package com.mycompany.main.vista;

import com.mycompany.main.DAO.ProductoDAO;
import com.mycompany.main.modelo.Producto;
import com.mycompany.main.modelo.DetallePedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaMenuInteractiva extends JFrame {
    private List<Producto> productos;
    private List<DetallePedido> carrito = new ArrayList<>();

    private DefaultTableModel carritoModel;

    public VentanaMenuInteractiva() {
        setTitle("Menú - Autoatención");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ProductoDAO dao = new ProductoDAO();
        productos = dao.listarProductos();

        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 1, 5, 5));
        for (Producto p : productos) {
            JButton boton = new JButton(p.getNombre() + " - S/ " + p.getPrecio());
            boton.addActionListener(e -> agregarAlCarrito(p));
            panelProductos.add(boton);
        }

        JScrollPane scroll = new JScrollPane(panelProductos);
        scroll.setBorder(BorderFactory.createTitledBorder("Selecciona productos"));
        add(scroll, BorderLayout.WEST);

        carritoModel = new DefaultTableModel(new Object[]{"Producto", "Cantidad", "Subtotal"}, 0);
        JTable tablaCarrito = new JTable(carritoModel);
        JScrollPane scrollTabla = new JScrollPane(tablaCarrito);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Carrito de Pedido"));
        add(scrollTabla, BorderLayout.CENTER);

        JButton btnConfirmar = new JButton("Confirmar Pedido");
        btnConfirmar.addActionListener(e -> confirmarPedido());

        JPanel abajo = new JPanel();
        abajo.add(btnConfirmar);
        add(abajo, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void agregarAlCarrito(Producto producto) {
        boolean yaExiste = false;

        for (DetallePedido d : carrito) {
            if (d.getProducto().getIdProducto() == producto.getIdProducto()) {
                d = new DetallePedido(producto, d.getCantidad() + 1);
                yaExiste = true;
                break;
            }
        }

        if (!yaExiste) {
            carrito.add(new DetallePedido(producto, 1));
        }

        actualizarTabla();
    }

    private void actualizarTabla() {
        carritoModel.setRowCount(0); // Limpiar
        for (DetallePedido d : carrito) {
            carritoModel.addRow(new Object[]{
                    d.getProducto().getNombre(),
                    d.getCantidad(),
                    "S/ " + d.getSubtotal()
            });
        }
    }

    private void confirmarPedido() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en el pedido.");
        } else {
            JOptionPane.showMessageDialog(this, "✅ Pedido confirmado. Gracias.");

            carrito.clear();
            carritoModel.setRowCount(0);
        }
    }
}

