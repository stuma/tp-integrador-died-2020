package View.gui.ordenes;

import Model.OrdenPedido;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PedidosCreadosTableModel extends AbstractTableModel {

    //Muestro tablas de todas las ordenes de pedidos: Id, Planta destino, fecha maxima entrega,
    private String[] columnNames =  {"Id","Planta Destino", "Fecha Máxima de Entrega"};
    private List<OrdenPedido> data;

    public PedidosCreadosTableModel(List<OrdenPedido> datos) {

        this.data = datos;
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int c) {

        Object value=this.getValueAt(0,c);
        return (value==null?Object.class:value.getClass());

        //return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {

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
                return ped.getFechaSolicitud();
        }
        return 0.0;
    }

}
