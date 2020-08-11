package DAO;

import Model.Camion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOCamion implements DAO<Camion> {

    private static DAOCamion daoCamion;

    public DAOCamion(){

    }

    public static DAOCamion getDaoInsumos(){
        if (daoCamion == null){
            daoCamion = new DAOCamion();
        }
        return daoCamion;
    }


    @Override
    public Optional<Camion> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Camion> getAll() {
        return null;
    }

    @Override
    public void save(Camion camion) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(Camion camion) {

    }

    public Optional<Camion> getCamionPatente(String patente){
        return Optional.empty();
    }

    public List<Camion> getListaCamionesAtributos (Camion camion){
        return new ArrayList<Camion>();
    }


}
