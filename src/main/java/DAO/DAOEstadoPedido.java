package DAO;

import Model.EstadoPedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

//update
public class DAOEstadoPedido implements DAO<EstadoPedido> {

    private static DAOEstadoPedido daoEstadoPedido;

    private DAOEstadoPedido(){

    }

    public static DAOEstadoPedido getDaoEstadoPedido(){
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
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(estadoPedido);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(EstadoPedido id) {

    }

    @Override
    public void delete(EstadoPedido estadoPedido) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(estadoPedido);
        session.getTransaction().commit();
        session.close();
    }
}
