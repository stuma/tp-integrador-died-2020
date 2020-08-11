package DAO;

import Model.EstadoPedido;

import java.util.List;
import java.util.Optional;

public class DAOEstadoPedido implements DAO<EstadoPedido> {

    private static DAOEstadoPedido daoEstadoPedido;

    private DAOEstadoPedido(){

    }

    public static DAOEstadoPedido getDaoInsumos(){
        if (daoEstadoPedido == null){
            daoEstadoPedido = new DAOEstadoPedido();
        }
        return daoEstadoPedido;
    }

    @Override
    public Optional<EstadoPedido> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<EstadoPedido> getAll() {
        return null;
    }

    @Override
    public void save(EstadoPedido estadoPedido) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(EstadoPedido estadoPedido) {

    }
}
