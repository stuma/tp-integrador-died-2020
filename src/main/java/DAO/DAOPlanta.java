package DAO;
import Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class DAOPlanta implements DAO<Planta>{

    private static DAOPlanta daoPlanta;
    private static Session session;

    public DAOPlanta(){
    }

    public static DAOPlanta getDaoPlanta(){
        if (daoPlanta == null){
            daoPlanta = new DAOPlanta();
        }
        return daoPlanta;
    }

    public List<Stock> getListaStockInsumo(Integer idPlanta){
        // call hibernet devovler la lista de stock
        return null;
    }


    @Override
    public Optional<Planta> get(int id) {
        session.beginTransaction();
        Planta planta = (Planta) session.load(Planta.class, id);
        Optional<Planta> optional = Optional.ofNullable(planta);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Planta> getAll() {
        return null;// session.createQuery("SELECT p FROM Planta p", Planta.class).getResultList();
    }

    @Override
    public void save(Planta planta) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(planta);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Planta planta) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(planta);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Planta planta) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(planta);
        session.getTransaction().commit();
        session.close();
    }
}
