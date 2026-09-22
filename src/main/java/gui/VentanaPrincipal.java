/**
 *
 * @author Jorge Munoz Leon
 */
package gui;

import java.awt.*;
import javax.swing.*;
import servicio.Repartidor;
import servicio.ZonaDeCarga;

public class VentanaPrincipal extends JFrame {

    private final ZonaDeCarga zonaDeCarga;

    public VentanaPrincipal(ZonaDeCarga zona) {
        this.zonaDeCarga = zona;

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout Principal
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Sistema SpeedFast", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel Central con botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton btnRegistrar = new JButton("Registrar Pedido");
        JButton btnListar = new JButton("Listar Pedidos");
        JButton btnIniciarReparto = new JButton("Asignar Repartidores / Iniciar Reparto");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnIniciarReparto);

        add(panelBotones, BorderLayout.CENTER);

        // Eventos de Navegación
        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido vRegistro = new VentanaRegistroPedido(zonaDeCarga);
            vRegistro.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            VentanaListaPedidos vLista = new VentanaListaPedidos(zonaDeCarga);
            vLista.setVisible(true);
        });

        btnIniciarReparto.addActionListener(e -> {
            if (zonaDeCarga.getPedidosPendientesCount() == 0) {
                JOptionPane.showMessageDialog(this, "No hay pedidos pendientes para repartir.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Iniciar hilos de reparto en segundo plano
            Thread t1 = new Thread(new Repartidor("Repartidor - Juan", zonaDeCarga));
            Thread t2 = new Thread(new Repartidor("Repartidor - Camila", zonaDeCarga));
            Thread t3 = new Thread(new Repartidor("Repartidor - Pedro", zonaDeCarga));

            t1.start();
            t2.start();
            t3.start();

            JOptionPane.showMessageDialog(this, "Hilos de reparto iniciados exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}