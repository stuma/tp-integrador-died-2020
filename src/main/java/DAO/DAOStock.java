package DAO;

import Model.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

//update
public class DAOStock implements DAO<Stock>{

    private static DAOStock daoStock;
    private SessionFactory sessionFactory;

    private DAOStock(){
        //this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    public static DAOStock getDaoStock(){
        if (daoStock == null){
            daoStock = new DAOStock();
        }
        return daoStock;
    }

    @Override
    public Optional<Stock> get(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Stock stock = session.load(Stock.class, id);
        Optional<Stock> optional = Optional.ofNullable(stock);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Stock> getAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sentencia = "SELECT * FROM stock";
        Query query = session.createSQLQuery(sentencia).addEntity(Stock.class);
        List<Stock> lista = query.list();
        session.getTransaction().commit();
        session.close();

        return lista;
    }

    @Override
    public void save(Stock stock) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(stock);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Stock stock) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(stock);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Stock stock) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(stock);
        session.getTransaction().commit();
        session.close();
    }

}
