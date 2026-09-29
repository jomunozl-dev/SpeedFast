package gui;

import Controlador.ControladorPedido;
import java.awt.*;
import javax.swing.*;
import model.Pedido;

public class VentanaRegistroPedido extends JFrame {

    private JTextField txtDireccion;
    private JComboBox<Pedido.TipoPedido> cbTipo;
    private ControladorPedido controlador;

    public VentanaRegistroPedido() {
        this.controlador = new ControladorPedido(); 

        setTitle("Registrar Pedido");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panelForm.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelForm.add(txtDireccion);

        panelForm.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(Pedido.TipoPedido.values());
        panelForm.add(cbTipo);

        add(panelForm, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar Pedido");
        btnGuardar.addActionListener(e -> guardar());
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void guardar() {
        String direccion = txtDireccion.getText();
        Pedido.TipoPedido tipo = (Pedido.TipoPedido) cbTipo.getSelectedItem();

        String resultado = controlador.registrarPedido(direccion, tipo);

        if ("OK".equals(resultado)) {
            JOptionPane.showMessageDialog(this, "Pedido registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (resultado.startsWith("Error de conexión")) {
            
            JOptionPane.showMessageDialog(this, resultado, "Error de Conexión BD", JOptionPane.ERROR_MESSAGE);
        } else {
            
            JOptionPane.showMessageDialog(this, resultado, "Atención en los Datos", JOptionPane.WARNING_MESSAGE);
        }
    }
}