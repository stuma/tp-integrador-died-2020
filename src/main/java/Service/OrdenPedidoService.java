package Service;
import Model.*;
import DTO.*;

import java.util.ArrayList;
import java.util.List;

public class OrdenPedidoService {

    GrafoService grafoService =new GrafoService();
    CamionService camionService = new CamionService();


    public void generarOrdenPedido(OrdenPedido dtoOrdenPedido) throws ElementoNoEncontradoException {

        OrdenPedido ordenPedido = new OrdenPedido();


        //Calculo de camino
       if(dtoOrdenPedido.tipoCamino){   //true camino rapido
           ordenPedido.setCamino((ArrayList<Planta>) grafoService.dijkstraHora(dtoOrdenPedido.plantaOrigen,dtoOrdenPedido.plantaDestino));

       } else{  //camino corto
             ordenPedido.setCamino((ArrayList<Planta>) grafoService.dijkstraKm(dtoOrdenPedido.plantaOrigen,dtoOrdenPedido.plantaDestino));
             }

       //CamionService asigna el camion correpondiente

       ordenPedido.setCamion(camionService.asignarCamion());

       //Agregar Items

        ordenPedido.setListaItems(dtoOrdenPedido.listaItems);

        //Cambiar Estado
       EstadoPedido estadoPedido = new EstadoPedido();
       estadoPedido.setId(1);
       ordenPedido.setEstadoPedido(estadoPedido);




    }

    public List<OrdenPedido> getListaOrdenPedido(Integer i){

        return new ArrayList<>();
    }
}
