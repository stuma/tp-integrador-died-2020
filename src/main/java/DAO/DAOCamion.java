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
        String sentencia = "SELECT * FROM camion";
        String where = " WHERE ";
        List<String> parametros = new ArrayList<>();

        //patente
        if(camion.getPatente() != null){
            String aux = "camion.patente = '" + camion.getPatente() + "'" ;
            parametros.add(aux);
        }
        //modelo
        if(camion.getModelo() != null){
            String aux = "camion.modelo = '" + camion.getModelo() + "'" ;
            parametros.add(aux);
        }
        //marca
        if(camion.getMarca() != null){
            String aux = "camion.marca = '" + camion.getMarca() + "'" ;
            parametros.add(aux);
        }
        //km recorridos
        if(camion.getKmRecorridos() != null){
            String aux = "camion.kmRecorridos = '" + camion.getKmRecorridos() + "'" ;
            parametros.add(aux);
        }
        //fecha compra
        if(camion.getFechaCompra() != null){
            String aux = "camion.fechaCompra = '" + camion.getFechaCompra() + "'" ;
            parametros.add(aux);
        }
        //costo por km
        if(camion.getCostoKm() != null){
            String aux = "camion.costoKm = '" + camion.getCostoKm() + "'" ;
            parametros.add(aux);
        }
        //costo por hora
        if(camion.getCostoHora() != null){
            String aux = "camion.costoHora = '" + camion.getCostoHora() + "'" ;
            parametros.add(aux);
        }

        if(!parametros.isEmpty()){
            sentencia += where;
            sentencia += String.join(" AND ",  parametros);
        }else{
            return getAll();
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(sentencia).addEntity(Camion.class);
        List<Camion> lista = query.list();
        session.getTransaction().commit();
        session.close();
        return lista;

    }


}
