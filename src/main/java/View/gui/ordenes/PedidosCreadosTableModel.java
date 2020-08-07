package View.gui.ordenes;

import Model.OrdenPedido;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PedidosCreadosTableModel extends AbstractTableModel {

    //Muestro tablas de todas las ordenes de pedidos: Id, Planta destino, fecha maxima entrega,
    //TODO Agrear columna con items (por lo menos, los insumos)
    private String[] columnNames =  {"Id","Planta Destino", "Fecha Máxima de Entrega"};
    private List<OrdenPedido> data;

    public PedidosCreadosTableModel(List<OrdenPedido> datos) {

        this.data = datos;
    }

    @Override
    public int getRowCount() {
        return this.data.size(); //TODO modificar esto
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
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
                return ped.getPlantaDestino().getNombre();
            case 2:
                return ped.getFechaEntrega();
        }
        return null;
    }

}
