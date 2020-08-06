package View.gui.ordenes;

import Model.Planta;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PlantaDisponibleTableModel extends AbstractTableModel {
    //nombre de planta
    //ruta más corta en KM desde esta planta a la seleccionada
    //ruta más corta en Hs desde esta planta a la seleccionada.

    private String[] columnNames =  {"Planta Origen", "Ruta más Corta (Km)", "Ruta más Corta (Hs)"};
    private List<Planta> data;
    private List<List<Planta>> caminosHs;
    private List<List<Planta>> caminosKm;

    public PlantaDisponibleTableModel(List<Planta> datos, List<List<Planta>> caminoHs, List<List<Planta>> caminokm) {

        this.data = datos;
        this.caminosHs = caminoHs;
        this.caminosKm = caminokm;

    }

    @Override
    public int getRowCount() {
        return this.data.size(); //TODO modificar esto
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

        Planta p = this.data.get(row);
        List<Planta> caminoH = this.caminosHs.get(row);
        List<Planta> caminoK = this.caminosKm.get(row);

        switch(col) {
            case 0:
                return p.getNombre();
            case 1: //TODO Cambiar por las horas, no por el camino.
                StringBuilder s = new StringBuilder();
                for(int i=0; i<caminoH.size()-1; i++){

                    s.append(caminoH.get(i).getNombre()).append(" - ");

                }

                s.append(caminoH.get(caminoH.size() - 1).getNombre());
                return s.toString();
            case 2:

                StringBuilder k = new StringBuilder();
                for(int i=0; i<caminoK.size()-1; i++){

                    k.append(caminoK.get(i).getNombre()).append(" - ");

                }

                k.append(caminoK.get(caminoH.size() - 1).getNombre());
                return k.toString();
        }
        return null;
    }
}
