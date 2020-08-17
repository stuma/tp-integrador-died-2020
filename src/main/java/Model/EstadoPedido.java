package Model;

import javax.persistence.*;

@Entity
@Table(name= "estadopedido")
public class EstadoPedido {

    @Id
    @Column(name = "id")
     @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    public EstadoPedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
