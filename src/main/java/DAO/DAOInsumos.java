package DAO;

import Model.Insumo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

//update
public class DAOInsumos implements DAO<Insumo>{

    private static DAOInsumos daoInsumos;
    private static Session session;

    public DAOInsumos(){

    }

    public static DAOInsumos getDaoInsumos(){
        if (daoInsumos == null){
            daoInsumos = new DAOInsumos();
        }
        return daoInsumos;
    }

    @Override
    public Optional<Insumo> get(int id) {
        session.beginTransaction();
        Insumo insumo = (Insumo) session.load(Insumo.class, id);
        Optional<Insumo> optional = Optional.ofNullable(insumo);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Insumo> getAll() {
        return null;// session.createQuery("SELECT i FROM Insumo i", Insumo.class).getResultList();
    }

    @Override
    public void save(Insumo insumo) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(insumo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Insumo insumo) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(insumo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Insumo insumo) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(insumo);
        session.getTransaction().commit();
        session.close();
    }
}
