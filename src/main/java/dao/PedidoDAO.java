package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;

public class PedidoDAO {

    public boolean guardar(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getTipo().name());
            stmt.setString(3, pedido.getEstado().name());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido ORDER BY id ASC";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                Pedido.TipoPedido tipo = Pedido.TipoPedido.valueOf(rs.getString("tipo"));
                Pedido.Estado estado = Pedido.Estado.valueOf(rs.getString("estado"));

                Pedido p = new Pedido(id, direccion, tipo);
                p.setEstado(estado);
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar o listar desde MySQL: " + e.getMessage());
        }
        return lista;
    }
}