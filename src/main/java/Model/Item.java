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

    @Column(name = "insumo_id")
    @ManyToOne(cascade=CascadeType.ALL)
    private Insumo insumo;

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
}
