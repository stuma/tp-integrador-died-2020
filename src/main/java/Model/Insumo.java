package Model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
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

    @Column(nullable = true)
    private Float densidad;

    @Column(nullable = true)
    private Float peso;

    public Insumo() {
    }

    public Insumo(String descripcion, String unidadMedida, Float costo) {
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.costo = costo;
    }

    public float  pesoPorUnidad(){

        if(this.densidad==null){
            return this.peso;
        }else{
            return this.densidad;
        }

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

    public Float getDensidad() {
        return densidad;
    }

    public void setDensidad(Float densidad) {
        this.densidad = densidad;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Insumo{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
