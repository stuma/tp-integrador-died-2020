package DAO;

import Model.OrdenPedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOOrdenPedido implements DAO<OrdenPedido>{

    //update
    private static DAOOrdenPedido daoOrdenPedido;

    private DAOOrdenPedido(){

    }

    public static DAOOrdenPedido getDaoOrdenPedido(){
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
    public void save(OrdenPedido ordenPedido) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ordenPedido);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(OrdenPedido id) {

    }

    @Override
    public void delete(OrdenPedido ordenPedido) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(ordenPedido);
        session.getTransaction().commit();
        session.close();
    }

    public List<OrdenPedido> buscarOrdenPorEstado (String descripcion){
        return new ArrayList<OrdenPedido>();
    }

}
