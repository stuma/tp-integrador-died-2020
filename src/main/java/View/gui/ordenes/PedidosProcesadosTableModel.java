package View.gui.ordenes;

import Model.OrdenPedido;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PedidosProcesadosTableModel extends AbstractTableModel {

    //Muestro tablas de todas las ordenes de pedidos: Id, Planta destino, fecha maxima entrega,
    //TODO Agrear columna con items (por lo menos, los insumos)
    private String[] columnNames =  {"Id","Planta Origen", "Planta Destino", "Fecha Máxima de Entrega"};
    private List<OrdenPedido> data;

    public PedidosProcesadosTableModel(List<OrdenPedido> datos) {

        this.data = datos;
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
//        if (col < 2) {
//            return false;
//        } else {
//            return true;
//        }
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {

        OrdenPedido ped = this.data.get(row);

        switch(col) {
            case 0:
                return ped.getId();
            case 1:
                return ped.getPlantaOrigen().getNombre();
            case 2:
                return ped.getPlantaDestino().getNombre();
            case 3:
                return ped.getFechaEntrega();
        }
        return null;
    }


}
