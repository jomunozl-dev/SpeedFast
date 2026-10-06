/**
 *
 * @author Jorge Munoz Leon
 */

package model;

public class Pedido {

    public enum Estado {
        PENDIENTE,
        EN_REPARTO,
        ENTREGADO
    }

    public enum TipoPedido {
        COMIDA,
        ENCOMIENDA,
        EXPRESS
    }

    private int id;
    private String direccionEntrega;
    private TipoPedido tipo;
    private Estado estado;

    public Pedido() {
    }

    public Pedido(int id, String direccionEntrega,
                  TipoPedido tipo, Estado estado) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Pedido(int id, String direccionEntrega, TipoPedido tipo) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo;
        this.estado = Estado.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipoPedido(TipoPedido tipo) {
        this.tipo = tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return id + " - " + direccionEntrega;
    }
}
