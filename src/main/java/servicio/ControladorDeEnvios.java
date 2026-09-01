package servicio;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;
import model.Pedido;

import java.util.ArrayList;

/**
 * Controla las operaciones relacionadas con el envío
 * de los pedidos.
 *
 * Implementa las interfaces Despachable, Cancelable
 * y Rastreable para separar las distintas responsabilidades.
 * @author Jorge Munoz Leon
 */
public class ControladorDeEnvios
        implements Despachable, Cancelable, Rastreable {

    // Pedido que será administrado por el controlador.
    private Pedido pedido;

    /*
     * Lista que almacena el historial de entregas.
     *
     * Se declara static para que el historial sea compartido
     * entre los distintos controladores creados durante
     * la ejecución del programa.
     */
    private static ArrayList<String> historial
            = new ArrayList<>();

    /**
     * Constructor del controlador.
     *
     * @param pedido pedido que será administrado.
     */
    public ControladorDeEnvios(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Despacha el pedido y agrega la entrega
     * al historial.
     */
    @Override
    public void despachar() {

        System.out.println(
                "Pedido despachado correctamente."
        );

        // Se registra la entrega en el historial.
        historial.add(
                "Pedido"
                + pedido.getClass().getSimpleName()
                + " #"
                + pedido.getNumeroPedido()
                + " – entregado por "
                + pedido.getRepartidor()
        );
    }

    /**
     * Cancela el pedido actual.
     */
    @Override
    public void cancelar() {

        System.out.println(
                "Cancelando "
                + pedido.getClass().getSimpleName()
                + " #"
                + pedido.getNumeroPedido()
                + "..."
        );

        System.out.println(
                "→ Pedido cancelado exitosamente."
        );
    }

    /**
     * Muestra todos los pedidos que han sido
     * registrados como entregados.
     */
    @Override
    public void verHistorial() {

        System.out.println("Historial:");

        // Recorre la lista y muestra cada registro.
        for (String registro : historial) {

            System.out.println("- " + registro);
        }
    }
}