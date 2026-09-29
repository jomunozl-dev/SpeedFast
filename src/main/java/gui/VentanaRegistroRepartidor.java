package gui;

import Controlador.ControladorRepartidor;
import java.awt.*;
import javax.swing.*;

public class VentanaRegistroRepartidor extends JFrame {

    private JTextField txtNombre;
    private ControladorRepartidor controlador;

    public VentanaRegistroRepartidor() {
        this.controlador = new ControladorRepartidor();

        setTitle("Registrar Repartidor (MySQL)");
        setSize(380, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Formulario
        JPanel panelForm = new JPanel(new GridLayout(1, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelForm.add(new JLabel("Nombre Repartidor:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        add(panelForm, BorderLayout.CENTER);

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar Repartidor");
        btnGuardar.addActionListener(e -> guardar());
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void guardar() {
        String nombre = txtNombre.getText();
        String resultado = controlador.registrarRepartidor(nombre);

        if ("OK".equals(resultado)) {
            JOptionPane.showMessageDialog(this, "Repartidor registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (resultado.startsWith("Error de conexión")) {
            JOptionPane.showMessageDialog(this, resultado, "Error de Conexión BD", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Atención en los Datos", JOptionPane.WARNING_MESSAGE);
        }
    }
}