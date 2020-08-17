package View.gui.planta;

import View.guiController.PlantaGuiController;

import javax.swing.*;

public class MatrizCaminosMinimosFrame extends JFrame {

    private MatrizCaminoTableModel modeloTablaCaminosMinimos;
    private PlantaGuiController controller;
    private JTable tablaCaminosMinimos;

    public MatrizCaminosMinimosFrame(){

        super();
        this.controller = PlantaGuiController.getPlantaController();
        armarApp();

    }

    private void armarApp() {

        this.modeloTablaCaminosMinimos = new MatrizCaminoTableModel(this.controller.getListaPlantas(), this.controller.getMatrizCaminos());
        this.tablaCaminosMinimos = new JTable();
        tablaCaminosMinimos.setModel(modeloTablaCaminosMinimos);
        JScrollPane scrollPane2 = new JScrollPane(tablaCaminosMinimos);
        tablaCaminosMinimos.setFillsViewportHeight(true);
        tablaCaminosMinimos.setRowHeight(tablaCaminosMinimos.getRowHeight() + 5);
        scrollPane2.setMinimumSize( scrollPane2.getPreferredSize());
        this.add(scrollPane2);

    }

    public void actualizarTabla(){

        this.modeloTablaCaminosMinimos = new MatrizCaminoTableModel(this.controller.getListaPlantas(), this.controller.getMatrizCaminos());
        this.tablaCaminosMinimos = new JTable();
        tablaCaminosMinimos.setModel(modeloTablaCaminosMinimos);
        JScrollPane scrollPane2 = new JScrollPane(tablaCaminosMinimos);
        tablaCaminosMinimos.setFillsViewportHeight(true);
        tablaCaminosMinimos.setRowHeight(tablaCaminosMinimos.getRowHeight() + 5);
        scrollPane2.setMinimumSize( scrollPane2.getPreferredSize());
        this.add(scrollPane2);

    }

}
