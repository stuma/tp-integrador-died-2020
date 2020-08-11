package DAO;

import Model.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class DAOStock implements DAO<Stock>{

    private static DAOStock daoStock;

    private DAOStock(){

    }

    public static DAOStock getDaoInsumos(){
        if (daoStock == null){
            daoStock = new DAOStock();
        }
        return daoStock;
    }

    @Override
    public Optional<Stock> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Stock> getAll() {
        return null;
    }

    @Override
    public void save(Stock stock) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(stock);
        session.getTransaction().commit();
        session.close();
    }



    @Override
    public void update(int id) {

    }

    @Override
    public void delete(Stock stock) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(stock);
        session.getTransaction().commit();
        session.close();
    }

}
