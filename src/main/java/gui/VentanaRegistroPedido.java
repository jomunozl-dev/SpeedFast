/**
 *
 * @author Jorge Munoz Leon
 */

package gui;

import java.awt.*;
import javax.swing.*;
import model.Pedido;
import servicio.ZonaDeCarga;

public class VentanaRegistroPedido extends JFrame {

    private final ZonaDeCarga zonaDeCarga;
    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<Pedido.TipoPedido> cbTipo;

    public VentanaRegistroPedido(ZonaDeCarga zona) {
        this.zonaDeCarga = zona;

        setTitle("Registrar Nuevo Pedido");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelForm.add(new JLabel("ID Pedido:"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelForm.add(txtDireccion);

        panelForm.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(Pedido.TipoPedido.values());
        panelForm.add(cbTipo);

        add(panelForm, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarPedido());
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void guardarPedido() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String direccion = txtDireccion.getText().trim();
            Pedido.TipoPedido tipo = (Pedido.TipoPedido) cbTipo.getSelectedItem();

            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La dirección no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pedido nuevoPedido = new Pedido(id, direccion, tipo);
            zonaDeCarga.agregarPedido(nuevoPedido);

            JOptionPane.showMessageDialog(this, "Pedido #" + id + " registrado correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }
}
