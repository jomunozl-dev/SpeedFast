package model;

/**
 * Representa un pedido Express de SpeedFast.
 * 
 * @author Jorge Munoz Leon
 * 
 */

public class PedidoExpress extends Pedido {

    /**
     * Constructor de PedidoExpress.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param distanciaKm distancia hasta el lugar de entrega
     */
    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Calcula el tiempo estimado de entrega para un pedido Express.
     *
     * El tiempo base es de 10 minutos.
     * Si la distancia es mayor a 5 km, se agregan 5 minutos.
     *
     * @return tiempo estimado en minutos
     */
    @Override
    public int calcularTiempoEntrega() {

        int tiempo = 10;

        // Se agregan 5 minutos si la distancia supera los 5 km
        if (distanciaKm > 5) {
            tiempo += 5;
        }

        return tiempo;
    }
}