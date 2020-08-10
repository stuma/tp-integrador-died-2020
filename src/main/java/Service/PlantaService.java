package Service;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class PlantaService {
DAOPlanta daoPlanta = new DAOPlanta();

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
            DAOStock.actualizar(aux);//TODO como actualizar datos (UPDATE)

        }
        catch (Exception e){ //Si la busqueda no devuelve un objeto, es porque no existe. Se atrapa la excepTion y se crea un objeto nuevo
            Stock nuevoStock = new Stock(cantidad,puntoPedido,insumo);
            planta.addStockListaStock(nuevoStock);
            DAOStock.add(nuevoStock);
        }

    }

    public void crearStock(Planta planta, Insumo insumo, Integer cantidad, Integer puntoPedido) {
        Stock nuevoStock = new Stock(cantidad, puntoPedido, insumo);
        planta.addStockListaStock(nuevoStock);
    }

    public List<Planta> getListaPlantas() throws ElementoNoEncontradoException {
        try {
            return daoPlanta.getAll();
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones");}

    }


    public void eilminarStock(Integer idStock){
                //todo llamar al dao para que elimine
        }

    public List<Stock> getListaStock(){

        // todo listar el stock de tod el sistema

        List<Stock> listaAux= new ArrayList<Stock>();
        List<Stock> listaStock = DAOStock.getAll();
        List<Insumo> listaInsumo = DAOInsumos.getAll();

        if(listaStock==null || listaInsumo==null){
            return new ArrayList<>();
        }

        for (Insumo i : listaInsumo )
        {
            listaStock.stream().filter(t->t.getInsumo().equals(i)).mapToInt(t-> t.getCantidad()).sum();
            Stock aux= new Stock(listaStock.stream().filter(t->t.getInsumo().equals(i)).mapToInt(t-> t.getCantidad()).sum(),i);
            listaAux.add(aux);
        }

        return listaStock;
    }


}



