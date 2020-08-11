package Model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "ruta")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "plantaOrigen_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Planta plantaOrigen;

    @Column(name = "plantaDestino_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Planta plantaDestino;

    @Column(name = "grafo_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Grafo grafo;

    @Column
    private Float distanciaKm;

    @Column
    private Float duracionHora;

    @Column
    private Float pesoMaximo;

    public Ruta() {
    }

    public Ruta(Planta plantaOrigen, Planta plantaDestino, float v, float v1, float v2) {
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

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }


}
