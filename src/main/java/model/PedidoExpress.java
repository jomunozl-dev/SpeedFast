package model;

/**
 * Representa una compra Express.
 * @author Jorge Munoz Leon
 */
public class PedidoExpress extends Pedido {

    /**
     * Constructor de PedidoExpress.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     */
    public PedidoExpress(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Express");
    }

    /**
     * Sobrescribe el método de asignación de repartidor.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Express]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata");
        System.out.println("encontrado.");
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