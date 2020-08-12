import DAO.DAOEstadoPedido;
import Model.EstadoPedido;

public class Main {

    public static void main(final String[] args) throws Exception {
        EstadoPedido estadoPedido = new EstadoPedido();
        estadoPedido.setDescripcion("estado de prueba");

        DAOEstadoPedido daoEstadoPedido = DAOEstadoPedido.getDaoEstadoPedido();

        daoEstadoPedido.save(estadoPedido);

    }
}