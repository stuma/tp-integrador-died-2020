package Model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer cantidad;

    //@Column
    @ManyToOne(cascade=CascadeType.ALL)
    private Insumo insumo;
/*
    @Column(name = "insumoLiquido_id")
    @ManyToOne(cascade=CascadeType.ALL)
    private InsumoLiquido insumoLiquido;

    @Column(name = "insumoGeneral_id")
    @ManyToOne(cascade=CascadeType.ALL)
    private InsumoGeneral insumoGeneral;
*/

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

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", insumo=" + insumo +
                '}';
    }
}
