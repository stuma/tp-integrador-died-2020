package Controller;
import DAO.*;
import Model.*;


public class  InsumosController {

    private void altaInsumoGeneral(String descripcion, String unidadMedida, Float costo, Float peso){
        Insumo aux = new InsumoGeneral(descripcion,unidadMedida,costo,peso);
         DAOInsumos.add(aux);
    }


    private void altaInsumoLiquido(String descripcion, String unidadMedida, Float costo,Float densidad){
        Insumo aux = new InsumoLiquido(descripcion,unidadMedida,costo,densidad);
        DAOInsumos.add(aux);
    }

    private Insumo buscarInsumo(Integer id){
        GrafoController grafoController = new GrafoController();
        Insumo aux= DAOInsumos.get(id);

       grafoController.stockTotal(aux);
        return DAOInsumos.get(id);
        //TODO ver como carajo devolver un par,en c# era facil
    }


    private void bajaInsumo(Integer id){
        DAOInsumos.remove(id);
    }

    private void modificarInsumo(Insumo i){
        //TODO COMO HACER UN UPDATE?

    }





}
