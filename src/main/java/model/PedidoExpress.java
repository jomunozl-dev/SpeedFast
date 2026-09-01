package model;

/**
 * Representa un pedido de tipo Express.
 * Hereda los atributos y métodos comunes de Pedido.
 * @author Jorge Munoz Leon
 */
public class PedidoExpress extends Pedido {

    /**
     * Constructor de PedidoExpress.
     *
     * @param numeroPedido número identificador del pedido.
     * @param direccion dirección donde se realiza la entrega.
     * @param distancia distancia de la entrega en kilómetros.
     */
    public PedidoExpress(
            int numeroPedido,
            String direccion,
            double distancia) {

        // Llama al constructor de la clase padre.
        super(numeroPedido, direccion, distancia);
    }

    /**
     * Asigna automáticamente un repartidor para el pedido Express.
     *
     * Este método sobrescribe el comportamiento definido
     * en la clase Pedido.
     */
    @Override
    public void asignarRepartidor() {

        repartidor = "Luis Díaz";

        System.out.println(
                "Asignando repartidor para pedido express..."
        );

        System.out.println(
                "→ Buscando repartidor más cercano..."
        );

        System.out.println(
                "→ Pedido asignado a " + repartidor
        );
    }

    /**
     * Calcula el tiempo estimado para una entrega Express.
     *
     * @return tiempo estimado en minutos.
     */
    @Override
    public int calcularTiempoEntrega() {

        return 15 + (int) distancia;
    }
}