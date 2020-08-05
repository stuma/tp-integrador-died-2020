package View.gui.stock;

import Model.Stock;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StockTableModel extends AbstractTableModel{

	private String[] columnNames =  {"Nombre de Planta","Descripción de Insumo","Stock en Planta", "Punto de Pedido en Planta", "Stock Total"};
	private List<Stock> data;
	
	public StockTableModel(List<Stock> datos) {
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
    	return true;
    }
    
	@Override
    public Object getValueAt(int row, int col) {

	    Stock s = this.data.get(row);

		switch(col) {
			case 0:
				return s.getPlanta().getNombre(); //TODO Necesito un service o hacer bidireccional la relación stock-Planta.
			case 1:
				return s.getInsumo().getDescripcion();
			case 2:
				return s.getCantidad();
			case 3:
				return s.getPuntoPedido();
			case 4:
				return 25; //TODO Debe llamar al service o inicializarse con el stock total.

		}
        return null;
    }


}
