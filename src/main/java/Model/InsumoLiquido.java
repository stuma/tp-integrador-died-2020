package Model;

public class InsumoLiquido extends Insumo {
    private Float densidad;

    public InsumoLiquido() {
    }

    @Override
    public float pesoPorUnidad() {
        return densidad;
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
