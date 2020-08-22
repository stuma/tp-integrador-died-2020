package Model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    
    //@Column cascade=CascadeType.ALL
    @ManyToOne()
    private Camion camion;

    //@Column cascade=CascadeType.ALL
    @ManyToOne()
    private EstadoPedido estadoPedido;

    //one to many
    //@Column
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ordenPedido_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Item> listaItems;

    //@Column cascade=CascadeType.ALL
    @ManyToOne()
    private Planta plantaDestino;

    //@Column cascade=CascadeType.ALL
    @ManyToOne()
    private Planta plantaOrigen;


    //@Column cascade = { CascadeType.ALL }
    @ManyToMany(targetEntity = Planta.class)
    @JoinTable(name = "camino",
            joinColumns = { @JoinColumn(name = "planta_id") },
            inverseJoinColumns = { @JoinColumn(name = "ordenPedido_id") })
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Planta> camino;

    //constructor
    public OrdenPedido() {
    }

    public List<Item> getListaItems() {
        return listaItems;
    }

    public void setListaItems(List<Item> listaItems) {
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

    public List<Planta> getCamino() {
        return camino;
    }

    public void setCamino(ArrayList<Planta> camino) {
        this.camino = camino;
    }

    @Override
    public String toString() {
        return "OrdenPedido{" +
                "id=" + id +
                ", fechaSolicitud=" + fechaSolicitud +
                ", fechaEntrega=" + fechaEntrega +
                ", costoEnvio=" + costoEnvio +
                ", camion=" + camion +
                ", estadoPedido=" + estadoPedido +
                ", listaItems=" + listaItems +
                ", plantaDestino=" + plantaDestino +
                ", plantaOrigen=" + plantaOrigen +
                ", camino=" + camino +
                '}';
    }
}
