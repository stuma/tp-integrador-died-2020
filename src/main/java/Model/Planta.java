package Model;

import java.util.ArrayList;

public class Planta {
    private Integer id;
    private String nombre;
    private ArrayList<Ruta> rutaEntrada;
    private ArrayList<Ruta> rutaSalida;
    private ArrayList<Stock> listastockInsumos;


    public Planta() {
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

    public ArrayList<Ruta> getRutaEntrada() {
        return rutaEntrada;
    }

    public void setRutaEntrada(ArrayList<Ruta> rutaEntrada) {
        this.rutaEntrada = rutaEntrada;
    }

    public ArrayList<Ruta> getRutaSalida() {
        return rutaSalida;
    }

    public void setRutaSalida(ArrayList<Ruta> rutaSalida) {
        this.rutaSalida = rutaSalida;
    }

}
