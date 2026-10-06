package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de establecer la conexión
 * con Oracle Cloud mediante Wallet, ya que no logre que me funcionara oracle cloud de forma local.
 *Base de datos: El proyecto utiliza Oracle Autonomous Database (Oracle Cloud) mediante JDBC y Wallet. 
 * El esquema entregado originalmente para MySQL fue adaptado a sintaxis Oracle, conservando las entidades, 
 * relaciones y reglas funcionales solicitadas. El archivo database/speedfast_oracle.sql 
 * contiene el script necesario para crear las tablas REPARTIDORES, PEDIDOS y ENTREGAS.
 * 
 * @author Jorge Munoz Leon
 */

public class ConexionDB {

    // Ruta donde se encuentra descomprimido el Wallet.
    private static final String WALLET_PATH =
            "/ruta/a/su/wallet";

    // Alias definido en el archivo tnsnames.ora.
    private static final String URL =
            "jdbc:oracle:thin:@evsumativa3_high?TNS_ADMIN="
            + WALLET_PATH;

    // Usuario de Oracle Cloud.
    private static final String USER = "ADMIN";

    // Contraseña de Oracle Cloud.
    private static final String PASSWORD = "SU_password_1234$";

    /**
     * Establece una conexión con la base de datos Oracle Cloud.
     *
     * @return conexión activa con Oracle Cloud
     * @throws SQLException si ocurre un error de conexión
     */
    public static Connection conectar() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}