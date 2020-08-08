package View.gui.camiones;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Camion;

public class CamionTableModel extends AbstractTableModel {
	//https://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data
	
	
	//Columnas de la tabla
    private String[] columnNames =  {"Patente","Modelo","Marca", "Km Recorridos", "Fecha de Compra", "Costo por Km", "Costo por Hora"};
	private List<Camion> data ;
    
    
    //Constructor
	public CamionTableModel(List<Camion> camiones) {
		this.data = camiones; //El atributo data contiene la información de la lista actual de camiones. 
	}
	
	
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Camion cam = data.get(row);
        switch(col) {
	        case 0:
	        	return cam.getPatente();
	        case 1:
	        	return cam.getModelo(); 
	        case 2:
	        	return cam.getMarca(); 
	        case 3:
	        	return cam.getKmRecorridos();
	        case 4:
	        	return cam.getFechaCompra(); 
	        case 5:
	        	return cam.getCostoKm();
	        case 6:
	        	return cam.getCostoHora(); 
        }
        return null;
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
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

//    /*
//     * Don't need to implement this method unless your table's
//     * data can change.
//     */
    public void setValueAt(Object value, int row, int col) {
    	
    	//Selecciono el elemento cuya fila fue seleccionada
    	Camion c = data.get(row);
    	
    	if(!(value instanceof Camion)) return;
    	
        switch(col) {
	        case 0:
	        	//Si se modificó la columna 0, se modificó la patente
	        	c.setPatente(((Camion)value).getPatente());
	        case 1:
	        	c.setModelo(((Camion)value).getModelo());
	        case 2:
	        	c.setMarca(((Camion)value).getMarca());
	        case 3:
	        	c.setKmRecorridos((((Camion)value).getKmRecorridos()));
	        case 4:
	        	c.setFechaCompra(((Camion)value).getFechaCompra());
	        case 5:
	        	c.setCostoKm(((Camion)value).getCostoKm());
	        case 6:
	        	c.setCostoHora(((Camion)value).getCostoHora());
        }

        // data[row][col] = value;
       //fireTableCellUpdated(row, col);
        
    }
    
//    public Object getValueAt(int row) {
//    	
//    	return this.data.get(row);
//    
//    }
//    
}