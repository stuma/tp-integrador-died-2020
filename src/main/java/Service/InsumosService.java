package Service;
import DAO.*;
import Model.*;

import java.util.ArrayList;
import java.util.List;


public class InsumosService {

    public void altaInsumoGeneral(String descripcion, String unidadMedida, Float costo, Float peso){
        //todo checkear que no exista ese insumo ya
        Insumo aux = new InsumoGeneral(descripcion,unidadMedida,costo,peso);
         DAOInsumos.add(aux);
    }


    public void altaInsumoLiquido(String descripcion, String unidadMedida, Float costo,Float densidad){
        Insumo aux = new InsumoLiquido(descripcion,unidadMedida,costo,densidad);
        DAOInsumos.add(aux);
    }

    private Insumo buscarInsumo(Integer id){
        GrafoService grafoService = new GrafoService();
        Insumo aux= DAOInsumos.get(id);

       grafoService.stockTotal(aux);
        return DAOInsumos.get(id);
        //TODO ver como carajo devolver un par,en c# era facil
    }


    public void bajaInsumo(Integer id){
        DAOInsumos.remove(id);
    }

    public void modificarInsumo(Insumo i){
        //TODO COMO HACER UN UPDATE?

    }
    public List<Insumo> getListaInsumos(){

        //TODO retorna lista de todos los insumos
        return new ArrayList<>();
    }





}
