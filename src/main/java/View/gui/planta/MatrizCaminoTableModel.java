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
    private double[][] matriz;
    private PlantaGuiController controller;

    public MatrizCaminoTableModel(List<Planta> plantas, double[][] matriz) {

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

    //TODO Armar esto de nuevo
    @Override
    public Object getValueAt(int row, int col) {

        this.columnNames[0] = "        ";
        for(int i=1; i<=this.plantas.size(); i++){

            this.columnNames[i] = this.plantas.get(i-1).getNombre();

        }

        if(row<plantas.size() && col<plantas.size()){

            if(col==0) return this.plantas.get(row).getNombre();

            return 0.0; //this.matriz[row][col-1];
        }

        return 0.0;
    }

}
