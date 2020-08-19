package DAO;

import Model.OrdenPedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOOrdenPedido implements DAO<OrdenPedido>{

    //update
    private static DAOOrdenPedido daoOrdenPedido;
    private SessionFactory sessionFactory;

    private DAOOrdenPedido(){
        //this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.sessionFactory= HibernateUtil.getSessionFactory();
    }

    public static DAOOrdenPedido getDaoOrdenPedido(){
        if (daoOrdenPedido == null){
            daoOrdenPedido = new DAOOrdenPedido();
        }
        return daoOrdenPedido;
    }

    @Override
    public Optional<OrdenPedido> get(int id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        OrdenPedido ordenPedido = session.load(OrdenPedido.class, id);
        Optional<OrdenPedido> optional = Optional.ofNullable(ordenPedido);
        session.getTransaction().commit();
        session.close();

        return optional;
    }

    @Override
    public List<OrdenPedido> getAll() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sentencia = "SELECT * FROM ordenpedido";
        Query query = session.createSQLQuery(sentencia).addEntity(OrdenPedido.class);
        List<OrdenPedido> lista = query.list();
        session.getTransaction().commit();
        session.close();

        return lista;
    }

    @Override
    public void save(OrdenPedido ordenPedido) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ordenPedido);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(OrdenPedido ordenPedido) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ordenPedido);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(OrdenPedido ordenPedido) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(ordenPedido);
        session.getTransaction().commit();
        session.close();
    }

    //TODO Implementar
    public List<OrdenPedido> buscarOrdenPorEstado (String descripcion){
        return new ArrayList<OrdenPedido>();
    }

}
