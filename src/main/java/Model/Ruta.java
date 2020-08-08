package Model;

public class Ruta {
    private Planta plantaOrigen;
    private Planta plantaDestino;
    private Integer id;
    private Float distanciaKm;
    private Float duracionHora;
    private Float pesoMaximo;

    public Ruta() {
    }

    public Ruta(Planta plantaOrigen, Planta plantaDestino, Float distanciaKm, Float duracionHora, Float pesoMaximo) {
        this.plantaOrigen = plantaOrigen;
        this.plantaDestino = plantaDestino;
        this.distanciaKm = distanciaKm;
        this.duracionHora = duracionHora;
        this.pesoMaximo = pesoMaximo;
        plantaOrigen.addRutaSalida(this);
        plantaDestino.addRutaEntrada(this);
    }


    public Integer getId() {
        return id;
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

    public Planta getPlantaOrigen() {
        return plantaOrigen;
    }

    public Planta getPlantaDestino() {
        return plantaDestino;
    }

    public void setPlantaDestino(Planta plantaDestino) {
        this.plantaDestino = plantaDestino;
    }

    public void setPlantaOrigen(Planta plantaOrigen) {
        this.plantaOrigen = plantaOrigen;
    }
}
