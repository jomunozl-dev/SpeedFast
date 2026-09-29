package gui;

import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("SpeedFast - Sistema de Gestión de Entregas");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título Principal
        JLabel lblTitulo = new JLabel("SpeedFast - Menú Principal", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel de Botones del Menú
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 40, 20, 40));

        JButton btnRegistrarPedido = new JButton("Registrar Pedido");
        JButton btnRegistrarRepartidor = new JButton("Registrar Repartidor");
        JButton btnListarPedidos = new JButton("Listar Pedidos");

        panelBotones.add(btnRegistrarPedido);
        panelBotones.add(btnRegistrarRepartidor);
        panelBotones.add(btnListarPedidos);

        add(panelBotones, BorderLayout.CENTER);

        // Eventos para abrir las ventanas correspondientes
        btnRegistrarPedido.addActionListener(e -> new VentanaRegistroPedido().setVisible(true));
        btnRegistrarRepartidor.addActionListener(e -> new VentanaRegistroRepartidor().setVisible(true));
        btnListarPedidos.addActionListener(e -> new VentanaListaPedidos().setVisible(true));
    }
}