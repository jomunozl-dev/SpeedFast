package gui;

import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {

        setTitle("SpeedFast - Sistema de Gestión");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo =
                new JLabel(
                        "SpeedFast - Menú Principal",
                        SwingConstants.CENTER);

        lblTitulo.setFont(
                new Font("Arial", Font.BOLD, 18));

        lblTitulo.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 0, 5, 0));

        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones =
                new JPanel(new GridLayout(3, 1, 10, 10));

        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 40, 20, 40));

        JButton btnPedidos =
                new JButton("Gestión de Pedidos");

        JButton btnRepartidores =
                new JButton("Gestión de Repartidores");

        JButton btnEntregas =
                new JButton("Gestión de Entregas");

        panelBotones.add(btnPedidos);
        panelBotones.add(btnRepartidores);
        panelBotones.add(btnEntregas);

        add(panelBotones, BorderLayout.CENTER);

        btnPedidos.addActionListener(
                e -> new VentanaGestionPedidos()
                        .setVisible(true));

        btnRepartidores.addActionListener(
                e -> new VentanaGestionRepartidores()
                        .setVisible(true));

        btnEntregas.addActionListener(
                e -> new VentanaGestionEntregas()
                        .setVisible(true));
    }
}