package model;

/**
 * Clase abstracta que representa un pedido genérico de SpeedFast.
 *
 * Contiene los atributos y comportamientos comunes
 * para los distintos tipos de pedidos.
 * 
 * @author Jorge Munoz Leon
 */

public abstract class Pedido {

    // Atributos comunes de todos los pedidos
    protected int idPedido;
    protected String direccionEntrega;
    protected double distanciaKm;

    /**
     * Constructor de la clase Pedido.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección donde se realizará la entrega
     * @param distanciaKm distancia hasta el lugar de entrega
     */
    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    /**
     * Muestra la información básica del pedido.
     */
    public void mostrarResumen() {
        System.out.println("ID del pedido: " + idPedido);
        System.out.println("Dirección de entrega: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    /**
     * Calcula el tiempo estimado de entrega.
     *
     * Cada clase derivada debe implementar este método
     * de acuerdo con sus propias reglas.
     *
     * @return tiempo estimado de entrega en minutos
     */
    public abstract int calcularTiempoEntrega();
}