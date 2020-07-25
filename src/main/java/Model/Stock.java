package Model;

public class Stock {

    private Integer id;
    private Integer cantidad;
    private Integer puntoPedido;
    private Insumo insumo;

    public Stock() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPuntoPedido() {
        return puntoPedido;
    }

    public void setPuntoPedido(Integer puntoPedido) {
        this.puntoPedido = puntoPedido;
    }
}
