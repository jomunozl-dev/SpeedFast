package app;

import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;
import servicio.ControladorDeEnvios;

/**
 * Clase principal del sistema SpeedFast.
 *
 * Permite ejecutar una simulación de las operaciones
 * solicitadas en la actividad.
 * @author Jorge Munoz Leon
 */
public class Main {

    /**
     * Método principal del programa.
     *
     * @param args argumentos de línea de comandos.
     */
    public static void main(String[] args) {

        /*
         * POLIMORFISMO:
         *
         * Las variables son de tipo Pedido, pero los objetos
         * corresponden a diferentes clases derivadas.
         */
        Pedido pedidoComida =
                new PedidoComida(
                        101,
                        "Av. Providencia 123",
                        5
                );

        Pedido pedidoEncomienda =
                new PedidoEncomienda(
                        102,
                        "Av. Santa Rosa 567",
                        7
                );

        Pedido pedidoExpress =
                new PedidoExpress(
                        103,
                        "Av. Vicuña Mackenna 890",
                        4
                );


        // ------------------------------------------
        // PEDIDO DE COMIDA
        // ------------------------------------------

        System.out.println(
                "===== PEDIDO COMIDA ====="
        );

        /*
         * Se ejecuta la versión sobrescrita de
         * asignarRepartidor() de PedidoComida.
         */
        pedidoComida.asignarRepartidor();

        // Se muestra el resumen del pedido.
        pedidoComida.mostrarResumen();

        /*
         * Se crea un controlador para administrar
         * las operaciones del pedido.
         */
        ControladorDeEnvios controladorComida =
                new ControladorDeEnvios(pedidoComida);

        // Se despacha el pedido.
        controladorComida.despachar();


        System.out.println();


        // ------------------------------------------
        // PEDIDO DE ENCOMIENDA
        // ------------------------------------------

        System.out.println(
                "===== PEDIDO ENCOMIENDA ====="
        );

        /*
         * Se demuestra la sobreescritura:
         * se ejecuta la implementación de
         * PedidoEncomienda.
         */
        pedidoEncomienda.asignarRepartidor();

        // Muestra los datos del pedido.
        pedidoEncomienda.mostrarResumen();

        /*
         * SOBRECARGA:
         *
         * Ahora utilizamos la segunda versión
         * del método asignarRepartidor(),
         * que recibe un String.
         */
        pedidoEncomienda.asignarRepartidor(
                "Camila Soto"
        );

        // Se crea el controlador.
        ControladorDeEnvios controladorEncomienda =
                new ControladorDeEnvios(pedidoEncomienda);

        // Se despacha la encomienda.
        controladorEncomienda.despachar();


        System.out.println();


        // ------------------------------------------
        // PEDIDO EXPRESS
        // ------------------------------------------

        System.out.println(
                "===== PEDIDO EXPRESS ====="
        );

        // Asignación automática del repartidor.
        pedidoExpress.asignarRepartidor();

        // Muestra el resumen del pedido.
        pedidoExpress.mostrarResumen();

        // Se crea el controlador del pedido Express.
        ControladorDeEnvios controladorExpress =
                new ControladorDeEnvios(pedidoExpress);

        // Se cancela el pedido Express.
        controladorExpress.cancelar();


        System.out.println();


        // ------------------------------------------
        // HISTORIAL
        // ------------------------------------------

        System.out.println(
                "===== HISTORIAL DE ENTREGAS ====="
        );

        /*
         * Se consulta el historial mediante la interfaz
         * Rastreable implementada por el controlador.
         */
        controladorExpress.verHistorial();
    }
}