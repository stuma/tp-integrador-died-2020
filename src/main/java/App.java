import DAO.utils.ConexionDB;
import Model.EstadoPedido;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ConexionDB.getConexion();

        EstadoPedido estado = new EstadoPedido();
        estado.setDescripcion("Solicitado");

        //Example 5. Saving entities
        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(estado);
        session.getTransaction().commit();
        session.close();

        //Example 6. Obtaining a list of entities
        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from EstadoPedido " ).list();
        for ( EstadoPedido estadoPedido : (List<EstadoPedido>) result ) {
            System.out.println( "EstadoPedido (" + estadoPedido.getId() + ") : " + estadoPedido.getDescripcion() );
        }
        session.getTransaction().commit();
        session.close();


    }
}
