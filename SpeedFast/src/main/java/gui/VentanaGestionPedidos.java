package gui;

import Controlador.ControladorPedido;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Pedido;

public class VentanaGestionPedidos extends JFrame {

    private JTextField txtDireccion;
    private JComboBox<Pedido.TipoPedido> cbTipo;
    private JComboBox<Pedido.Estado> cbEstado;

    private JTable tabla;
    private DefaultTableModel modelo;

    private ControladorPedido controlador;

    private int idSeleccionado = -1;

    public VentanaGestionPedidos() {

        controlador = new ControladorPedido();

        setTitle("Gestión de Pedidos");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formulario =
                new JPanel(new GridLayout(3, 2, 10, 10));

        formulario.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 5, 15));

        formulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        formulario.add(txtDireccion);

        formulario.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(Pedido.TipoPedido.values());
        formulario.add(cbTipo);

        formulario.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(Pedido.Estado.values());
        formulario.add(cbEstado);

        add(formulario, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new String[]{
                    "ID",
                    "Dirección",
                    "Tipo",
                    "Estado"
                }, 0);

        tabla = new JTable(modelo);

        tabla.getSelectionModel().addListSelectionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila >= 0) {

                idSeleccionado =
                        Integer.parseInt(
                                modelo.getValueAt(fila, 0).toString());

                txtDireccion.setText(
                        modelo.getValueAt(fila, 1).toString());

                cbTipo.setSelectedItem(
                        Pedido.TipoPedido.valueOf(
                                modelo.getValueAt(fila, 2).toString()));

                cbEstado.setSelectedItem(
                        Pedido.Estado.valueOf(
                                modelo.getValueAt(fila, 3).toString()));
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
                controlador.registrarPedido(
                        txtDireccion.getText(),
                        (Pedido.TipoPedido) cbTipo.getSelectedItem(),
                        (Pedido.Estado) cbEstado.getSelectedItem()
                );

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
                    "Seleccione un pedido.");
            return;
        }

        String resultado =
                controlador.actualizarPedido(
                        idSeleccionado,
                        txtDireccion.getText(),
                        (Pedido.TipoPedido) cbTipo.getSelectedItem(),
                        (Pedido.Estado) cbEstado.getSelectedItem()
                );

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
                    "Seleccione un pedido.");
            return;
        }

        String resultado =
                controlador.eliminarPedido(idSeleccionado);

        mostrarResultado(resultado);

        if ("OK".equals(resultado)) {
            cargarTabla();
            limpiar();
        }
    }

    private void cargarTabla() {

        modelo.setRowCount(0);

        for (Pedido p : controlador.obtenerTodosLosPedidos()) {

            modelo.addRow(new Object[]{
                p.getId(),
                p.getDireccionEntrega(),
                p.getTipo(),
                p.getEstado()
            });
        }
    }

    private void limpiar() {
        idSeleccionado = -1;
        txtDireccion.setText("");
        cbTipo.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
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