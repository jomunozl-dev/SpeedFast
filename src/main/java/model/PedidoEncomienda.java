package model;

/**
 * Representa un pedido de encomienda.
 * @author Jorge Munoz Leon
 */
public class PedidoEncomienda extends Pedido {

    /**
     * Constructor de PedidoEncomienda.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Encomienda");
    }

    /**
     * Sobrescribe el método de asignación de repartidor.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Encomienda]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Validando peso y embalaje... OK");
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