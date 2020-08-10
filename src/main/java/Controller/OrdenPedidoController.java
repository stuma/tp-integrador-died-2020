package Controller;
import Model.*;
//import DTO.*;

import java.util.ArrayList;

public class OrdenPedidoController {

    GrafoController grafoController =new GrafoController();
    CamionController camionController = new CamionController();


    /*public void generarOrdenPedido(DTOOrdenPedido dtoOrdenPedido) throws ElementoNoEncontradoException {

        OrdenPedido ordenPedido = new OrdenPedido(dtoOrdenPedido);


        //Calculo de camino

       if(dtoOrdenPedido.tipoCamino){   //true camino rapido
           ordenPedido.setCamino((ArrayList<Planta>) grafoController.caminoMinimoHora(dtoOrdenPedido.plantaOrigen,dtoOrdenPedido.plantaDestino));

       } else{  //camino corto
             ordenPedido.setCamino((ArrayList<Planta>) grafoController.caminoMinimoKm(dtoOrdenPedido.plantaOrigen,dtoOrdenPedido.plantaDestino));
             }

       //CamionController asigna el camion correpondiente

       ordenPedido.setCamion(camionController.asignarCamion());

       //Agregar Items

        ordenPedido.setListaItems(dtoOrdenPedido.listaItems);




        //Cambiar Estado
       EstadoPedido estadoPedido = new EstadoPedido();
       estadoPedido.setId(1);
       ordenPedido.setEstadoPedido(estadoPedido);




    }*/

}
