package View.gui.ordenes;

import Model.Planta;
import View.guiController.OrdenPedidoGuiController;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Optional;

public class PlantaDisponibleTableModel extends AbstractTableModel {
    //nombre de planta
    //ruta más corta en KM desde esta planta a la seleccionada
    //ruta más corta en Hs desde esta planta a la seleccionada.

    private String[] columnNames =  {"Planta Origen", "Km de Ruta más Corta", "Hs de Ruta más Corta"};
    private List<Planta> data;
    private List<List<Planta>> caminosHs;
    private List<List<Planta>> caminosKm;
    private OrdenPedidoGuiController controller;

    public PlantaDisponibleTableModel(List<Planta> datos, List<List<Planta>> caminoHs, List<List<Planta>> caminokm) throws Exception {

        this.data = datos;
        this.caminosHs = caminoHs;
        this.caminosKm = caminokm;
        this.controller = OrdenPedidoGuiController.getOrdenPedidoController();
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

        Planta p = this.data.get(row);
        List<Planta> caminoH = this.caminosHs.get(row);
        List<Planta> caminoK = this.caminosKm.get(row);

        switch(col) {
            case 0:
                return p.getNombre();
            case 1:
                float hs = 0F;

                for(int i=0, j=1; j<caminoH.size(); i++, j++){

                    hs+=this.controller.getHsCamino(caminoH.get(i), caminoH.get(j));

                }
                return hs;
/*                StringBuilder s = new StringBuilder();
                for(int i=0; i<caminoH.size()-1; i++){

                    s.append(caminoH.get(i).getNombre()).append(" - ");

                }

                s.append(caminoH.get(caminoH.size() - 1).getNombre());
                return s.toString();*/
            case 2:

                float km = 0F;

                for(int i=0, j=1; j<caminoK.size(); i++, j++){

                    km+=this.controller.getKmCamino(caminoK.get(i), caminoK.get(j));

                }
                return km;

/*                StringBuilder k = new StringBuilder();
                for(int i=0; i<caminoK.size()-1; i++){

                    k.append(caminoK.get(i).getNombre()).append(" - ");

                }

                k.append(caminoK.get(caminoH.size() - 1).getNombre());
                return k.toString();*/
        }
        return null;
    }
}
