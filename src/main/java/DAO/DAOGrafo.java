package DAO;

import Model.Grafo;
import Model.Planta;
import Model.Ruta;

import java.util.List;
import java.util.Optional;

public class DAOGrafo implements DAO<Grafo>{

    private static DAOGrafo daoGrafo;

    private DAOGrafo(){

    }

    public static DAOGrafo getDaoInsumos(){
        if (daoGrafo == null){
            daoGrafo = new DAOGrafo();
        }
        return daoGrafo;
    }

    @Override
    public Optional<Grafo> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Grafo> getAll() {
        return null;
    }

    @Override
    public void save(Grafo grafo) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(Grafo grafo) {

    }

    public static void addPlanta(Planta nuevaPlanta) {
    }

    public static void addruta(Ruta nuevaRuta) {
    }

    public static void crearGrafo(Grafo grafo) {
    }
}
