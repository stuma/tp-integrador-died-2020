package Model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "planta")
public class Planta {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private String nombre;

    @Column(name = "grafo_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Grafo grafo;

    @Column(name = "rutaEntrada_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="rutaEntrada_id")
    private List<Ruta> rutaEntrada;

    @Column(name = "rutaSaluda_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="rutaSaluda_id")
    private List<Ruta> rutaSalida;

    @Column(name = "stock_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="stock_id")
    private List<Stock> listaStockInsumos;



    public Planta() {
        this.rutaSalida = new ArrayList<>();
        this.rutaEntrada = new ArrayList<>();
    }
    public Planta(String nombre) {
        // super();
        this.rutaSalida = new ArrayList<>();
        this.rutaEntrada = new ArrayList<>();
        this.listaStockInsumos = new ArrayList<>();
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public List<Ruta> getRutaEntrada() {
        return rutaEntrada;
    }

    public void setRutaEntrada(ArrayList<Ruta> rutaEntrada) {
        this.rutaEntrada = rutaEntrada;
    }

    public List<Ruta> getRutaSalida() {
        return rutaSalida;
    }

    public void setRutaSalida(ArrayList<Ruta> rutaSalida) {
        this.rutaSalida = rutaSalida;
    }

    public List<Stock> getListaStockInsumos() {
        return listaStockInsumos;
    }

    public void setListaStockInsumos(ArrayList<Stock> listaStockInsumos) {
        this.listaStockInsumos = listaStockInsumos;
    }

    public List<Planta> getAdyacente(){

        ArrayList<Planta> auxPlantas= new ArrayList<>();

        for (Ruta r :this.rutaSalida ) {
            auxPlantas.add(r.getPlantaDestino());
        }
        return auxPlantas;

    }

    public void setAdyacente(Ruta p){

        this.rutaSalida.add(p);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planta)) return false;
        Planta planta = (Planta) o;
        return Objects.equals(nombre, planta.nombre);
    }

}