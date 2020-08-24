package Model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "planta")
public class Planta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //TODO corregir todo a IDENTITY
    private Integer id;

    @Column
    private String nombre;

    //@Column(name = "grafo_id")cascade = CascadeType.ALL
    @ManyToOne()
    private Grafo grafo;

    //@Column(name = "rutaEntrada_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="plantaDestino_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Ruta> rutaEntrada;

    //@Column(name = "rutaSaluda_id")
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="plantaOrigen_id")
    private List<Ruta> rutaSalida;

    //@Column(name = "stock_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="planta_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Stock> listaStockInsumos;



    public Planta( Grafo gf, String name) {
        this.setNombre(name);
        this.setGrafo(gf);
        this.rutaSalida = new ArrayList<>();
        this.rutaEntrada = new ArrayList<>();

    }

    public Planta() {
        this.rutaSalida = new ArrayList<>();
        this.rutaEntrada = new ArrayList<>();
    }

    public Planta(String nombre) {

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
        if(this.rutaEntrada==null)
            this.rutaEntrada=new ArrayList<>();
        return rutaEntrada;
    }

    public void setRutaEntrada(ArrayList<Ruta> rutaEntrada) {
        this.rutaEntrada = rutaEntrada;
    }

    public List<Ruta> getRutaSalida() {
        if(this.rutaSalida==null)
            this.rutaSalida=new ArrayList<>();
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

    public void addRutaSalida(Ruta ruta){

        this.rutaSalida.add(ruta);

    }

    public void addRutaEntrada(Ruta ruta){
        this.rutaEntrada.add(ruta);
    }

    public void addStockListaStock(Stock stock){
        this.listaStockInsumos.add(stock);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planta)) return false;
        Planta planta = (Planta) o;
        if(id!=null && planta.id!=null) return Objects.equals(id, planta.id);
        return Objects.equals(nombre, planta.nombre);
    }

    @Override
    public String toString() {
        return "Planta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}