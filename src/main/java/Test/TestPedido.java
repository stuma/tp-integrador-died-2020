package Test;

/*import Model.Grafo;
import Model.Item;
import Model.OrdenPedido;
import Model.Planta;
import Service.ElementoNoEncontradoException;
import Service.GrafoService;
import Service.InsumosService;
import Service.OrdenPedidoService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPedido {

    private OrdenPedido nuevaOrden;
    private GrafoService grafoService = new GrafoService();
    private InsumosService insumoService = new InsumosService();
    private OrdenPedidoService service = new OrdenPedidoService();

    private Grafo grafo;

    @Test
    public void crearPedidoTest() throws ElementoNoEncontradoException {

        this.grafo = grafoService.gfInit();
        this.nuevaOrden = new OrdenPedido();

        this.nuevaOrden.setPlantaDestino(this.grafo.getPlantas().get(0));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.nuevaOrden.setFechaSolicitud(LocalDate.parse("12/05/2020", df));

        ArrayList<Item> items = new ArrayList<>();
        Item i1 = new Item();
        i1.setInsumo(insumoService.getListaInsumos().get(0));
        i1.setCantidad(1);

        Item i2 = new Item();
        i2.setInsumo(insumoService.getListaInsumos().get(0));
        i2.setCantidad(3);

        items.add(i1);
        items.add(i2);
        this.nuevaOrden.setListaItems(items);

        this.service.generarOrdenPedido(this.nuevaOrden);

        assertEquals(this.service.getListaOrdenPedido(0).get(0).getEstadoPedido().getId(), 0);

    }

    @Test
    public void procesarPedidoTest() throws ElementoNoEncontradoException {

        this.nuevaOrden.setPlantaOrigen(this.grafo.getPlantas().get(6));
        this.nuevaOrden.setCamino((ArrayList<Planta>) this.grafoService.dijkstraKm(this.nuevaOrden.getPlantaOrigen(), this.nuevaOrden.getPlantaDestino()));

        this.service.procesarOrden(this.nuevaOrden);

        assertEquals(this.service.getListaOrdenPedido(1).get(0).getEstadoPedido().getId(), 1);

    }

    @Test
    public void entregarPedidoTest() throws ElementoNoEncontradoException {

        assertEquals(this.service.entregarPedido(this.nuevaOrden.getId()).getEstadoPedido().getId(), 2);

    }

    @Test
    public void cancelarPedidoTest(){

        assertEquals(this.service.cancelarPedido(this.nuevaOrden.getId()).getEstadoPedido().getId(), 3);

    }
}
*/