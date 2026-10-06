package Controlador;

import dao.RepartidorDAO;
import java.sql.SQLException;
import java.util.List;
import model.Repartidor;

/**
 * Controlador encargado de gestionar repartidores en speed fast
 */
public class ControladorRepartidor {

    private RepartidorDAO repartidorDAO;

    public ControladorRepartidor() {
        repartidorDAO = new RepartidorDAO();
    }

    public String registrarRepartidor(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre no puede estar vacío.";
        }

        if (nombre.trim().length() < 3) {
            return "El nombre debe tener al menos 3 caracteres.";
        }

        try {

            Repartidor repartidor =
                    new Repartidor(0, nombre.trim());

            if (repartidorDAO.create(repartidor)) {
                return "OK";
            }

            return "No se pudo registrar el repartidor.";

        } catch (SQLException e) {
            return "Error de Base de Datos: " + e.getMessage();
        }
    }

    public String actualizarRepartidor(int id, String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre no puede estar vacío.";
        }

        if (nombre.trim().length() < 3) {
            return "El nombre debe tener al menos 3 caracteres.";
        }

        try {

            Repartidor repartidor =
                    new Repartidor(id, nombre.trim());

            if (repartidorDAO.update(repartidor)) {
                return "OK";
            }

            return "No se pudo actualizar el repartidor.";

        } catch (SQLException e) {
            return "Error de Base de Datos: " + e.getMessage();
        }
    }

    public String eliminarRepartidor(int id) {

        try {

            if (repartidorDAO.delete(id)) {
                return "OK";
            }

            return "No se pudo eliminar el repartidor.";

        } catch (SQLException e) {
            return "Error de Base de Datos: " + e.getMessage();
        }
    }

    public List<Repartidor> obtenerRepartidores() {
        return repartidorDAO.readAll();
    }
}