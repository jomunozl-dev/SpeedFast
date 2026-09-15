package app;

import java.util.ArrayList;
import java.util.List;

import model.Pedido;
import servicio.Repartidor;
import servicio.ZonaDeCarga;

/**
 *
 * @author Jorge Munoz Leon
 */
public class Main {
    public static void main(String[] args) {
        // Inicializa la zona de carga
        ZonaDeCarga zona = new ZonaDeCarga();

        // Agrega los 5 pedidos con destinos
        zona.agregarPedido(new Pedido(1, "Santiago Centro", Pedido.Estado.PENDIENTE));
        zona.agregarPedido(new Pedido(2, "Providencia", Pedido.Estado.PENDIENTE));
        zona.agregarPedido(new Pedido(3, "Ñuñoa",  Pedido.Estado.PENDIENTE));
        zona.agregarPedido(new Pedido(4, "Recoleta",  Pedido.Estado.PENDIENTE));
        zona.agregarPedido(new Pedido(5, "Las Condes",  Pedido.Estado.PENDIENTE));

        // Crea los repartidores con el formato
        Repartidor r1 = new Repartidor("Repartidor - Juan", zona);
        Repartidor r2 = new Repartidor("Repartidor - Camila", zona);
        Repartidor r3 = new Repartidor("Repartidor - Pedro", zona);

        List<Thread> hilosRepartidores = new ArrayList<>();
        hilosRepartidores.add(new Thread(r1));
        hilosRepartidores.add(new Thread(r2));
        hilosRepartidores.add(new Thread(r3));

        for (Thread hilo : hilosRepartidores) {
            hilo.start();
        }

        while (zona.getPedidosPendientesCount() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Pequeña espera para asegurar que los hilos terminen de imprimir la última entrega
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (Thread hilo : hilosRepartidores) {
            hilo.interrupt();
        }

        for (Thread hilo : hilosRepartidores) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Todos los pedidos han sido entregados correctamente");
    }
}