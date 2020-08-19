package Service;
import Model.*;
import DAO.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenPedidoService {

    private DAOOrdenPedido daoOrdenPedido= DAOOrdenPedido.getDaoOrdenPedido();
    private GrafoService grafoService = GrafoService.getGrafoService();
    private CamionService camionService = new CamionService();


    public void generarOrdenPedido(OrdenPedido ordenPedido){

        cambiarEstadoOrden("CREADA",ordenPedido);
        //guardar orden pedido
        daoOrdenPedido.save(ordenPedido);

    }



    public Float calcularCostoEnvio(Camion c, Float kmARecorrer, Float horaARecorrer){
                Float aux=0F;
                return aux= c.getCostoHora()*horaARecorrer+c.getCostoKm()*kmARecorrer;

    }
    //todo
    public void procesarOrden(OrdenPedido ordenPedido) throws ElementoNoEncontradoException {

        Float kmCamino = grafoService.calcularKmCamino(ordenPedido.getCamino());
        Float horaCamino= grafoService.calcularHoraCamino(ordenPedido.getCamino());

        //CamionService asigna el camion correpondiente y le actualizamos los km
        Camion auxCamion= camionService.asignarCamion(kmCamino);
        ordenPedido.setCamion(auxCamion);

        //Calculo costo del envio
        ordenPedido.setCostoEnvio(calcularCostoEnvio(auxCamion,kmCamino,horaCamino));

        //cambiar estadoa  procesada
        cambiarEstadoOrden("PROCESADA",ordenPedido);


        daoOrdenPedido.update(ordenPedido);

        //update orden pedido

    }


    public void cambiarEstadoOrden(String estado, OrdenPedido ordenPedido){
        DAOEstadoPedido daoEstadoPedido= DAOEstadoPedido.getDaoEstadoPedido();
        switch (estado){

            case "CREADA": ordenPedido.setEstadoPedido(daoEstadoPedido.get(1).get());
                break;
            case "PROCESADA":ordenPedido.setEstadoPedido(daoEstadoPedido.get(2).get());
                break;
            case "ENTREGADA":ordenPedido.setEstadoPedido(daoEstadoPedido.get(3).get());
                break;
            case "CANCELADA":ordenPedido.setEstadoPedido(daoEstadoPedido.get(4).get());
                break;
        }



    }
    public OrdenPedido cancelarPedido(Integer idOrdenPedido){
        try {
            OrdenPedido aux = daoOrdenPedido.get(idOrdenPedido).get();
            cambiarEstadoOrden("CANCELADA", aux);
            daoOrdenPedido.update(aux);
            return aux;
        }catch (Exception e){e.printStackTrace();}

        return null;
    }
    public OrdenPedido entregarPedido(Integer idOrdenPedido){


        try {
            OrdenPedido aux = daoOrdenPedido.get(idOrdenPedido).get();
            cambiarEstadoOrden("ENTREGADA", aux);
            aux.setFechaEntrega(LocalDate.now());
            camionService.addCamion(aux.getCamion());
            daoOrdenPedido.update(aux);
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

                case 0:   return (daoOrdenPedido.buscarOrdenPorEstado("CREADA")==null)? new ArrayList<>() : daoOrdenPedido.buscarOrdenPorEstado("CREADA");

                case 1:   return (daoOrdenPedido.buscarOrdenPorEstado("PROCESADA")== null) ? new ArrayList<>() : daoOrdenPedido.buscarOrdenPorEstado("PROCESADA");
            }
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay pedidos creados");

        }
     return null;
    }
}