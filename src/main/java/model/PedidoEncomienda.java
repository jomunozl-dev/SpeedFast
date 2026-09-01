package model;

/**
 * Representa un pedido de tipo encomienda.
 * Hereda las características generales de Pedido.
 * @author Jorge Munoz Leon
 */
public class PedidoEncomienda extends Pedido {

    /**
     * Constructor de PedidoEncomienda.
     *
     * @param numeroPedido número identificador del pedido.
     * @param direccion dirección donde se entrega la encomienda.
     * @param distancia distancia de la entrega en kilómetros.
     */
    public PedidoEncomienda(
            int numeroPedido,
            String direccion,
            double distancia) {

        // Inicializa los atributos heredados.
        super(numeroPedido, direccion, distancia);
    }

    /**
     * Asigna automáticamente un repartidor para la encomienda.
     *
     * Este método sobrescribe la implementación de Pedido.
     */
    @Override
    public void asignarRepartidor() {

        repartidor = "Daniela Tapia";

        System.out.println(
                "Asignando repartidor para encomienda..."
        );

        System.out.println(
                "→ Validando peso y embalaje... OK"
        );

        System.out.println(
                "→ Pedido asignado a " + repartidor
        );
    }

    /**
     * Calcula el tiempo estimado de entrega de la encomienda.
     *
     * @return tiempo estimado en minutos.
     */
    @Override
    public int calcularTiempoEntrega() {

        return 16 + (int) distancia * 2;
    }
}