package Controller;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;


public class InsumosService {

    private void altaInsumoGeneral(String descripcion, String unidadMedida, Float costo, Float peso){
        Insumo aux = new InsumoGeneral(descripcion,unidadMedida,costo,peso);
         DAOinsumos.add(aux);
    }


    private void altaInsumoLiquido(String descripcion, String unidadMedida, Float costo,Float densidad){
        Insumo aux = new InsumoLiquido(descripcion,unidadMedida,costo,densidad);
        DAOinsumos.add(aux);
    }

    private Insumo buscarInsumo(Integer id){
        GrafoService grafoController = new GrafoService();
        Insumo aux=DAOinsumos.get(id);

       grafoController.stockTotal(aux);
        return DAOinsumos.get(id);
        //TODO ver como carajo devolver un par,en c# era facil
    }


    public void bajaInsumo(Integer id){
        DAOinsumos.remove(id);
    }

    public void modificarInsumo(Insumo i){
        //TODO COMO HACER UN UPDATE?

        //el insumo i puede haber cambiado de tipo (de liquido a general).

    }

    public void altaInsumo(Insumo in){

    }

    public List<Insumo> getListaInsumos(){

        //TODO Debe retornar una lista con todos los insumos (tanto liquidos como generales)
        return new ArrayList<>();
    }

}
