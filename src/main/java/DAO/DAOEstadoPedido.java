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
    private static Session session;

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
        session.beginTransaction();
        EstadoPedido estadoPedido = (EstadoPedido) session.load(EstadoPedido.class, id);
        Optional<EstadoPedido> optional = Optional.ofNullable(estadoPedido);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<EstadoPedido> getAll() {
        //return session.createQuery("SELECT ep FROM EstadoPedido ep", EstadoPedido.class).getResultList();
        return null;
    }

    @Override
    public void save(EstadoPedido estadoPedido) {


        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(estadoPedido);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(EstadoPedido estadoPedido) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(estadoPedido);
        session.getTransaction().commit();
        session.close();
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
