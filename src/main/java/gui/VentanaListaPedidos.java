/**
 *
 * @author Jorge Munoz Leon
 */

package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Pedido;
import servicio.ZonaDeCarga;

public class VentanaListaPedidos extends JFrame {

    private final ZonaDeCarga zonaDeCarga;
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;

    public VentanaListaPedidos(ZonaDeCarga zona) {
        this.zonaDeCarga = zona;

        setTitle("Listado de Pedidos");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPedidos = new JTable(modeloTabla);

        add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.addActionListener(e -> cargarDatos());
        add(btnRefrescar, BorderLayout.SOUTH);

        cargarDatos();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0); // Limpiar filas anteriores
        for (Pedido p : zonaDeCarga.obtenerTodosLosPedidos()) {
            Object[] fila = {p.getId(), p.getDireccionEntrega(), p.getTipo(), p.getEstado()};
            modeloTabla.addRow(fila);
        }
    }
}