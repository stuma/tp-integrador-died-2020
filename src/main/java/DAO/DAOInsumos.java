package DAO;

import Model.Insumo;

import java.util.List;
import java.util.Optional;

public class DAOInsumos implements DAO{

    private static DAOInsumos daoInsumos;

    public DAOInsumos(){

    }

    public static DAOInsumos getDaoInsumos(){
        if (daoInsumos == null){
            daoInsumos = new DAOInsumos();
        }
        return daoInsumos;
    }

    @Override
    public Optional<Insumo> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Insumo> getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(Object o) {

    }
}
