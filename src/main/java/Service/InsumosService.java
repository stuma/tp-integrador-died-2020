package Service;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;


public class InsumosService {

    public void altaInsumoGeneral(String descripcion, String unidadMedida, Float costo, Float peso){
        Insumo aux = new InsumoGeneral(descripcion,unidadMedida,costo,peso);
         DAOinsumos.add(aux);
    }


    public void altaInsumoLiquido(String descripcion, String unidadMedida, Float costo,Float densidad){
        Insumo aux = new InsumoLiquido(descripcion,unidadMedida,costo,densidad);
        DAOinsumos.add(aux);
    }

    private Insumo buscarInsumo(Integer id){
        GrafoService grafoService = new GrafoService();
        Insumo aux=DAOinsumos.get(id);

       grafoService.stockTotal(aux);
        return DAOinsumos.get(id);
        //TODO ver como carajo devolver un par,en c# era facil
    }


    public void bajaInsumo(Integer id){
        DAOinsumos.remove(id);
    }

    public void modificarInsumo(Insumo i){
        //TODO COMO HACER UN UPDATE?

    }

    public List<Insumo> getListaInsumos(){

        return new ArrayList<>();
    }



}
