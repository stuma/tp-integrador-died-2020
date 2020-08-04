package Model;

public class InsumoLiquido extends Insumo {
    private Float densidad;

    public InsumoLiquido() {
    }

    @Override
    public float pesoPorUnidad() {
        return super.pesoPorUnidad();
    }

    public InsumoLiquido(Float densidad) {
        this.densidad = densidad;
    }

    public Float getDensidad() {
        return densidad;
    }

    public void setDensidad(Float densidad) {
        this.densidad = densidad;
    }
}
