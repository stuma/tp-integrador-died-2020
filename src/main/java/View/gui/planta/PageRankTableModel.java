package View.gui.planta;

import Model.Planta;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Map;

public class PageRankTableModel extends AbstractTableModel {

    //Columnas de la tabla
    private String[] columnNames =  {"Nombre de Planta","Page Rank"};
    private List<Planta> plantas;
    private Map<Planta, Double> pageRank;

    //Constructor
    public PageRankTableModel(List<Planta> p, Map<Planta, Double> pr) {
        this.plantas = p; //El atributo data contiene la información de la lista actual de camiones.
        this.pageRank = pr;
    }

    @Override
    public int getRowCount() {
        return plantas.size();//plantas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {

        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Planta p = plantas.get(row);

        switch (col){
            case 0:
                return p.getNombre();

            case 1:
                return pageRank.get(p);
        }

        return null;
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


}
