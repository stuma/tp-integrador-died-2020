package Model;

public class Ruta {
    private Integer id;
    private Float distanciaKm;
    private Float duracionHora;
    private Float pesoMaximo;

    public Ruta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(Float distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Float getDuracionHora() {
        return duracionHora;
    }

    public void setDuracionHora(Float duracionHora) {
        this.duracionHora = duracionHora;
    }

    public Float getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(Float pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }
}
