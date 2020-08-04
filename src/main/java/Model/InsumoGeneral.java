package Model;

public class InsumoGeneral extends Insumo{

    private Float peso;

    public InsumoGeneral() {
    }

    public InsumoGeneral(Float peso) {
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
