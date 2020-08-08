package Controller;
import DAO.*;
import Model.*;
import DTO.*;

import java.util.ArrayList;
import java.util.List;

public class OrdenPedidoController {

    GrafoController grafoController =new GrafoController();
    CamionController camionController = new CamionController();


    public void generarOrdenPedido(DTOordenPedido dtoOrdenPedido) throws ElementoNoEncontradoException {

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




    }

    public void generarOrdenPedido(OrdenPedido orden){

    }

    public void procesarOrden(OrdenPedido orden){

        //a la orden de pedido se le modificó la planta de origen. También se le debe asignar un camion, cambiar estad de pedido y
        //calcular costo de envio.

    }
    public List<OrdenPedido> getListaOrdenPedidoCreadas(){

        //Retorna lista de pedidos con estado= Creada.
        return new ArrayList<>();
    }
    public List<OrdenPedido> getListaOrdenPedidoProcesadas(){

        //Retorna lista de pedido con estado = procesada
        return new ArrayList<>();
    }

    public void entregarPedido(OrdenPedido orden){

        //Actualiza estado de orden de Procesada a Entregada. Actualiza fecha de entrega.
        //Se elimina de la lista de pedidos procesadas
    }

    public void cancelarPedido(OrdenPedido orden){

    }
}
