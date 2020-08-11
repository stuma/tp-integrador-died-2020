package View.gui.insumos;


import Model.Insumo;
import View.guiController.StockGuiController;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class InsumoTableModel  extends AbstractTableModel {

	
	private String[] columnNames =  {"Descripción","Tipo de Insumo","Unidad de Medida", "Costo por Unidad", "Kg. por Unidad de Medida", "Stock Total"};
	private List<Insumo> data;
	private StockGuiController controller;

	public InsumoTableModel(List<Insumo> datos, StockGuiController stockSe) {

		this.data = datos;
		this.controller = stockSe;

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
        return getValueAt(0, c).getClass();

	}
    
    public boolean isCellEditable(int row, int col) {

    	return false;
    }
    
	@Override
    public Object getValueAt(int row, int col) {

		Insumo i = data.get(row);

		switch(col) {
			case 0:
				return i.getDescripcion();
			case 1:
				return (i.getDensidad()==null ? "General": "Liquido" );
			case 2:
				return i.getUnidadMedida();
			case 3:
				return i.getCosto();
			case 4:
				return i.pesoPorUnidad();
			case 5:
				return this.controller.calcularStockTotal(i);
		}
        return null;
    }

}
