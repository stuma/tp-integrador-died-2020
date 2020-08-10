package Service;
import DAO.*;
import Model.*;

import java.util.List;


public class InsumosService {


    DAOInsumos daoInsumos = new DAOInsumos();
    DAOPlanta daoPlanta= new DAOPlanta();


    public void altaInsumoGeneral(String descripcion, String unidadMedida, Float costo, Float peso){
        //todo checkear que no exista ese insumo ya
        Insumo aux = new InsumoGeneral(descripcion,unidadMedida,costo,peso);
        daoInsumos.add(aux);
    }



    public void altaInsumoLiquido(String descripcion, String unidadMedida, Float costo,Float densidad){
        Insumo aux = new InsumoLiquido(descripcion,unidadMedida,costo,densidad);
        daoInsumos.add(aux);
    }

    public void bajaInsumo(Integer id){
        daoInsumos.remove(id);
    }

    public void modificarInsumo(Insumo i){
        daoInsumos.update(i);
    }

    public List<Insumo> getListaInsumos() throws ElementoNoEncontradoException {
        try {
            return daoInsumos.getAll();
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
