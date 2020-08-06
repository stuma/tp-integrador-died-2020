package Controller;
import DAO.*;
import Model.*;
public class StockController {

    //STOCK

    private void actualizarStock(Planta planta, Insumo insumo, Integer cantidad, Integer puntoPedido) {
        Stock aux = planta.getListaStockInsumos().stream().filter(t -> t.getInsumo().equals(insumo)).findFirst().orElseThrow();
        aux.setCantidad(cantidad);
        aux.setPuntoPedido(puntoPedido);
        DAOstock.actualizar(aux);

    }
}


