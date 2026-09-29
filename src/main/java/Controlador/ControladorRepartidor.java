package Controlador;

import dao.RepartidorDAO;
import java.sql.SQLException;
import java.util.List;

public class ControladorRepartidor {

    private RepartidorDAO repartidorDAO;

    public ControladorRepartidor() {
        this.repartidorDAO = new RepartidorDAO();
    }

    public String registrarRepartidor(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre del repartidor no puede estar vacío.";
        }

        if (nombre.trim().length() < 3) {
            return "El nombre debe tener al menos 3 caracteres.";
        }

        try {
            boolean guardado = repartidorDAO.guardar(nombre.trim());
            if (guardado) {
                return "OK";
            } else {
                return "No se pudo insertar el repartidor en la base de datos.";
            }
        } catch (SQLException e) {
            return "Error de conexión con la Base de Datos: " + e.getMessage();
        }
    }

    public List<String> obtenerNombresRepartidores() {
        return repartidorDAO.listarNombres();
    }
}