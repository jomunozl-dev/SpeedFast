package model;

/**
 *
 * @author Jorge MUnoz Leon
 */

public class Pedido {
    
    public enum Estado{
        PENDIENTE,
        EN_REPARTO,
        ENTREGADO
    }
    private int id;
    private String direccionEntrega;
    private Estado estado;
    
    public Pedido (int id, String direccionEntrega, Estado estado){
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = estado;
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

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", direccionEntrega=" + direccionEntrega + ", estado=" + estado + '}';
    }
}
