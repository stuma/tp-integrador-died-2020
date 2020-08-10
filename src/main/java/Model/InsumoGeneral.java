package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "insumogeneral")
public class InsumoGeneral extends Insumo{

    @Column
    private Float peso;

    public InsumoGeneral() {
    }

    public InsumoGeneral(String descripcion, String unidadMedida, Float costo, Float peso) {
        super(descripcion, unidadMedida, costo);
        this.peso = peso;
    }

    @Override
    public float pesoPorUnidad() {
        return this.getPeso();

    }


    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }
}
