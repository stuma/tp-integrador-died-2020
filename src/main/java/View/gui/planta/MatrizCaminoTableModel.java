package View.gui.planta;

import Model.Planta;
import View.guiController.PlantaGuiController;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MatrizCaminoTableModel extends AbstractTableModel {

    //Columnas de la tabla
    private String[] columnNames;
    private List<Planta> plantas;
    private Float[][] matriz;

    public MatrizCaminoTableModel(List<Planta> plantas, Float[][] matriz) {

        this.matriz = matriz;
        this.plantas = plantas;
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

        //return Math.min(this.plantas.size(), this.matriz.length);
        return this.plantas.size()+1;
    }

    @Override
    public int getColumnCount() {
        //return Math.min(this.plantas.size(), this.matriz.length);
        return this.plantas.size()+1;
    }

    public String getColumnName(int col) {

/*        if(col==0) return " ";
        else{
            return this.plantas.get(col-1).getNombre();
        }*/

        return " ";
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

        if(row==0){
            if(col==0) return "      ";

            return this.columnNames[col];
        }

        if(row<plantas.size()+1 && col<plantas.size()+1){

            if(col==0) return this.columnNames[row];
            return (this.matriz[row-1][col-1]==Float.MAX_VALUE? "-" : this.matriz[row-1][col-1]);

        }

        return 0.0;
    }

}
