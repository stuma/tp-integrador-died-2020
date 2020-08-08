package Controller;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class PlantaService {


    /**Metodo para crear y modificar Stock de insumos
     * Si existe ese insumo en el stock de la planta lo modificamos, sino creamo uno nuevo
     * @param  -Planta , Insumo, cantidad, puntoPedido
     * @return void
     * @author juan
     */
    public void actualizarStock(Planta planta, Insumo insumo, Integer cantidad, Integer puntoPedido) {

        //o viene un dto insumo con datos  o el insumo creado, o todos los valores para que lo cree

        try {
            Stock aux = planta.getListaStockInsumos().stream().filter(t -> t.getInsumo().equals(insumo)).findFirst().orElseThrow();
            aux.setCantidad(cantidad);
            aux.setPuntoPedido(puntoPedido);
            DAOstock.actualizar(aux);//TODO como actualizar datos (UPDATE)

        }
        catch (Exception e){ //Si la busqueda no devuelve un objeto, es porque no existe. Se atrapa la excepTion y se crea un objeto nuevo
            Stock nuevoStock = new Stock(cantidad,puntoPedido,insumo);
            planta.addStockListaStock(nuevoStock);
            DAOstock.add(nuevoStock);
        }

    }

    public void crearStock(Planta planta, Insumo insumo, Integer cantidad, Integer puntoPedido){
        Stock nuevoStock = new Stock(cantidad,puntoPedido,insumo);
        planta.addStockListaStock(nuevoStock);

        //Actualizar la lista de stock de Stock Controller.
    }

    public List<Planta> getListaPlantas(){

        return new ArrayList<>();
    }


    public List<Planta> getListaPlantas(OrdenPedido orden){
        //Retorna la lista de plantas que puedan satisfacer la demanda de items de orden
        //si no, arrojar excepcion para cancelar orden

        return new ArrayList<>();
    }

    public void altaPlanta(Planta p){
        //No solo la almacena en la base de datos sino que la debe agregar al grafo
    }
}
