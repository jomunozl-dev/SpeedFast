package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Entrega;

/**
 * DAO encargado de realizar las operaciones CRUD
 * de las entregas de SpeedFast.
 *
 * @author Jorge Munoz Leon
 */
public class EntregaDAO {

    // Formato utilizado para almacenar la hora en Oracle.
    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * CREATE
     * Registra una nueva entrega.
     */
    public boolean create(Entrega entrega) throws SQLException {

        String sql = "INSERT INTO entregas "
                + "(id_pedido, id_repartidor, fecha, hora) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());

            stmt.setDate(
                    3,
                    Date.valueOf(entrega.getFecha())
            );

            stmt.setString(
                    4,
                    entrega.getHora().format(FORMATO_HORA)
            );

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * READ
     * Obtiene todas las entregas registradas.
     */
    public List<Entrega> readAll() {

        List<Entrega> lista = new ArrayList<>();

        String sql = "SELECT id, id_pedido, id_repartidor, "
                + "fecha, hora "
                + "FROM entregas "
                + "ORDER BY id";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                LocalTime hora = LocalTime.parse(
                        rs.getString("hora"),
                        FORMATO_HORA
                );

                Entrega entrega = new Entrega(
                        rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_repartidor"),
                        rs.getDate("fecha").toLocalDate(),
                        hora
                );

                lista.add(entrega);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error al listar entregas: "
                    + e.getMessage()
            );

        } catch (Exception e) {

            System.err.println(
                    "Error al convertir datos de entrega: "
                    + e.getMessage()
            );
        }

        return lista;
    }

    /**
     * UPDATE
     * Modifica una entrega existente.
     */
    public boolean update(Entrega entrega) throws SQLException {

        String sql = "UPDATE entregas SET "
                + "id_pedido = ?, "
                + "id_repartidor = ?, "
                + "fecha = ?, "
                + "hora = ? "
                + "WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());

            stmt.setDate(
                    3,
                    Date.valueOf(entrega.getFecha())
            );

            stmt.setString(
                    4,
                    entrega.getHora().format(FORMATO_HORA)
            );

            stmt.setInt(5, entrega.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * DELETE
     * Elimina una entrega según su ID.
     */
    public boolean delete(int id) throws SQLException {

        String sql = "DELETE FROM entregas WHERE id = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}