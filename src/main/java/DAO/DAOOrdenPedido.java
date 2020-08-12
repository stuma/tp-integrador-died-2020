package DAO;

import Model.Camion;
import Model.Grafo;
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
    private static Session session;

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
        session.beginTransaction();
        OrdenPedido ordenPedido = (OrdenPedido) session.load(OrdenPedido.class, id);
        Optional<OrdenPedido> optional = Optional.ofNullable(ordenPedido);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<OrdenPedido> getAll() {
        return session.createQuery("SELECT op FROM OrdenPedido op", OrdenPedido.class).getResultList();
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
    public void update(OrdenPedido ordenPedido) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ordenPedido);
        session.getTransaction().commit();
        session.close();
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
