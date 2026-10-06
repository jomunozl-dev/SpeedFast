package Controlador;

import dao.PedidoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Pedido;

/**
 * Controlador encargado de gestionar los pedidos en speed fast
 */

public class ControladorPedido {

    private PedidoDAO pedidoDAO;

    public ControladorPedido() {
        pedidoDAO = new PedidoDAO();
    }

    public String registrarPedido(
            String direccion,
            Pedido.TipoPedido tipo,
            Pedido.Estado estado) {

        if (direccion == null || direccion.trim().isEmpty()) {
            return "La dirección no puede estar vacía.";
        }

        if (direccion.trim().length() < 5) {
            return "La dirección debe tener al menos 5 caracteres.";
        }

        if (tipo == null) {
            return "Debe seleccionar un tipo.";
        }

        if (estado == null) {
            return "Debe seleccionar un estado.";
        }

        try {

            Pedido pedido = new Pedido(
                    0,
                    direccion.trim(),
                    tipo,
                    estado
            );

            if (pedidoDAO.create(pedido)) {
                return "OK";
            }

            return "No se pudo registrar el pedido.";

        } catch (SQLException e) {
            return "Error de Base de Datos: " + e.getMessage();
        }
    }

    public String actualizarPedido(
            int id,
            String direccion,
            Pedido.TipoPedido tipo,
            Pedido.Estado estado) {

        if (direccion == null || direccion.trim().isEmpty()) {
            return "La dirección no puede estar vacía.";
        }

        if (direccion.trim().length() < 5) {
            return "La dirección debe tener al menos 5 caracteres.";
        }

        try {

            Pedido pedido = new Pedido(
                    id,
                    direccion.trim(),
                    tipo,
                    estado
            );

            if (pedidoDAO.update(pedido)) {
                return "OK";
            }

            return "No se pudo actualizar el pedido.";

        } catch (SQLException e) {
            return "Error de Base de Datos: " + e.getMessage();
        }
    }

    public String eliminarPedido(int id) {

        try {

            if (pedidoDAO.delete(id)) {
                return "OK";
            }

            return "No se pudo eliminar el pedido.";

        } catch (SQLException e) {
            return "Error de Base de Datos: " + e.getMessage();
        }
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoDAO.readAll();
    }
}