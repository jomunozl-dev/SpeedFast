package gui;

import Controlador.ControladorPedido; 
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Pedido;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private ControladorPedido controlador; 

    public VentanaListaPedidos() {
        this.controlador = new ControladorPedido();

        setTitle("Lista de Pedidos - MVC");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPedidos = new JTable(modeloTabla);

        add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Refrescar Tabla");
        btnRefrescar.addActionListener(e -> cargarDatos());
        add(btnRefrescar, BorderLayout.SOUTH);

        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        
        for (Pedido p : controlador.obtenerTodosLosPedidos()) {
            Object[] fila = {p.getId(), p.getDireccionEntrega(), p.getTipo(), p.getEstado()};
            modeloTabla.addRow(fila);
        }
    }
}