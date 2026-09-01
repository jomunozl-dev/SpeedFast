package model;

/**
 * Representa un pedido de comida dentro de SpeedFast.
 * Hereda los atributos y comportamientos comunes de Pedido.
 * @author Jorge Munoz Leon
 */
public class PedidoComida extends Pedido {

    /**
     * Constructor de PedidoComida.
     *
     * @param numeroPedido número identificador del pedido.
     * @param direccion dirección donde se entrega el pedido.
     * @param distancia distancia de la entrega en kilómetros.
     */
    public PedidoComida(
            int numeroPedido,
            String direccion,
            double distancia) {

        // Llama al constructor de la clase padre.
        super(numeroPedido, direccion, distancia);
    }

    /**
     * Asigna automáticamente un repartidor para un pedido
     * de comida.
     *
     * Este método sobrescribe el método abstracto
     * definido en la clase Pedido.
     */
    @Override
    public void asignarRepartidor() {

        repartidor = "Luis Díaz";

        System.out.println(
                "Asignando repartidor para pedido de comida..."
        );

        System.out.println(
                "→ Verificando mochila térmica... OK"
        );

        System.out.println(
                "→ Pedido asignado a " + repartidor
        );
    }

    /**
     * Calcula el tiempo estimado de entrega para comida.
     *
     * @return tiempo estimado en minutos.
     */
    @Override
    public int calcularTiempoEntrega() {

        return 20 + (int) distancia * 2;
    }
}