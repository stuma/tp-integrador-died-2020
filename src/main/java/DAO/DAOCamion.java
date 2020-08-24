package DAO;

import Model.Camion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//update
public class DAOCamion implements DAO<Camion> {

    private SessionFactory sessionFactory;
    private static DAOCamion daoCamion;

    public DAOCamion(){
        //this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    public static DAOCamion getDaoCamion(){
        if (daoCamion == null){
            daoCamion = new DAOCamion();
        }
        return daoCamion;
    }


    @Override
    public Optional<Camion> get(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Camion camion = session.load(Camion.class, id);
        Optional<Camion> optional = Optional.ofNullable(camion);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Camion> getAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sentencia = "SELECT * FROM camion";
        Query query = session.createSQLQuery(sentencia).addEntity(Camion.class);
        List<Camion> lista = query.list();
        session.getTransaction().commit();
        session.close();

/*
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT ep FROM EstadoPedido ep", Camion.class).getResultList();
*/
        return lista;

    }

    @Override
    public void save(Camion camion) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(camion);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Camion camion) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(camion);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Camion camion) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(camion);
        session.getTransaction().commit();
        session.close();
    }

    //TODO Implementar


    public List<Camion> getListaCamionesAtributos (Camion camion){
        return getAll();
       // return new ArrayList<Camion>();
    }


}
