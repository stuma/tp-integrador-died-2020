package Controller;
import DAO.DAOinsumos;
import Model.*;
import View.*;


public class InsumosController {

    private void altaInsumoGeneral(Float peso){
        Insumo aux = new InsumoGeneral(peso);
         DAOinsumos.add(aux);
    }


    private void altaInsumoLiquido(Float densidad){
        Insumo aux = new InsumoLiquido(densidad);
        DAOinsumos.add(aux);
    }

    private Insumo buscarInsumo(Integer id){
       return DAOinsumos.get(id);
    }

    private void bajaInsumo(Integer id){
        DAOinsumos.remove(id);
    }





}
