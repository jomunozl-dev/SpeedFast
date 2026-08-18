import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

/**
 * Clase principal del sistema SpeedFast.
 * @author Jorge Munoz Leon
 */
public class Main {

    public static void main(String[] args) {

        // Crear un pedido de comida
        PedidoComida comida = new PedidoComida(
                1,
                "Av. Alemania 123"
        );

        // Crear una encomienda
        PedidoEncomienda encomienda = new PedidoEncomienda(
                2,
                "Av. España 456"
        );

        // Crear una compra Express
        PedidoExpress express = new PedidoExpress(
                3,
                "Av. Los Carrera 789"
        );

        // Asignar repartidor al pedido de comida
        comida.asignarRepartidor();
        comida.asignarRepartidor("Juan Pérez");

        // Separación entre pedidos
        System.out.println();

        // Asignar repartidor a la encomienda
        encomienda.asignarRepartidor();
        encomienda.asignarRepartidor("Camila Soto");

        System.out.println();

        // Asignar repartidor a la compra Express
        express.asignarRepartidor();
        express.asignarRepartidor("Luis Díaz");
    }
}