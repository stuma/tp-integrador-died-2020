package DAO;
import Model.*;

import java.util.List;

public class DAOPlanta {

    private static DAOPlanta daoPlanta;

    public DAOPlanta(){

    }

    public static DAOPlanta getDaoPlanta(){
        if (daoPlanta == null){
            daoPlanta = new DAOPlanta();
        }

        return daoPlanta;

    }

    public List<Stock> getListaStockInsumo(Integer idPlanta){

        // call hibernet devovler la lista de stock
        return null;
    }

    public List<Planta> getAll(){
        return null;
    }
}
