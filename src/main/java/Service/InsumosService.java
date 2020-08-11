package Service;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;


public class InsumosService {


    DAOInsumos daoInsumos = new DAOInsumos();
    DAOPlanta daoPlanta= new DAOPlanta();




    public void altaInsumo(Insumo ins){
       daoInsumos.save(ins);
    }

    public void bajaInsumo(Integer id){
        daoInsumos.remove(id);
    }

    public void modificarInsumo(Insumo i){
        daoInsumos.update(i);
    }

    public List<Insumo> getListaInsumos() throws ElementoNoEncontradoException {
        try {
            return (daoInsumos.getAll()==null)? new ArrayList<>() : (daoInsumos.getAll()) ;
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones");}

    }

    public Insumo buscarInsumo(Integer id){

        Insumo auxInsumo= DAOInsumos.get(id);
//Siempre que llamamos s esta funcion tamb no interesa ssaber el stock total el mismo insumo en todas las plantas
       stockTotalInsumo(auxInsumo);
        return daoInsumos.get(id);

    }

    public Integer stockTotalInsumo(Insumo insumo){
        Integer sumaAux =0;

        for (Planta unaPlanta: daoPlanta.getAll()) {        //todo llamar al dao plantas geat all, y lugo pedirla la lista de stock de insumos
            sumaAux+=   DAOPlanta.getListaStockInsumo(unaPlanta.getId()).stream().
                    filter(t->t.getInsumo().getDescripcion().equals(insumo.getDescripcion())). //todo checkear esto
                    mapToInt(Stock::getCantidad).
                    sum();
        }
        return sumaAux;
    }






}
