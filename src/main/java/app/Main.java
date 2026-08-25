package app;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

/**
 * 
 * Clase principal que prueba los distintos tipos de pedidos.
 * 
 * @author Jorge Munoz Leon
 * 
 */

public class Main {

    public static void main(String[] args) {

        // Crear un pedido de comida de 5 km
        Pedido comida = new PedidoComida(
                1,
                "Av. Alemania 123",
                5
        );

        // Crear una encomienda de 5 km
        Pedido encomienda = new PedidoEncomienda(
                2,
                "Av. España 456",
                5
        );

        // Crear un pedido Express de 7 km
        Pedido express = new PedidoExpress(
                3,
                "Av. Los Carrera 789",
                7
        );

        System.out.println("===== PEDIDO COMIDA =====");

        // Mostrar información básica
        comida.mostrarResumen();

        // Calcular y mostrar tiempo estimado
        System.out.println("Tiempo estimado: "
                + comida.calcularTiempoEntrega() + " minutos");

        System.out.println();

        System.out.println("===== PEDIDO ENCOMIENDA =====");

        // Mostrar información básica
        encomienda.mostrarResumen();

        // Calcular y mostrar tiempo estimado
        System.out.println("Tiempo estimado: "
                + encomienda.calcularTiempoEntrega() + " minutos");

        System.out.println();

        System.out.println("===== PEDIDO EXPRESS =====");

        // Mostrar información básica
        express.mostrarResumen();

        // Calcular y mostrar tiempo estimado
        System.out.println("Tiempo estimado: "
                + express.calcularTiempoEntrega() + " minutos");
    }
}