package Model;

public class Stock {

    private Integer id;
    private Integer cantidad;
    private Integer puntoPedido;
    private Planta planta;
    private Insumo insumo;

    public Stock() {
    }

    public Stock(Integer cantidad, Integer puntoPedido, Insumo insumo) {
        this.cantidad = cantidad;
        this.puntoPedido = puntoPedido;

        this.insumo = insumo;
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

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Planta getPlanta() {
        return this.planta;
    }

    public void setPlanta(Planta p) {
        this.planta=p;
    }
}
