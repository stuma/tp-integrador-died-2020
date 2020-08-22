package Service;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;

public class PlantaService {

    private DAOPlanta daoPlanta = DAOPlanta.getDaoPlanta();
    private DAOStock daoStock = DAOStock.getDaoStock();
    private DAOInsumos daoInsumos = DAOInsumos.getDaoInsumos();

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

            System.out.println(aux);

            aux.setCantidad(cantidad);
            aux.setPuntoPedido(puntoPedido);
            daoStock.update(aux);

        }
        catch (Exception e){ //Si la busqueda no devuelve un objeto, es porque no existe. Se atrapa la excepTion y se crea un objeto nuevo

            e.printStackTrace();
            /*            Stock nuevoStock = new Stock(cantidad,puntoPedido,insumo);
            planta.addStockListaStock(nuevoStock);
            nuevoStock.setPlanta(planta);
            daoStock.save(nuevoStock);*/

        }

    }

    public void crearStock(Planta planta, Insumo insumo, Integer cantidad, Integer puntoPedido) {
        Stock nuevoStock = new Stock(cantidad,puntoPedido,insumo);
        planta.addStockListaStock(nuevoStock);
        nuevoStock.setPlanta(planta);
        daoStock.save(nuevoStock);
    }

    public List<Planta> getListaPlantas() throws ElementoNoEncontradoException {
        try {
            return (daoPlanta.getAll()==null)? new ArrayList<>() : daoPlanta.getAll();
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay plantas disponibles");}

    }

    public void modificarStock(Stock stock){

        daoStock.update(stock);
    }

    public void eliminarStock(Stock idStock){
        daoStock.delete(idStock);
    }

    public List<Stock> getListaStock(){


        //List<Stock> listaAux= new ArrayList<>();
        List<Stock> listaStock = daoStock.getAll();
        //List<Insumo> listaInsumo = daoInsumos.getAll();

        if(listaStock==null){
            return new ArrayList<>();
        }

    /*    for (Insumo i : listaInsumo )
        {
            listaStock.stream().filter(t->t.getInsumo().equals(i)).mapToInt(t-> t.getCantidad()).sum();
            Stock aux= new Stock(listaStock.stream().filter(t->t.getInsumo().equals(i)).mapToInt(t-> t.getCantidad()).sum(),i);
            listaAux.add(aux);
        }*/

        return listaStock;
    }

    public void actualizarStockPlanta(OrdenPedido ord){

        List<Stock> st = ord.getPlantaOrigen().getListaStockInsumos();

        for (Stock s : st){
            for (Item i : ord.getListaItems()){
                if(i.getInsumo().getDescripcion().equals(s.getInsumo().getDescripcion())){
                    s.setCantidad(s.getCantidad()-i.getCantidad());
                    this.daoStock.update(s);
                }

            }
        }
    }
}



