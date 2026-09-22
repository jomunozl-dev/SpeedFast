/**
 *
 * @author Jorge Munoz Leon
 */

package servicio;

import java.util.LinkedList;
import java.util.List;
import model.Pedido;

public class ZonaDeCarga {
    private final List<Pedido> pedidosPendientes;

    public ZonaDeCarga() {
        this.pedidosPendientes = new LinkedList<>();
        System.out.println("[Zona de carga inicializada]"); // Texto exacto
    }

    public synchronized void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccionEntrega());
        notifyAll();
    }

    public synchronized Pedido retirarPedido() {
        while (pedidosPendientes.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return pedidosPendientes.remove(0);
    }

    public synchronized int getPedidosPendientesCount() {
        return this.pedidosPendientes.size();
    }
    
    public synchronized List<Pedido> obtenerTodosLosPedidos() {
        return new java.util.ArrayList<>(pedidosPendientes);
    }
}