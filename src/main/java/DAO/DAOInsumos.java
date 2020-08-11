package DAO;

import Model.Insumo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class DAOInsumos implements DAO<Insumo>{

    private static DAOInsumos daoInsumos;

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
        return Optional.empty();
    }

    @Override
    public List<Insumo> getAll() {
        return null;
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
    public void update(int id) {

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
