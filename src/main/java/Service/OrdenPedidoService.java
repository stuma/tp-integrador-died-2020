package Service;
import Model.*;
import DAO.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenPedidoService {
    DAOOrdenPedido daoOrdenPedido= new DAOOrdenPedido();
    GrafoService grafoService =new GrafoService();
    CamionService camionService = new CamionService();


    public void generarOrdenPedido(OrdenPedido ordenPedido){
        //Cambiar Estado a CREADA

        cambiarEstadoOrden("CREADA",ordenPedido);

        //guardar orden pedido
        DAOOrdenPedido.save(ordenPedido);
    }


    //todo
    public void procesarOrden(OrdenPedido ordenPedido) throws ElementoNoEncontradoException {


//CamionService asigna el camion correpondiente

        ordenPedido.setCamion(camionService.asignarCamion());
//cambiar estadoa  procesada

        cambiarEstadoOrden("PROCESADA",ordenPedido);

        daoOrdenPedido.update(ordenPedido);


        //update orden pedido

    }


    public void cambiarEstadoOrden(String estado, OrdenPedido ordenPedido){
        DAOEstadoPedido daoEstadoPedido= new DAOEstadoPedido();
        switch (estado){

            case "CREADA": ordenPedido.setEstadoPedido(daoEstadoPedido.get(0));
                break;
            case "PROCESADA":ordenPedido.setEstadoPedido(daoEstadoPedido.get(1));
                break;
            case "ENTREGADA":ordenPedido.setEstadoPedido(daoEstadoPedido.get(2));
                break;
            case "CANCELADA":ordenPedido.setEstadoPedido(daoEstadoPedido.get(3));
                break;
        }



    }
    public OrdenPedido cancelarPedido(Integer idOrdenPedido){
        try {
            OrdenPedido aux = daoOrdenPedido.get(idOrdenPedido);
            cambiarEstadoOrden("CANCELADA", aux);
            daoOrdenPedido.update(aux);
            return aux;
        }catch (Exception e){e.printStackTrace();}

        return null;
    }
    public OrdenPedido entregarPedido(Integer idOrdenPedido){


        try {
            OrdenPedido aux = daoOrdenPedido.get(idOrdenPedido);
            cambiarEstadoOrden("ENTREGADA", aux);
            aux.setFechaEntrega(LocalDate.now());
            daoOrdenPedido.update(aux);
            camionService.addCamion(aux.getCamion());
            return aux;
        }catch (Exception e){e.printStackTrace();}

        return null;
    }

    /**
     * si vienne 0 filtrar por CREADA, si viene uno PROCESADA
     *
     */

    public List<OrdenPedido> getListaOrdenPedido(Integer filtro) throws ElementoNoEncontradoException {


        try {
            switch (filtro) {

                case 0:   return (daoOrdenPedido.buscarOrdenPorEstado("CREADA")==null)? new ArrayList<>() : daoOrdenPedido.buscarOrdenPorEstado("CREADA"); //todo el dao tiene que lanzar excepcion si no encuentra nada

                case 1:   return (daoOrdenPedido.buscarOrdenPorEstado("PROCESADA")== null) ? new ArrayList<>() : daoOrdenPedido.buscarOrdenPorEstado("PROCESADA");
            }
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay pedidos creados");

        }
     return null;
    }
}