package Service;
import Model.*;
import DAO.*;
import DTO.*;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;

public class CamionService {

    private SortedSet<Camion> listaCamionsSort;
    DAOCamion daoCamion = new DAOCamion();

public List<Camion> getListaCamion() throws ElementoNoEncontradoException {
    try {
        return daoCamion.getAll();
    }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones");}

}


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
            //TODO return DAOCamion.buscarCamion(String patente);

        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones Disponibles"); }
        return null;
    }

    public List<Camion> getListaCamiones(DTOCamion dtocamion) throws ElementoNoEncontradoException {
       try {
           daoCamion.buscarCamion(dtocamion);
       }catch (Exception e){throw new ElementoNoEncontradoException("No encontramos ningun camion con esas caracteristicas");
                            }

        return null;
    }

    public void altaCamion(String patente, String marca, String modelo, Float kmRecorridos, Float costoKm, Float costoHora, LocalDate fechaCompra) throws Exception {

      //Checkear que no exista un camion con la misma patente
        if(this.buscarCamionPatente(patente)==null){

            Camion c1 = new Camion(patente, marca, modelo, kmRecorridos, costoKm, costoHora, fechaCompra);
            this.addCamion(c1);

            //TODO DAOCamion.add(c1)

        }else{throw new Exception(" Ya existe un camion con esa patente");}

    }

    public  void bajaCamion(Camion c){ // o se elimina por id?
        listaCamionsSort.remove(c);
        //TODO DAOCamion.remove(c);
    }

    //TODO podemos modificar todos los atributos del camion?? o solo los costos. <-seria lo logico excepto errores de introduccion de datos
    public void modificarCamion(Camion unCamion) throws ElementoNoEncontradoException {
        Camion aux =this.buscarCamionPatente(unCamion.getPatente());
        aux.setCostoHora(unCamion.getCostoHora());
        aux.setCostoKm(unCamion.getCostoKm());
        aux.setKmRecorridos(unCamion.getKmRecorridos());
        aux.setFechaCompra(unCamion.getFechaCompra());
        aux.setMarca(unCamion.getMarca());
        aux.setModelo(unCamion.getModelo());
        // TODO update del camion en la bd  DAOCamion.update(aux);
        //revisar, creo q no hay q crear una nueva instancia de camion
    }


}
