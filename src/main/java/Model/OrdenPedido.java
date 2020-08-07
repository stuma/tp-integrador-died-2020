package Model;
import DTO.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrdenPedido {

    private Integer id;
    private LocalDate fechaSolicitud;
    private LocalDate fechaEntrega;
    private Float costoEnvio;
    private Camion camion;
    private EstadoPedido estadoPedido;
    private ArrayList<Item> listaItems;
    private Planta plantaDestino;
    private Planta plantaOrigen;
    private ArrayList<Planta> camino;

    //constructor
    public OrdenPedido() {
    }

    public OrdenPedido(DTOordenPedido dtoOrdenPedido){
        this.fechaSolicitud = dtoOrdenPedido.fechaSolicitud;
        this.fechaEntrega = dtoOrdenPedido.fechaEntrega;
        this.listaItems = dtoOrdenPedido.listaItems;
        this.plantaDestino = dtoOrdenPedido.plantaDestino;
        this.plantaOrigen = dtoOrdenPedido.plantaOrigen;
        //this.camino= dtoOrdenPedido.camino;
        // this.costoEnvio = dtoOrdenPedido.costoEnvio;
        // this.camion = dtoOrdenPedido.camion;
        // this.estadoPedido = dtoOrdenPedido.estadoPedido;

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