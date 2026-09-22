/**
 *
 * @author Jorge Munoz Leon
 */

package servicio;
import model.Pedido;

public class Repartidor implements Runnable {
    // Atributos solicitados
    private String nombre;
    private ZonaDeCarga zonaDeCarga; // Referencia a la instancia compartida

    // Constructor para inyectar los atributos
    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                
                Pedido pedido = zonaDeCarga.retirarPedido();

                if (pedido != null) {
                    // 1. Mensaje de retiro
                    System.out.println("[" + nombre + "] Retirando pedido #" + pedido.getId() + "...");

                    // 2. Cambio a EN_REPARTO
                    pedido.setEstado(Pedido.Estado.EN_REPARTO);
                    System.out.println("[" + nombre + "] Estado: " + pedido.getEstado());

                    // 3. Cambio a Entregado a repartidor, no se ingreso como estado en enum.
                    System.out.println("[" + nombre + "] Entregando pedido #" + pedido.getId() + "...");
                    Thread.sleep(1500);

                    // 4. Cambio a ENTREGADO A CLIENTE
                    pedido.setEstado(Pedido.Estado.ENTREGADO);
                    System.out.println("[" + nombre + "] Estado: " + pedido.getEstado());
                }
            }
        } catch (InterruptedException e) {
            // Se silencia la interrupción final para no ensuciar la salida
            Thread.currentThread().interrupt();
        }
    }

    public String getNombre() {
        return nombre;
    }
    
}
