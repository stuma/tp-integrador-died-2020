package Model;

import javax.persistence.*;

@MappedSuperclass
public class Insumo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String descripcion;

    @Column
    private String unidadMedida;

    @Column
    private Float costo;


    public Insumo() {
    }

    public Insumo(String descripcion, String unidadMedida, Float costo) {
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.costo = costo;
    }

    public float  pesoPorUnidad(){
        return 0;
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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }
}
