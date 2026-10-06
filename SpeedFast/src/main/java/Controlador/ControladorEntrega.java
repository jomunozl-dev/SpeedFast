package Controlador;

import dao.EntregaDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import model.Entrega;

/**
 * Controlador encargado de gestionar las entregas
 * de SpeedFast.
 *
 * @author Jorge Munoz Leon
 */
public class ControladorEntrega {

    private EntregaDAO entregaDAO;

    public ControladorEntrega() {
        entregaDAO = new EntregaDAO();
    }

    /**
     * Registra una nueva entrega.
     */
    public String registrarEntrega(
            int idPedido,
            int idRepartidor,
            LocalDate fecha,
            LocalTime hora) {

        // Validaciones
        if (idPedido <= 0) {
            return "Debe seleccionar un pedido.";
        }

        if (idRepartidor <= 0) {
            return "Debe seleccionar un repartidor.";
        }

        if (fecha == null) {
            return "Debe ingresar una fecha válida.";
        }

        if (hora == null) {
            return "Debe ingresar una hora válida.";
        }

        try {

            Entrega entrega = new Entrega(
                    0,
                    idPedido,
                    idRepartidor,
                    fecha,
                    hora
            );

            if (entregaDAO.create(entrega)) {
                return "OK";
            }

            return "No se pudo registrar la entrega.";

        } catch (SQLException e) {

            return "Error de Base de Datos: "
                    + e.getMessage();
        }
    }

    /**
     * Actualiza una entrega existente.
     */
    public String actualizarEntrega(
            int id,
            int idPedido,
            int idRepartidor,
            LocalDate fecha,
            LocalTime hora) {

        // Validaciones
        if (id <= 0) {
            return "Debe seleccionar una entrega.";
        }

        if (idPedido <= 0) {
            return "Debe seleccionar un pedido.";
        }

        if (idRepartidor <= 0) {
            return "Debe seleccionar un repartidor.";
        }

        if (fecha == null) {
            return "Debe ingresar una fecha válida.";
        }

        if (hora == null) {
            return "Debe ingresar una hora válida.";
        }

        try {

            Entrega entrega = new Entrega(
                    id,
                    idPedido,
                    idRepartidor,
                    fecha,
                    hora
            );

            if (entregaDAO.update(entrega)) {
                return "OK";
            }

            return "No se pudo actualizar la entrega.";

        } catch (SQLException e) {

            return "Error de Base de Datos: "
                    + e.getMessage();
        }
    }

    /**
     * Elimina una entrega.
     */
    public String eliminarEntrega(int id) {

        if (id <= 0) {
            return "Debe seleccionar una entrega.";
        }

        try {

            if (entregaDAO.delete(id)) {
                return "OK";
            }

            return "No se pudo eliminar la entrega.";

        } catch (SQLException e) {

            return "Error de Base de Datos: "
                    + e.getMessage();
        }
    }

    /**
     * Obtiene todas las entregas registradas.
     */
    public List<Entrega> obtenerEntregas() {
        return entregaDAO.readAll();
    }
}