package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "insumoliquido")
public class InsumoLiquido extends Insumo {

    @Column
    private Float densidad;

    public InsumoLiquido() {
    }

    @Override
    public float pesoPorUnidad() {
        return super.pesoPorUnidad();
    }



    public InsumoLiquido(String descripcion, String unidadMedida, Float costo, Float densidad) {
        super(descripcion, unidadMedida, costo);
        this.densidad = densidad;
    }

    public Float getDensidad() {
        return densidad;
    }

    public void setDensidad(Float densidad) {
        this.densidad = densidad;
    }
}
