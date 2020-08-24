package Test;

import Model.*;
import Service.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TestPedido {

    private OrdenPedido nuevaOrden;
    private GrafoService grafoService = GrafoService.getGrafoService();
    private InsumosService insumoService = new InsumosService();
    private OrdenPedidoService service = new OrdenPedidoService();
    private PlantaService plantaService = new PlantaService();
    private List<Planta> plantas;

    public static void main(String[] args) throws ElementoNoEncontradoException {


        TestPedido pedido = new TestPedido();

        pedido.crearPedido();
        pedido.procesarPedido();
        pedido.entregarPedido();

        pedido.cancelarPedido();

    }


    public void crearPedido() throws ElementoNoEncontradoException {

        //this.grafoService.setGrafo(); //TODO Si no hay un grafo en la BD, descomentar esto y ejecutarlo. Si lo hay, ignorar
        this.plantas = grafoService.getGrafo().getPlantas();

        //Creo un insumo
        Insumo insumo = new Insumo();
        insumo.setDescripcion("Insumo 1");
        insumo.setPeso(15F);
        insumo.setDensidad(null);
        insumo.setUnidadMedida("Kilogramos");
        insumo.setCosto(10F);
        insumoService.altaInsumo(insumo);

        //Creo Stock
        plantaService.crearStock(plantas.get(0),insumoService.getListaInsumos().get(0),15,5 );

        //Creo orden de pedido
        this.nuevaOrden = new OrdenPedido();
        this.nuevaOrden.setPlantaDestino(plantas.get(plantas.size()-1)); //Planta Destino = Puerto
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.nuevaOrden.setFechaSolicitud(LocalDate.parse("12/05/2020", df));

        ArrayList<Item> items = new ArrayList<>();
        Item i1 = new Item();
        i1.setInsumo(insumoService.getListaInsumos().get(0));
        i1.setCantidad(1);
        items.add(i1);
        this.nuevaOrden.setListaItems(items);


        this.service.generarOrdenPedido(this.nuevaOrden);
        System.out.println("Estado Pedido Creado : " + this.service.getListaOrdenPedido(0).get(0).getEstadoPedido().getDescripcion());

    }


    public void procesarPedido() throws ElementoNoEncontradoException {

        System.out.println("Stock en Planta Origen: " );
        for (Stock st :  this.plantas.get(0).getListaStockInsumos()){
            System.out.println(st);
        }

        this.nuevaOrden.setPlantaOrigen(this.plantas.get(0)); //Planta Origen es Puerto
        this.nuevaOrden.setCamino((ArrayList<Planta>) this.grafoService.dijkstraKm(this.nuevaOrden.getPlantaOrigen(), this.nuevaOrden.getPlantaDestino()));

        this.service.procesarOrden(this.nuevaOrden);

        System.out.println("Camino: ");
        System.out.println(this.nuevaOrden.getCamino());
        System.out.println("Camión: ");
        System.out.println(this.nuevaOrden.getCamion());

        System.out.println("Estado Pedido Procesado: " + this.service.getListaOrdenPedido(1).get(0).getEstadoPedido().getDescripcion());
        System.out.println("Stock en Planta Origen: " + this.plantaService.getListaStock().get(0));

    }

    public void entregarPedido() throws ElementoNoEncontradoException {

        System.out.println("Estado Pedido Entregado: " + this.service.entregarPedido(this.nuevaOrden.getId()).getEstadoPedido().getDescripcion());

    }

    public void cancelarPedido() throws ElementoNoEncontradoException {

        //Creo orden de pedido
        this.nuevaOrden = new OrdenPedido();
        this.nuevaOrden.setPlantaDestino(plantas.get(plantas.size()-1)); //Planta Destino = Puerto
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.nuevaOrden.setFechaSolicitud(LocalDate.parse("12/05/2020", df));

        ArrayList<Item> items = new ArrayList<>();
        Item i1 = new Item();
        i1.setInsumo(insumoService.getListaInsumos().get(0));
        i1.setCantidad(50);
        items.add(i1);
        this.nuevaOrden.setListaItems(items);
        this.service.generarOrdenPedido(this.nuevaOrden);
        System.out.println("Estado Pedido Creado : " + this.service.getListaOrdenPedido(0).get(0).getEstadoPedido().getDescripcion());

        System.out.println("Estado Pedido Cancelado: " + this.service.cancelarPedido(this.nuevaOrden.getId()).getEstadoPedido().getDescripcion());

    }
}
