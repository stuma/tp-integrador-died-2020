package Controller;
import Model.*;
import DAO.*;
import DTO.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.SortedSet;

public class CamionService {

    DAOcamion daoCamion = new DAOcamion();



    private SortedSet<Camion> listaCamionsSort;


    public SortedSet<Camion> getListaCamionsSort() {
        return listaCamionsSort;
    }

    public void setListaCamionsSort(SortedSet<Camion> listaCamionsSort) {
        this.listaCamionsSort = listaCamionsSort;
    }

    public Camion asignarCamion() throws ElementoNoEncontradoException {
        try {
            return listaCamionsSort.first();
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones Disponibles");}
    }



    public void addCamion(Camion c){
        this.listaCamionsSort.add(c);
    }

    public Camion buscarCamionPatente(String patente) throws ElementoNoEncontradoException {
        try {
            //TODO return DAOcamion.buscarCamion(String patente);

        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones Disponibles"); }
        return null;
    }

    public Camion buscarCamion(DTOCamion dtocamion) throws ElementoNoEncontradoException {
       try {
           daoCamion.buscarCamion(dtocamion);
       }catch (Exception e){throw new ElementoNoEncontradoException("No encontramos ningun camion con esas caracteristicas");
                            }

        return null;
    }

    private void altaCamion(String patente, String marca, String modelo, Float kmRecorridos, Float costoKm, Float costoHora, LocalDate fechaCompra) throws Exception {

      //Checkear que no exista un camion con la misma patente
        if(this.buscarCamionPatente(patente)==null){

            Camion c1 = new Camion(patente, marca, modelo, kmRecorridos, costoKm, costoHora, fechaCompra);
            this.addCamion(c1);

            //TODO DAOcamion.add(c1)

        }else{throw new Exception(" Ya existe un camion con esa patente");}

    }

    private  void bajaCamion(Camion c){ // o se elimina por id?
        listaCamionsSort.remove(c);
        //TODO DAOcamion.remove(c);
    }
    //TODO podemos modificar todos los atributos del camion?? o solo los costos. <-seria lo logico excepto errores de introduccion de datos
    private void modificarCamion(String patente, String marca, String modelo, Float kmRecorridos, Float costoKm, Float costoHora, LocalDate fechaCompra) throws ElementoNoEncontradoException {
        Camion aux =this.buscarCamionPatente(patente);
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
