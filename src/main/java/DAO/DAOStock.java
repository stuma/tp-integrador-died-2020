package DAO;

import Model.Stock;

import java.util.List;
import java.util.Optional;

public class DAOStock implements DAO<Stock>{

    private static DAOStock daoStock;

    private DAOStock(){

    }

    public static DAOStock getDaoInsumos(){
        if (daoStock == null){
            daoStock = new DAOStock();
        }
        return daoStock;
    }

    @Override
    public Optional<Stock> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Stock> getAll() {
        return null;
    }

    @Override
    public void save(Stock stock) {

    }



    @Override
    public void update(int id) {

    }

    @Override
    public void delete(Stock stock) {

    }

}
