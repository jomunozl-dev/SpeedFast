package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Repartidor;

/**
 * DAO encargado de realizar las operaciones CRUD
 * de los repartidores en speed fast
 */

public class RepartidorDAO {

    // CREATE
    public boolean create(Repartidor repartidor) throws SQLException {

        String sql = "INSERT INTO repartidores (nombre) VALUES (?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, repartidor.getNombre());

            return stmt.executeUpdate() > 0;
        }
    }

    // READ
    public List<Repartidor> readAll() {

        List<Repartidor> lista = new ArrayList<>();

        String sql = "SELECT id, nombre "
                + "FROM repartidores ORDER BY id";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Repartidor repartidor = new Repartidor(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );

                lista.add(repartidor);
            }

        } catch (SQLException e) {
            System.err.println(
                    "Error al listar repartidores: "
                    + e.getMessage());
        }

        return lista;
    }

    // UPDATE
    public boolean update(Repartidor repartidor) throws SQLException {

        String sql = "UPDATE repartidores "
                + "SET nombre = ? WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, repartidor.getNombre());
            stmt.setInt(2, repartidor.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM repartidores WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}