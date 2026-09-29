package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    //Crear la base de datos en el equipo local donde se ejecutara el programa speedfast
    
    /*
     Script Sql para crear la base de datos en el equipo local y usar realizar test del programa
    
    CREATE DATABASE IF NOT EXISTS speedfast;
    USE speedfast_db;
    CREATE TABLE repartidor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
    );
    CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(150) NOT NULL,
    tipo VARCHAR(30) NOT NULL, -- COMIDA | ENCOMIENDA | EXPRESS
    estado VARCHAR(20) NOT NULL -- PENDIENTE | EN_REPARTO | ENTREGADO
    );
    CREATE TABLE entrega (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_repartidor INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id),
    FOREIGN KEY (id_repartidor) REFERENCES repartidor(id)
    );

    */
    private static final String URL = "jdbc:mysql://localhost:3306/speedfast_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "ADMIN"; // se ingresa segun la maquina donde se vaya a ejecutar el programa 
    private static final String PASSWORD = "ADMIN"; //

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver de MySQL no encontrado.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}