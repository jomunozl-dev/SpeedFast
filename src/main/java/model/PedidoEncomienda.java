package model;

/**
 * Representa un pedido de encomienda de SpeedFast.
 * 
 * @author Jorge Munoz Leon
 */
public class PedidoEncomienda extends Pedido {

    /**
     * Constructor de PedidoEncomienda.
     *
     * @param idPedido identificador del pedido
     * @param direccionEntrega dirección de entrega
     * @param distanciaKm distancia hasta el lugar de entrega
     */
    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Calcula el tiempo estimado de entrega para una encomienda.
     *
     * Fórmula:
     * 20 minutos base + 1.5 minutos por cada kilómetro.
     *
     * El resultado se redondea a un número entero.
     *
     * @return tiempo estimado en minutos
     */
    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(20 + (1.5 * distanciaKm));
    }
}