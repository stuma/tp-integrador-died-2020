package Service;
import Model.*;
import DAO.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenPedidoService {

    private DAOOrdenPedido daoOrdenPedido= DAOOrdenPedido.getDaoOrdenPedido();
    private GrafoService grafoService = GrafoService.getGrafoService();
    private CamionService camionService = new CamionService();
    private PlantaService plantaService = new PlantaService();

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

        //Actualiza el stock disponible
        this.plantaService.actualizarStockPlanta(ordenPedido);

        //cambiar estadoa  procesada
        cambiarEstadoOrden("PROCESADA",ordenPedido);
        daoOrdenPedido.update(ordenPedido);


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
            case "CANCELADA":
                EstadoPedido es = daoEstadoPedido.get(4).get();
                ordenPedido.setEstadoPedido(es);
                break;
        }



    }
    public OrdenPedido cancelarPedido(Integer idOrdenPedido){
        try {
            OrdenPedido aux = daoOrdenPedido.get(idOrdenPedido).get();
            cambiarEstadoOrden("CANCELADA", aux);
            daoOrdenPedido.update(aux);
            return daoOrdenPedido.get(idOrdenPedido).get();

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
     * si viene 0 filtrar por CREADA, si viene uno PROCESADA
     *
     */

    public List<OrdenPedido> getListaOrdenPedido(Integer filtro) throws ElementoNoEncontradoException {


        try {
            switch (filtro) {

                case 0:   return (daoOrdenPedido.getAll().stream().filter(t->t.getEstadoPedido().getDescripcion().equals("CREADA")).collect(Collectors.toList())); //.isEmpty()? new ArrayList<>() : daoOrdenPedido.getAll().stream().filter(t->t.getEstadoPedido().getDescripcion().equals("CREADA")).collect(Collectors.toList());

                case 1:   return (daoOrdenPedido.getAll().stream().filter(t->t.getEstadoPedido().getDescripcion().equals("PROCESADA")).collect(Collectors.toList())).isEmpty()? new ArrayList<>() : daoOrdenPedido.getAll().stream().filter(t->t.getEstadoPedido().getDescripcion().equals("PROCESADA")).collect(Collectors.toList());

            }
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay pedidos creados");

        }
     return null;
    }
}