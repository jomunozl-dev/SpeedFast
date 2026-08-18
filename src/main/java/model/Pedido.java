package model;

/**
 * Clase base que representa un pedido genérico.
 * @author Jorge Munoz Leon
 */
public class Pedido {

    protected int idPedido;
    protected String direccionEntrega;
    protected String tipoPedido;

    /**
     * Constructor de la clase Pedido.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param tipoPedido tipo de pedido
     */
    public Pedido(int idPedido, String direccionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    /**
     * Método que asigna un repartidor.
     * Será sobrescrito por las clases derivadas.
     */
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor...");
    }

    /**
     * Método sobrecargado para asignar un repartidor específico.
     *
     * @param nombreRepartidor nombre del repartidor
     */
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Pedido asignado a " + nombreRepartidor);
    }
}