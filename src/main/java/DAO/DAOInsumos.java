package DAO;

import Model.Insumo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

//update
public class DAOInsumos implements DAO<Insumo>{

    private SessionFactory sessionFactory;
    private static DAOInsumos daoInsumos;


    public DAOInsumos(){
        //this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    public static DAOInsumos getDaoInsumos(){
        if (daoInsumos == null){
            daoInsumos = new DAOInsumos();
        }
        return daoInsumos;
    }

    @Override
    public Optional<Insumo> get(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Insumo insumo = session.load(Insumo.class, id);
        Optional<Insumo> optional = Optional.ofNullable(insumo);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<Insumo> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sentencia = "SELECT * FROM insumo";
        Query query = session.createSQLQuery(sentencia).addEntity(Insumo.class);
        List<Insumo> lista = query.list();
        session.getTransaction().commit();
        session.close();

        return lista;
       // session.createQuery("SELECT i FROM Insumo i", Insumo.class).getResultList();
    }

    @Override
    public void save(Insumo insumo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(insumo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Insumo insumo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(insumo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Insumo insumo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(insumo);
        session.getTransaction().commit();
        session.close();
    }
}
