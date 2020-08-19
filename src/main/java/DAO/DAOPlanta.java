package DAO;
import Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class DAOPlanta implements DAO<Planta>{

    private static DAOPlanta daoPlanta;
    private SessionFactory sessionFactory;

    public DAOPlanta(){
        //this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory= HibernateUtil.getSessionFactory();
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

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Planta planta = session.load(Planta.class, id);
        Optional<Planta> optional = Optional.ofNullable(planta);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Planta> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sentencia = "SELECT * FROM planta";
        Query query = session.createSQLQuery(sentencia).addEntity(Planta.class);
        List<Planta> lista = query.list();
        session.getTransaction().commit();
        session.close();

        return lista;
    }

    @Override
    public void save(Planta planta) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(planta);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Planta planta) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(planta);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Planta planta) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(planta);
        session.getTransaction().commit();
        session.close();
    }
}
