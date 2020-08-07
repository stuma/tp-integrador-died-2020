package Controller;
import Model.*;
import DAO.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedSet;

public class CamionController {

    private ArrayList<Camion> listaCamiones;
    private SortedSet<Camion> listaCamions;

  private void sortListaCamiones(){
        Collections.sort(listaCamiones,(c1,c2)->c1.compareTo(c2));
    }

    public Camion asignarCamion() throws ElementoNoEncontradoException {
        this.sortListaCamiones();
        try {
            return listaCamiones.get(0);
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones Disponibles");}
    }

    public ArrayList<Camion> getListaCamiones() {
        return listaCamiones;
    }

    public void setListaCamiones(ArrayList<Camion> listaCamiones) {
        this.listaCamiones = listaCamiones;
    }
    public void addCamion(Camion c){
        this.listaCamiones.add(c);
    }

    public Camion buscarCamion(String patente){
        //TODO DAOcamion buscar camion
        return null;
    }

    private void altaCamion(String patente, String marca, String modelo, Float kmRecorridos, Float costoKm, Float costoHora, LocalDate fechaCompra) {

        Camion c1 = new Camion(patente, marca, modelo, kmRecorridos, costoKm, costoHora, fechaCompra);
        this.addCamion(c1);
        //TODO DAOcamion.add(c1)
    }

    private  void bajaCamion(Integer idCamion){ // o se elimina por id?
        //TODO DAOcamion.remove(patente);

    }
    //TODO podemos modificar todos los atributos del camion?? o solo los costos. <-seria lo logico excepto errores de introduccion de datos
    private void modificarCamion(String patente, String marca, String modelo, Float kmRecorridos, Float costoKm, Float costoHora, LocalDate fechaCompra){
        Camion aux =this.buscarCamion(patente);
        aux.setCostoHora(costoHora);
        aux.setCostoKm(costoKm);
        aux.setKmRecorridos(kmRecorridos);
        aux.setFechaCompra(fechaCompra);
        aux.setMarca(marca);
        aux.setModelo(modelo);
        // TODO update del camion en la bd  DAOcamion.update(aux);
        //revisar, creo q no hay q crear una nueva instancia de camion
    }


}
