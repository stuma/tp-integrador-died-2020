package Model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name= "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer cantidad;

    @Column
    private Integer puntoPedido;

    @Column
    @ManyToOne(cascade=CascadeType.ALL)
    private Insumo insumo;

/*    @Column(name = "insumoGeneral_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private InsumoGeneral insumoGeneral;

    @Column(name = "insumoLiquido_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private InsumoLiquido insumoLiquido;*/

    @Column(name = "planta_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Planta planta;

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

/*    public InsumoGeneral getInsumoGeneral() {
        return insumoGeneral;
    }

    public void setInsumoGeneral(InsumoGeneral insumoGeneral) {
        this.insumoGeneral = insumoGeneral;
    }

    public InsumoLiquido getInsumoLiquido() {
        return insumoLiquido;
    }

    public void setInsumoLiquido(InsumoLiquido insumoLiquido) {
        this.insumoLiquido = insumoLiquido;
    }*/

    public Planta getPlanta(){
        return this.planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }
}
