package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 * DAO para la entidad Cliente, la cual no se utilizara.
 *
 * Esta clase se incluye para dar cumplimiento al Paso 2
 * del enunciado, que solicita implementar ClienteDAO
 * con las operaciones CRUD.
 *
 * Cliente no forma parte de los requerimientos funcionales
 * principales de gestión de Repartidores, Pedidos y Entregas,
 * por lo que este DAO no se integra a la interfaz gráfica.
 */

public class ClienteDAO {

    /**
     * CREATE: registra un cliente.
     */
    public boolean create(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO clientes (nombre) VALUES (?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * READ: obtiene todos los clientes.
     */
    public List<Cliente> readAll() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id, nombre FROM clientes ORDER BY id";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println(
                    "Error al listar clientes: " + e.getMessage()
            );
        }

        return clientes;
    }

    /**
     * UPDATE: modifica un cliente.
     */
    public boolean update(Cliente cliente) throws SQLException {

        String sql =
                "UPDATE clientes SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setInt(2, cliente.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * DELETE: elimina un cliente por su ID.
     */
    public boolean delete(int id) throws SQLException {

        String sql =
                "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}