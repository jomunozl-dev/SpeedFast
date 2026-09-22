/**
 *
 * @author Jorge Munoz Leon
 */

package model;

public class Pedido {
    
    public enum Estado{
        PENDIENTE,
        EN_REPARTO,
        ENTREGADO
    }
    public enum TipoPedido{
        COMIDA,
        ENCOMIENDA,
        EXPRESS
    }
    
    private int id;
    private String direccionEntrega;
    private Estado estado;
    private TipoPedido tipo;
    
    public Pedido (int id, String direccionEntrega, TipoPedido tipo){
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = estado.PENDIENTE;
        this.tipo = tipo;
    }
    
    //get and setters de id
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    
    //get and setters de direccionEntrega
    public void setDireccionEntrega(String direccionEntrega){
        this.direccionEntrega = direccionEntrega;
    }
    public String getDireccionEntrega(){
        return this.direccionEntrega;
    }
    
    //get and setters estado
    public void setEstado(Estado estado){
        this.estado = estado;
    }
    public Estado getEstado(){
        return this.estado;
    }
    
    //get and setters estado
    public void setTipoPedido(TipoPedido tipo){
        this.tipo = tipo;
    }
    public TipoPedido getTipo(){
        return tipo;
    }
    
    @Override
    public String toString() {
        return "Pedido #" + id + " [" + tipo + "] -> " + direccionEntrega + " (" + estado + ")";
    }
}
