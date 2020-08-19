package DAO;

import Model.Ruta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class DAORuta implements DAO<Ruta>{

    private static DAORuta daoRuta;
    private SessionFactory sessionFactory;

    private DAORuta(){
        //this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    public static DAORuta getDaoRuta(){
        if (daoRuta == null){
            daoRuta = new DAORuta();
        }
        return daoRuta;
    }

    @Override
    public Optional<Ruta> get(int id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ruta stock = session.load(Ruta.class, id);
        Optional<Ruta> optional = Optional.ofNullable(stock);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Ruta> getAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sentencia = "SELECT * FROM ruta";
        Query query = session.createSQLQuery(sentencia).addEntity(Ruta.class);
        List<Ruta> lista = query.list();
        session.getTransaction().commit();
        session.close();

        return lista;
    }

    @Override
    public void save(Ruta stock) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(stock);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Ruta stock) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(stock);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Ruta stock) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(stock);
        session.getTransaction().commit();
        session.close();
    }

}