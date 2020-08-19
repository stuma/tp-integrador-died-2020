package DAO;

import Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//update

public class DAOGrafo implements DAO<Grafo>{

    private SessionFactory sessionFactory;
    private static DAOGrafo daoGrafo;

    private DAOGrafo(){
        //this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    public static DAOGrafo getDaoGrafo(){
        if (daoGrafo == null){
            daoGrafo = new DAOGrafo();
        }
        return daoGrafo;
    }

    @Override
    public Optional<Grafo> get(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Grafo grafo = session.load(Grafo.class, id);
        Optional<Grafo> optional = Optional.ofNullable(grafo);
        session.getTransaction().commit();
        session.close();
        return optional;
    }

    @Override
    @Transactional
    public List<Grafo> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sentencia = "SELECT * FROM grafo";
        Query query = session.createSQLQuery(sentencia).addEntity(Grafo.class);
        List<Grafo> lista = query.list();
        session.getTransaction().commit();
        session.close();

        return lista;
    }

    @Override
    public void save(Grafo grafo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(grafo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Grafo grafo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(grafo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Grafo grafo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(grafo);
        session.getTransaction().commit();
        session.close();
    }

}
