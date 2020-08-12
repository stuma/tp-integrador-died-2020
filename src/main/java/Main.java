import DAO.DAOEstadoPedido;
import Model.EstadoPedido;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {

    public static void main(final String[] args) throws Exception {
        EstadoPedido estadoPedido = new EstadoPedido();
        estadoPedido.setDescripcion("estado de prueba");

        DAOEstadoPedido daoEstadoPedido = DAOEstadoPedido.getDaoEstadoPedido();

        daoEstadoPedido.save(estadoPedido);

    }
}