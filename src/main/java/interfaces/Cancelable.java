package interfaces;

/**
 * Define el comportamiento necesario para
 * cancelar un envío.
 * @author Jorge Munoz Leon
 */
public interface Cancelable {

    /**
     * Cancela el pedido o envío correspondiente.
     */
    void cancelar();
}