package Controller;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class StockService {

    //STOCK

    private void actualizarStock(Planta planta, Insumo insumo, Integer cantidad, Integer puntoPedido) {

        Stock aux = planta.getListaStockInsumos().stream().filter(t -> t.getInsumo().equals(insumo)).findFirst().orElseThrow();

        aux.setCantidad(cantidad);
        aux.setPuntoPedido(puntoPedido);
        DAOstock.actualizar(aux);

    }

    public void modificarStock(Stock stock){
        //Ya tendría id
    }

    public List<Stock> getListaStock(){

        return new ArrayList<>();
    }

    public void eliminar(Stock stock){

    }

    public List<Stock> buscarPorInsumo(Insumo in){

        return new ArrayList<>();
    }

    public List<Stock> buscarPorPlanta(Planta p){

        return new ArrayList<>();
    }

    public List<Stock> buscarPor(Planta p, Insumo in){

        return new ArrayList<>();

    }
}


