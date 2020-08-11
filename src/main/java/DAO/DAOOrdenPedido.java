package DAO;

import Model.OrdenPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOOrdenPedido implements DAO<OrdenPedido>{

    private static DAOOrdenPedido daoOrdenPedido;

    public DAOOrdenPedido(){

    }

    public static DAOOrdenPedido getDaoInsumos(){
        if (daoOrdenPedido == null){
            daoOrdenPedido = new DAOOrdenPedido();
        }
        return daoOrdenPedido;
    }

    @Override
    public Optional<OrdenPedido> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<OrdenPedido> getAll() {
        return null;
    }

    @Override
    public static void save(OrdenPedido ordenPedido) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(OrdenPedido ordenPedido) {

    }

    public List<OrdenPedido> buscarOrdenPorEstado (String descripcion){
        return new ArrayList<OrdenPedido>();
    }

}
