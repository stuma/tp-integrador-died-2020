package Model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "ordenpedido")
public class OrdenPedido {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column
    private LocalDate fechaSolicitud;

    @Column
    private LocalDate fechaEntrega;

    @Column
    private Float costoEnvio;
    
    @Column
    @ManyToOne(cascade=CascadeType.ALL)
    private Camion camion;

    @Column
    @ManyToOne(cascade=CascadeType.ALL)
    private EstadoPedido estadoPedido;

    //one to many
    @Column
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="estadoPedido_id")
    private ArrayList<Item> listaItems;

    @Column
    @ManyToOne(cascade=CascadeType.ALL)
    private Planta plantaDestino;

    @Column
    @ManyToOne(cascade=CascadeType.ALL)
    private Planta plantaOrigen;


    @Column
    @ManyToMany(targetEntity = Planta.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "camino",
            joinColumns = { @JoinColumn(name = "planta_id") },
            inverseJoinColumns = { @JoinColumn(name = "ordenPedido_id") })
    private ArrayList<Planta> camino;

    //constructor
    public OrdenPedido() {
    }

    public ArrayList<Item> getListaItems() {
        return listaItems;
    }

    public void setListaItems(ArrayList<Item> listaItems) {
        this.listaItems = listaItems;
    }

    public Planta getPlantaDestino() {
        return plantaDestino;
    }

    public void setPlantaDestino(Planta plantaDestino) {
        this.plantaDestino = plantaDestino;
    }

    public Planta getPlantaOrigen() {
        return plantaOrigen;
    }

    public void setPlantaOrigen(Planta plantaOrigen) {
        this.plantaOrigen = plantaOrigen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Float getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Float costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public ArrayList<Planta> getCamino() {
        return camino;
    }

    public void setCamino(ArrayList<Planta> camino) {
        this.camino = camino;
    }
}
