package DAO;

import Model.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

//update
public class DAOStock implements DAO<Stock>{

    private static DAOStock daoStock;
    private static Session session;

    private DAOStock(){

    }

    public static DAOStock getDaoStock(){
        if (daoStock == null){
            daoStock = new DAOStock();
        }
        return daoStock;
    }

    @Override
    public Optional<Stock> get(int id) {
        session.beginTransaction();
        Stock stock = (Stock) session.load(Stock.class, id);
        Optional<Stock> optional = Optional.ofNullable(stock);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Stock> getAll() {
        return null;//session.createQuery("SELECT s FROM Stock s", Stock.class).getResultList();
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
    public void update(Stock stock) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(stock);
        session.getTransaction().commit();
        session.close();
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
