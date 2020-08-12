package DAO;

import Model.Camion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//update
public class DAOCamion implements DAO<Camion> {

    private static Session session;
    private static DAOCamion daoCamion;

    private DAOCamion(){

    }

    public static DAOCamion getDaoCamion(){
        if (daoCamion == null){
            daoCamion = new DAOCamion();
        }
        return daoCamion;
    }


    @Override
    public Optional<Camion> get(int id) {
        session.beginTransaction();
        Camion camion = (Camion) session.load(Camion.class, id);
        Optional<Camion> optional = Optional.ofNullable(camion);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Camion> getAll() {
        return null; // session.createQuery("SELECT c FROM Camion c", Camion.class).getResultList();
    }

    @Override
    public void save(Camion camion) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(camion);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Camion camion) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(camion);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Camion camion) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(camion);
        session.getTransaction().commit();
        session.close();
    }

    public Optional<Camion> getCamionPatente(String patente){
        return Optional.empty();
    }

    public List<Camion> getListaCamionesAtributos (Camion camion){
        return new ArrayList<Camion>();
    }


}
