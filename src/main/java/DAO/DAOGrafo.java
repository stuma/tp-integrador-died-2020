package DAO;

import Model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

//update
public class DAOGrafo implements DAO<Grafo>{

    private SessionFactory sessionFactory;
    private static DAOGrafo daoGrafo;

    private DAOGrafo(){
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
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
    public List<Grafo> getAll() {
        return null; //session.createQuery("SELECT g FROM Grafo g", Grafo.class).getResultList();
    }

    @Override
    public void save(Grafo grafo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(grafo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Grafo grafo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(grafo);
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

    //TODO Implementar
    public static void addPlanta(Planta nuevaPlanta) {
    }

    public static void addruta(Ruta nuevaRuta) {
    }

    public static void crearGrafo(Grafo grafo) {
    }
}
