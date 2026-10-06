package gui;

import Controlador.ControladorEntrega;
import Controlador.ControladorPedido;
import Controlador.ControladorRepartidor;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Entrega;
import model.Pedido;
import model.Repartidor;

/**
 * Ventana para realizar las operaciones CRUD
 * de las entregas de SpeedFast.
 *
 * @author Jorge Munoz Leon
 */
public class VentanaGestionEntregas extends JFrame {

    private JComboBox<Pedido> cbPedido;
    private JComboBox<Repartidor> cbRepartidor;

    private JTextField txtFecha;
    private JTextField txtHora;

    private JTable tabla;
    private DefaultTableModel modelo;

    private ControladorEntrega controladorEntrega;
    private ControladorPedido controladorPedido;
    private ControladorRepartidor controladorRepartidor;

    private int idSeleccionado = -1;

    public VentanaGestionEntregas() {

        controladorEntrega = new ControladorEntrega();
        controladorPedido = new ControladorPedido();
        controladorRepartidor = new ControladorRepartidor();

        setTitle("Gestión de Entregas");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // -------------------------
        // FORMULARIO
        // -------------------------

        JPanel formulario =
                new JPanel(new GridLayout(4, 2, 10, 10));

        formulario.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 5, 15));

        formulario.add(new JLabel("Pedido:"));

        cbPedido = new JComboBox<>();
        formulario.add(cbPedido);

        formulario.add(new JLabel("Repartidor:"));

        cbRepartidor = new JComboBox<>();
        formulario.add(cbRepartidor);

        formulario.add(
                new JLabel("Fecha (AAAA-MM-DD):"));

        txtFecha = new JTextField();
        formulario.add(txtFecha);

        formulario.add(
                new JLabel("Hora (HH:MM):"));

        txtHora = new JTextField();
        formulario.add(txtHora);

        add(formulario, BorderLayout.NORTH);

        // -------------------------
        // TABLA
        // -------------------------

        modelo = new DefaultTableModel(
                new String[]{
                    "ID",
                    "ID Pedido",
                    "ID Repartidor",
                    "Fecha",
                    "Hora"
                },
                0
        );

        tabla = new JTable(modelo);

        add(
                new JScrollPane(tabla),
                BorderLayout.CENTER
        );

        // Selección de una entrega desde la tabla.
        tabla.getSelectionModel()
                .addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int fila = tabla.getSelectedRow();

                if (fila >= 0) {

                    idSeleccionado =
                            Integer.parseInt(
                                    modelo.getValueAt(
                                            fila, 0
                                    ).toString()
                            );

                    int idPedido =
                            Integer.parseInt(
                                    modelo.getValueAt(
                                            fila, 1
                                    ).toString()
                            );

                    int idRepartidor =
                            Integer.parseInt(
                                    modelo.getValueAt(
                                            fila, 2
                                    ).toString()
                            );

                    seleccionarPedido(idPedido);

                    seleccionarRepartidor(
                            idRepartidor
                    );

                    txtFecha.setText(
                            modelo.getValueAt(
                                    fila, 3
                            ).toString()
                    );

                    txtHora.setText(
                            modelo.getValueAt(
                                    fila, 4
                            ).toString()
                    );
                }
            }
        });

        // -------------------------
        // BOTONES
        // -------------------------

        JPanel botones = new JPanel();

        JButton btnRegistrar =
                new JButton("Registrar");

        JButton btnEditar =
                new JButton("Editar");

        JButton btnEliminar =
                new JButton("Eliminar");

        JButton btnRefrescar =
                new JButton("Refrescar");

        botones.add(btnRegistrar);
        botones.add(btnEditar);
        botones.add(btnEliminar);
        botones.add(btnRefrescar);

        add(botones, BorderLayout.SOUTH);

        // Eventos de los botones.
        btnRegistrar.addActionListener(
                e -> registrar());

        btnEditar.addActionListener(
                e -> editar());

        btnEliminar.addActionListener(
                e -> eliminar());

        btnRefrescar.addActionListener(
                e -> actualizarDatos());

        // Carga inicial.
        actualizarDatos();
    }

    /**
     * Actualiza los JComboBox y la tabla.
     */
    private void actualizarDatos() {

        cargarCombos();
        cargarTabla();
    }

    /**
     * Carga los pedidos y repartidores
     * registrados en la base de datos.
     */
    private void cargarCombos() {

        cbPedido.removeAllItems();
        cbRepartidor.removeAllItems();

        for (Pedido pedido :
                controladorPedido.obtenerTodosLosPedidos()) {

            cbPedido.addItem(pedido);
        }

        for (Repartidor repartidor :
                controladorRepartidor.obtenerRepartidores()) {

            cbRepartidor.addItem(repartidor);
        }
    }

    /**
     * Registra una nueva entrega.
     */
    private void registrar() {

        Pedido pedido =
                (Pedido) cbPedido.getSelectedItem();

        Repartidor repartidor =
                (Repartidor) cbRepartidor.getSelectedItem();

        if (pedido == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (repartidor == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un repartidor.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (txtFecha.getText().trim().isEmpty()
                || txtHora.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar fecha y hora.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            LocalDate fecha =
                    LocalDate.parse(
                            txtFecha.getText().trim()
                    );

            LocalTime hora =
                    LocalTime.parse(
                            txtHora.getText().trim()
                    );

            String resultado =
                    controladorEntrega.registrarEntrega(
                            pedido.getId(),
                            repartidor.getId(),
                            fecha,
                            hora
                    );

            mostrarResultado(resultado);

            if ("OK".equals(resultado)) {

                actualizarDatos();
                limpiar();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Formato incorrecto.\n"
                    + "Fecha: AAAA-MM-DD\n"
                    + "Hora: HH:MM",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    /**
     * Edita la entrega seleccionada.
     */
    private void editar() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una entrega.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Pedido pedido =
                (Pedido) cbPedido.getSelectedItem();

        Repartidor repartidor =
                (Repartidor) cbRepartidor.getSelectedItem();

        if (pedido == null || repartidor == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar pedido y repartidor.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (txtFecha.getText().trim().isEmpty()
                || txtHora.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar fecha y hora.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            LocalDate fecha =
                    LocalDate.parse(
                            txtFecha.getText().trim()
                    );

            LocalTime hora =
                    LocalTime.parse(
                            txtHora.getText().trim()
                    );

            String resultado =
                    controladorEntrega.actualizarEntrega(
                            idSeleccionado,
                            pedido.getId(),
                            repartidor.getId(),
                            fecha,
                            hora
                    );

            mostrarResultado(resultado);

            if ("OK".equals(resultado)) {

                actualizarDatos();
                limpiar();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Formato incorrecto.\n"
                    + "Fecha: AAAA-MM-DD\n"
                    + "Hora: HH:MM",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    /**
     * Elimina la entrega seleccionada.
     */
    private void eliminar() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una entrega.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea eliminar la entrega seleccionada?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        String resultado =
                controladorEntrega.eliminarEntrega(
                        idSeleccionado
                );

        mostrarResultado(resultado);

        if ("OK".equals(resultado)) {

            actualizarDatos();
            limpiar();
        }
    }

    /**
     * Carga las entregas registradas
     * en la JTable.
     */
    private void cargarTabla() {

        modelo.setRowCount(0);

        for (Entrega entrega :
                controladorEntrega.obtenerEntregas()) {

            modelo.addRow(
                    new Object[]{
                        entrega.getId(),
                        entrega.getIdPedido(),
                        entrega.getIdRepartidor(),
                        entrega.getFecha(),
                        entrega.getHora()
                    }
            );
        }
    }

    /**
     * Selecciona en el JComboBox el pedido
     * correspondiente al ID.
     */
    private void seleccionarPedido(int id) {

        for (int i = 0;
                i < cbPedido.getItemCount();
                i++) {

            Pedido pedido =
                    cbPedido.getItemAt(i);

            if (pedido.getId() == id) {

                cbPedido.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * Selecciona en el JComboBox el repartidor
     * correspondiente al ID.
     */
    private void seleccionarRepartidor(int id) {

        for (int i = 0;
                i < cbRepartidor.getItemCount();
                i++) {

            Repartidor repartidor =
                    cbRepartidor.getItemAt(i);

            if (repartidor.getId() == id) {

                cbRepartidor.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * Limpia los campos después de una operación.
     */
    private void limpiar() {

        idSeleccionado = -1;

        txtFecha.setText("");
        txtHora.setText("");

        tabla.clearSelection();
    }

    /**
     * Muestra el resultado de una operación.
     */
    private void mostrarResultado(
            String resultado) {

        if ("OK".equals(resultado)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Operación realizada correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    resultado,
                    "Atención",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}