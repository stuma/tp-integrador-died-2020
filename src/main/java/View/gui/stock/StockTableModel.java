package View.gui.stock;

import Model.Stock;
import View.guiController.StockGuiController;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StockTableModel extends AbstractTableModel{

	private String[] columnNames =  {"Nombre de Planta","Descripción de Insumo","Stock en Planta", "Punto de Pedido en Planta", "Stock Total"};
	private List<Stock> data;
	private StockGuiController controller;
	
	public StockTableModel(StockGuiController con) {
		this.controller = con;
		this.data = this.controller.listarTodos();
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

    	return false;
    }
    
	@Override
    public Object getValueAt(int row, int col) {

	    Stock s = this.data.get(row);

		switch(col) {
			case 0:
				return s.getPlanta().getNombre();
			case 1:
				return s.getInsumo().getDescripcion();
			case 2:
				return s.getCantidad();
			case 3:
				return s.getPuntoPedido();
			case 4:
				return this.controller.calcularStockTotal(s.getInsumo());

		}
        return null;
    }


}
