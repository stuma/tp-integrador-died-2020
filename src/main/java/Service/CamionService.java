package Service;
import DAO.DAOCamion;
import DAO.DAOOrdenPedido;
import Model.Camion;
import Model.OrdenPedido;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CamionService {


    //private SortedSet<Camion> listaCamionsDisponibles;
    private Queue<Camion> listaCamionsDisponibles = new PriorityQueue<Camion>((c1, c2)-> {
        if(c2.getKmRecorridos()-c1.getKmRecorridos()>0){
            return 1;
        }else{
            if(c2.getKmRecorridos()-c1.getKmRecorridos()<0){
                return -1;
            }
            else{
                return 0;
            }

        }
    });
    private DAOCamion daoCamion = DAOCamion.getDaoCamion();
    private DAOOrdenPedido daoOrdenPedido = DAOOrdenPedido.getDaoOrdenPedido();



    public List<Camion> getListaCamion(){
        try {
            return (daoCamion.getAll()==null)? new ArrayList<>() : daoCamion.getAll();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

//    public SortedSet<Camion> getListaCamionsSort() {
//        return listaCamionsDisponibles;
//    }

    public Queue<Camion> getListaCamionsDisponibles() {
        return listaCamionsDisponibles;
    }


    public void setListaCamionsSort(Queue<Camion> listaCamionsSort) {
        this.listaCamionsDisponibles = listaCamionsSort;
    }

    public void updateListaCamiones(){
                                                                                        //Esta pedido= 1 es PROCESADA
      List<Camion> listaOP= daoOrdenPedido.getAll().stream().filter(t->!t.getEstadoPedido().getId().equals(1)).
                                                                map(OrdenPedido::getCamion).
                                                                collect(Collectors.toList());

      this.listaCamionsDisponibles.clear(); //Vacía la cola
      this.listaCamionsDisponibles.addAll(listaOP);

    }

/*    public void updateListaCamiones(){
                                                                                        //Esta pedido= 1 es PROCESADA
      List<Camion> listaOP= daoOrdenPedido.getAll().stream().filter(t->t.getEstadoPedido().getId().equals(1)).
                                                                map(OrdenPedido::getCamion).
                                                                collect(Collectors.toList());
        List<Camion> resultado = daoCamion.getAll();

        for (Camion c: listaOP) {
            if(resultado.contains(c)){
                resultado.remove(c);
            }

        }

      setListaCamionsSort((SortedSet<Camion>) resultado);

    }*/


    public Camion asignarCamion(Float km) throws ElementoNoEncontradoException {
                updateListaCamiones();
        try {
            Camion auxCamion= listaCamionsDisponibles.remove();
            auxCamion.setKmRecorridos(km);
            modificarCamion(auxCamion);
           return auxCamion;
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones Disponibles");}
    }

    public void addCamion(Camion c){
        this.listaCamionsDisponibles.add(c);
    }

    public Camion buscarCamionPatente(String patente) throws ElementoNoEncontradoException {
        try {
            //todo return DAOCamion.getCamionPatente(String patente);

        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones Disponibles"); }
        return null;
    }

    public List<Camion> buscarCamiones(Camion auxCamion){
       try {
           return (daoCamion.getListaCamionesAtributos(auxCamion) == null) ? new ArrayList<>() : daoCamion.getListaCamionesAtributos(auxCamion);
       }catch (Exception e){

           return new ArrayList<>();

       }

    }

    public void altaCamion(String patente, String marca, String modelo, Float kmRecorridos, Float costoKm, Float costoHora, LocalDate fechaCompra) throws Exception {

      //Checkear que no exista un camion con la misma patente
        //CREO UN CAMION Y LE SETEO LA PATENTE Y UTILIZO LA FUNCION CREDA buscarCamiones(auxCamion)  .ISEMPTY

        if(this.buscarCamionPatente(patente)==null){

            Camion c1 = new Camion( patente, marca, modelo, kmRecorridos, costoKm, costoHora, fechaCompra);
            this.addCamion(c1);

           DAOCamion.getDaoCamion().save(c1);

        }else{throw new Exception(" Ya existe un camion con esa patente");}

    }

    public  void bajaCamion(Camion c){ // o se elimina por id?
        listaCamionsDisponibles.remove(c);
        daoCamion.delete(c);
    }

    public void modificarCamion(Camion unCamion) throws ElementoNoEncontradoException {
/*        Camion aux =this.buscarCamionPatente(unCamion.getPatente());
        aux.setCostoHora(unCamion.getCostoHora());
        aux.setCostoKm(unCamion.getCostoKm());
        aux.setKmRecorridos(unCamion.getKmRecorridos());
        aux.setFechaCompra(unCamion.getFechaCompra());
        aux.setMarca(unCamion.getMarca());
        aux.setModelo(unCamion.getModelo());*/
        daoCamion.update(unCamion);
        //revisar, creo q no hay q crear una nueva instancia de camion
    }


}
