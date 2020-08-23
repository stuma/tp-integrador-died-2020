import DAO.DAOEstadoPedido;
import Model.EstadoPedido;

public class InicializacionEstadoPedido {

    public static void main(final String[] args) throws Exception {

        DAOEstadoPedido daoEstadoPedido = DAOEstadoPedido.getDaoEstadoPedido();

        EstadoPedido estadoPedido1 = new EstadoPedido();
        estadoPedido1.setDescripcion("CREADA");
        daoEstadoPedido.save(estadoPedido1);

        EstadoPedido estadoPedido2 = new EstadoPedido();
        estadoPedido2.setDescripcion("PROCESADA");
        daoEstadoPedido.save(estadoPedido2);

        EstadoPedido estadoPedido3 = new EstadoPedido();
        estadoPedido3.setDescripcion("ENTREGADA");
        daoEstadoPedido.save(estadoPedido3);

        EstadoPedido estadoPedido4 = new EstadoPedido();
        estadoPedido4.setDescripcion("CANCELADA");
        daoEstadoPedido.save(estadoPedido4);
    }
}