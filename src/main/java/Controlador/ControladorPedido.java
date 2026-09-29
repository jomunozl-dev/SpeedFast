package Controlador;

import dao.PedidoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Pedido;

public class ControladorPedido {

    private PedidoDAO pedidoDAO;

    public ControladorPedido() {
        this.pedidoDAO = new PedidoDAO();
    }

    public String registrarPedido(String direccion, Pedido.TipoPedido tipo) {
        
        if (direccion == null || direccion.trim().isEmpty()) {
            return "La dirección no puede estar vacía.";
        }

        if (direccion.trim().length() < 5) {
            return "La dirección debe tener al menos 5 caracteres.";
        }

        if (tipo == null) {
            return "Debe seleccionar un tipo de pedido válido.";
        }

       
        try {
            Pedido nuevoPedido = new Pedido(0, direccion.trim(), tipo);
            boolean guardado = pedidoDAO.guardar(nuevoPedido);
            
            if (guardado) {
                return "OK";
            } else {
                return "No se pudo insertar el registro en la base de datos.";
            }
        } catch (SQLException e) {
            
            return "Error de conexión con la Base de Datos: " + e.getMessage();
        }
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoDAO.listarTodos();
    }
}