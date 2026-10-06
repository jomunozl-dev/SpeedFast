package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;

/**
 * DAO encargado de realizar las operaciones CRUD
 * de los pedidos en speed fast
 */

public class PedidoDAO {

    // CREATE
    public boolean create(Pedido pedido) throws SQLException {

        String sql = "INSERT INTO pedidos "
                + "(direccion, tipo, estado) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getTipo().name());
            stmt.setString(3, pedido.getEstado().name());

            return stmt.executeUpdate() > 0;
        }
    }

    // READ
    public List<Pedido> readAll() {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT id, direccion, tipo, estado "
                + "FROM pedidos ORDER BY id";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        Pedido.TipoPedido.valueOf(rs.getString("tipo")),
                        Pedido.Estado.valueOf(rs.getString("estado"))
                );

                lista.add(pedido);
            }

        } catch (SQLException e) {
            System.err.println(
                    "Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE
    public boolean update(Pedido pedido) throws SQLException {

        String sql = "UPDATE pedidos "
                + "SET direccion = ?, tipo = ?, estado = ? "
                + "WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getTipo().name());
            stmt.setString(3, pedido.getEstado().name());
            stmt.setInt(4, pedido.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}