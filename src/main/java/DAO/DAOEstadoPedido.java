package DAO;

import Model.EstadoPedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

//update
public class DAOEstadoPedido implements DAO<EstadoPedido> {

    private static DAOEstadoPedido daoEstadoPedido;
    private SessionFactory sessionFactory;

    private DAOEstadoPedido(){
        //this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    public static DAOEstadoPedido getDaoEstadoPedido(){
        if (daoEstadoPedido == null){
            daoEstadoPedido = new DAOEstadoPedido();
        }
        return daoEstadoPedido;
    }

    @Override
    public Optional<EstadoPedido> get(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        EstadoPedido estadoPedido = session.load(EstadoPedido.class, id);
        Optional<EstadoPedido> optional = Optional.ofNullable(estadoPedido);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<EstadoPedido> getAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT ep FROM EstadoPedido ep", EstadoPedido.class).getResultList();
        //return null;
    }

    @Override
    public void save(EstadoPedido estadoPedido) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(estadoPedido);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(EstadoPedido estadoPedido) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(estadoPedido);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(EstadoPedido estadoPedido) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(estadoPedido);
        session.getTransaction().commit();
        session.close();

    }
}
