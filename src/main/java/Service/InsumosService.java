package Service;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;


public class InsumosService {


    private DAOInsumos daoInsumos =DAOInsumos.getDaoInsumos();
    private DAOPlanta daoPlanta= DAOPlanta.getDaoPlanta();




    public void altaInsumo(Insumo ins){
       daoInsumos.save(ins);
    }

    public void bajaInsumo(Integer id){
        daoInsumos.delete(buscarInsumo(id));
    }

    public void modificarInsumo(Insumo insumo){
       daoInsumos.update(insumo);
    }

    public List<Insumo> getListaInsumos() throws ElementoNoEncontradoException {
        try {
            return (daoInsumos.getAll()==null)? new ArrayList<>() : (daoInsumos.getAll()) ;
        }catch (Exception e){throw new ElementoNoEncontradoException("No hay camiones");}

    }

    public Insumo buscarInsumo(Integer id){

        Insumo auxInsumo= daoInsumos.get(id).get();
        //Siempre que llamamos s esta funcion tamb no interesa ssaber el stock total el mismo insumo en todas las plantas
       stockTotalInsumo(auxInsumo);
        return daoInsumos.get(id).get();

    }

    public Integer stockTotalInsumo(Insumo insumo){

        Integer sumaAux =0;

        for (Planta unaPlanta: daoPlanta.getAll()) {
            sumaAux+=daoPlanta.getListaStockInsumo(unaPlanta.getId()).stream().
                    filter(t->t.getInsumo().getDescripcion().equals(insumo.getDescripcion())).
                    mapToInt(Stock::getCantidad).
                    sum();
        }
        return sumaAux;
    }






}
