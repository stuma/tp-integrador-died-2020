package View.gui.planta;

import Model.Planta;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MatrizCaminoTableModel extends AbstractTableModel {

    //Columnas de la tabla
    private String[] columnNames;
    private List<Planta> plantas;
    private Integer[][] matriz;

    //TODO Asumo que las plantas están ordenadas alfabéticamente, y según eso, la matriz está ordenada así
    public MatrizCaminoTableModel(List<Planta> plantas, Integer[][] matriz) {
        this.plantas = plantas;
        this.matriz = matriz;

        if(this.plantas == null){

            this.plantas = new ArrayList<Planta>();

            return;
        }

        this.columnNames=new String[plantas.size()+1];

        this.columnNames[0] = "        ";
        for(int i=1; i<=this.plantas.size(); i++){

            this.columnNames[i] = this.plantas.get(i-1).getNombre();

        }

    }

    @Override
    public int getRowCount() {
        return this.plantas.size();
    }

    @Override
    public int getColumnCount() {
        return this.plantas.size()+1;
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

        if(row<plantas.size() && col<plantas.size()){

            if(col==0) return this.plantas.get(row).getNombre();

            return this.matriz[row][col-1];
        }
        return null;
    }

}
