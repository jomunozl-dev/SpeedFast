package gui;

import Controlador.ControladorRepartidor;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Repartidor;

public class VentanaGestionRepartidores extends JFrame {

    private JTextField txtNombre;
    private JTable tabla;
    private DefaultTableModel modelo;
    private ControladorRepartidor controlador;

    private int idSeleccionado = -1;

    public VentanaGestionRepartidores() {

        controlador = new ControladorRepartidor();

        setTitle("Gestión de Repartidores");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridLayout(1, 2, 10, 10));
        formulario.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 5, 15));

        formulario.add(new JLabel("Nombre:"));

        txtNombre = new JTextField();
        formulario.add(txtNombre);

        add(formulario, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre"}, 0);

        tabla = new JTable(modelo);

        tabla.getSelectionModel().addListSelectionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {
                idSeleccionado =
                        Integer.parseInt(
                                modelo.getValueAt(fila, 0).toString());

                txtNombre.setText(
                        modelo.getValueAt(fila, 1).toString());
            }
        });

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        botones.add(btnRegistrar);
        botones.add(btnEditar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        add(botones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> registrar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());

        cargarTabla();
    }

    private void registrar() {

        String resultado =
                controlador.registrarRepartidor(txtNombre.getText());

        mostrarResultado(resultado);

        if ("OK".equals(resultado)) {
            cargarTabla();
            limpiar();
        }
    }

    private void editar() {

        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un repartidor.");
            return;
        }

        String resultado =
                controlador.actualizarRepartidor(
                        idSeleccionado,
                        txtNombre.getText());

        mostrarResultado(resultado);

        if ("OK".equals(resultado)) {
            cargarTabla();
            limpiar();
        }
    }

    private void eliminar() {

        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un repartidor.");
            return;
        }

        String resultado =
                controlador.eliminarRepartidor(idSeleccionado);

        mostrarResultado(resultado);

        if ("OK".equals(resultado)) {
            cargarTabla();
            limpiar();
        }
    }

    private void cargarTabla() {

        modelo.setRowCount(0);

        for (Repartidor r : controlador.obtenerRepartidores()) {

            modelo.addRow(new Object[]{
                r.getId(),
                r.getNombre()
            });
        }
    }

    private void limpiar() {
        idSeleccionado = -1;
        txtNombre.setText("");
        tabla.clearSelection();
    }

    private void mostrarResultado(String resultado) {

        if ("OK".equals(resultado)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Operación realizada correctamente.");
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    resultado,
                    "Atención",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}