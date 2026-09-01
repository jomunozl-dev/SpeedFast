package interfaces;

/**
 * Define el comportamiento necesario para que
 * un pedido pueda ser despachado.
 * @author Jorge Munoz Leon
 */
public interface Despachable {

    /**
     * Realiza el despacho de un pedido.
     */
    void despachar();
}