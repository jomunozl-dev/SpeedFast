package model;

/**
 * Clase abstracta que representa un pedido general de SpeedFast.
 * Contiene los atributos y comportamientos comunes para los
 * distintos tipos de pedidos.
 * @author Jorge Munoz Leon
 */
public abstract class Pedido {

    // Identificador único del pedido.
    protected int numeroPedido;

    // Dirección donde se debe realizar la entrega.
    protected String direccion;

    // Distancia entre el origen y el destino de la entrega.
    protected double distancia;

    // Nombre del repartidor asignado al pedido.
    protected String repartidor;

    /**
     * Constructor de la clase Pedido.
     *
     * @param numeroPedido número identificador del pedido.
     * @param direccion dirección de entrega.
     * @param distancia distancia de la entrega en kilómetros.
     */
    public Pedido(int numeroPedido, String direccion, double distancia) {
        this.numeroPedido = numeroPedido;
        this.direccion = direccion;
        this.distancia = distancia;
        this.repartidor = "Sin asignar";
    }

    /**
     * Asigna automáticamente un repartidor.
     * Cada clase hija debe implementar este método
     * según sus propias reglas de negocio.
     */
    public abstract void asignarRepartidor();

    /**
     * Asigna manualmente un repartidor mediante su nombre.
     * Este método representa una sobrecarga del método
     * asignarRepartidor().
     *
     * @param nombre nombre del repartidor que será asignado.
     */
    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;

        System.out.println(
                "Repartidor asignado manualmente: " + nombre
        );
    }

    /**
     * Calcula el tiempo estimado de entrega.
     * Cada tipo de pedido implementa su propio cálculo.
     *
     * @return tiempo estimado de entrega en minutos.
     */
    public abstract int calcularTiempoEntrega();

    /**
     * Muestra la información principal del pedido.
     * Este método es común para todos los tipos de pedidos.
     */
    public void mostrarResumen() {

        System.out.println("Pedido #" + numeroPedido);
        System.out.println("Dirección: " + direccion);
        System.out.println("Distancia: " + distancia + " km");
        System.out.println("Repartidor asignado: " + repartidor);
        System.out.println(
                "Tiempo estimado: "
                + calcularTiempoEntrega()
                + " minutos"
        );
    }

    /**
     * Obtiene el número del pedido.
     *
     * @return número del pedido.
     */
    public int getNumeroPedido() {
        return numeroPedido;
    }

    /**
     * Obtiene el repartidor asignado.
     *
     * @return nombre del repartidor.
     */
    public String getRepartidor() {
        return repartidor;
    }
}