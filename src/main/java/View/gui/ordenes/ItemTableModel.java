package View.gui.ordenes;

import Model.Item;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ItemTableModel extends AbstractTableModel {

    private String[] columnNames =  {"Insumo","Cantidad", "Precio"};
    private List<Item> data;

    public ItemTableModel(List<Item> datos) {

        this.data = datos;

    }

    @Override
    public int getRowCount() {
        return data.size();
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

        Item i = this.data.get(row);

        if(i==null) return null;

        switch(col) {
            case 0:
                return i.getInsumo().getDescripcion();
            case 1:
                return i.getCantidad();
            case 2:
                return  i.getInsumo().getCosto()*i.getCantidad();
        }

        return null;
    }

}
