package model;

/**
 * Representa un pedido de comida.
 * @author Jorge Munoz Leon
 */
public class PedidoComida extends Pedido {

    /**
     * Constructor de PedidoComida.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     */
    public PedidoComida(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Comida");
    }

    /**
     * Sobrescribe el método de asignación de repartidor.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Comida]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Verificando mochila térmica... OK");
    }

    /**
     * Sobrecarga el método para asignar un repartidor específico.
     *
     * @param nombreRepartidor nombre del repartidor
     */
    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("→ Pedido asignado a " + nombreRepartidor);
    }
}