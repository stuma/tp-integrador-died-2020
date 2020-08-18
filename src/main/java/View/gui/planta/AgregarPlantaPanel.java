package View.gui.planta;

import Model.Planta;
import View.guiController.PlantaGuiController;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgregarPlantaPanel extends JPanel {

    private JLabel lblTitulo = new JLabel("Alta de Plantas:");
    private JLabel lblSubtitulo1 = new JLabel("Agregar Planta:");
    private JLabel lblSubtitulo2 = new JLabel("Lista de Plantas: ");
    private JLabel lblSubtitulo3 = new JLabel("Calculo de Caminos Mínimos:");
    private JLabel lblSubtitulo4 = new JLabel("Matriz de Caminos Mínimos:");

    private JLabel lblPlanta = new JLabel("Nombre de Planta: *");
    private JTextField txtPlanta;

    private JLabel lblCaminos = new JLabel("Seleccionar Tipo de Matriz:");
    private JComboBox<String> txtCaminos;

    private JButton btnAceptar;
    private JButton btnCancelar;
    private JButton btnCalcular;

    //Errores:
    private JLabel lblErrorPlanta = new JLabel("Campo alfanumérico y obligatorio.");


    //Tabla Plantas-PageRank
    private JTable tablaPageRank;
    private PageRankTableModel modeloTablaPageRank;

    //Matriz Caminos Mínimo Hs.
    private JTable tablaCaminosMinimos;
    private MatrizCaminoTableModel modeloTablaCaminosMinimos;

    private PlantaGuiController controller;
    private MatrizCaminosMinimosFrame matrizPopUp;

    public AgregarPlantaPanel() {
        super();
        this.controller = PlantaGuiController.getPlantaController();
        this.matrizPopUp = new MatrizCaminosMinimosFrame();
        this.armarPanel();
    }

    private void armarPanel() {

        //Agrega un Layout
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);


        GridBagConstraints constraintsTitulo = new GridBagConstraints();
        constraintsTitulo.fill = GridBagConstraints.VERTICAL;
        constraintsTitulo.anchor = GridBagConstraints.CENTER;
        constraintsTitulo.gridwidth=8;
        constraintsTitulo.insets = new Insets(0, 0, 15, 0);

        GridBagConstraints constraintsSubtitulos = new GridBagConstraints();
        constraintsSubtitulos.gridwidth=8;
        constraintsSubtitulos.weightx=1.0;
        constraintsSubtitulos.weighty=(double)(1/16);
        constraintsSubtitulos.insets = new Insets(20, 10, 10, 0);
        constraintsSubtitulos.fill = GridBagConstraints.HORIZONTAL;

        GridBagConstraints constraintsLabels = new GridBagConstraints();
        constraintsLabels.gridwidth = 1;
        constraintsLabels.weightx=0.12; //
        constraintsLabels.weighty=0;
        constraintsLabels.gridheight=1;
        constraintsLabels.insets = new Insets(5, 5, 5, 0); //-> Este determina la separación entre objetos.
        constraintsLabels.ipadx = 5;
        constraintsLabels.ipady=5;
        constraintsLabels.fill = GridBagConstraints.NONE;
        constraintsLabels.anchor = GridBagConstraints.LINE_END;

        GridBagConstraints constraintsTextfields = new GridBagConstraints();
        constraintsTextfields.gridwidth = 1;
        constraintsTextfields.weightx=0.12; //
        constraintsTextfields.weighty=0;
        constraintsTextfields.gridheight=1;
        constraintsTextfields.insets = new Insets(5, 5, 5, 0); //-> Este determina la separación entre objetos.
        constraintsTextfields.ipadx = 5;
        constraintsTextfields.ipady=5;
        constraintsTextfields.anchor = GridBagConstraints.LINE_START;
        constraintsTextfields.fill = GridBagConstraints.HORIZONTAL;

        //Botones
        GridBagConstraints constraintsBotones = new GridBagConstraints();
        constraintsBotones.fill = GridBagConstraints.NONE;
        constraintsBotones.insets = new Insets(5, 15, 10, 0);

        GridBagConstraints constraintsTablas = new GridBagConstraints();
        constraintsTablas.gridwidth=3;
        constraintsTablas.gridheight=2;
        constraintsTablas.anchor = GridBagConstraints.FIRST_LINE_START;
        constraintsTablas.fill = GridBagConstraints.NONE;
        constraintsTablas.insets = new Insets(0, 20, 0, 20);

        GridBagConstraints constraintsErrores = new GridBagConstraints();
        constraintsErrores.gridwidth = 2;
        constraintsErrores.gridheight = 1;
        constraintsErrores.weightx = 1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
        constraintsErrores.weighty = (double) (1 / 16);
        constraintsErrores.insets = new Insets(0, 5, 10, 5);
        constraintsErrores.anchor = GridBagConstraints.FIRST_LINE_END;
        constraintsErrores.fill = GridBagConstraints.NONE;





        //Titulo
        constraintsTitulo.gridx = 0;
        constraintsTitulo.gridy = 0;
        lblTitulo.setFont(new Font("System", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        this.add(lblTitulo,constraintsTitulo);


        //Subtitulo: Agregar Planta
        constraintsSubtitulos.gridx = 0; //Columna 0
        constraintsSubtitulos.gridy = 1; //Fila 2
        lblSubtitulo1.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo1.setForeground(Color.BLACK);
        this.add(lblSubtitulo1,constraintsSubtitulos);



        //Label Nombre de Planta
        constraintsLabels.gridx = 0;
        constraintsLabels.gridy = 2;
        lblPlanta.setPreferredSize(new Dimension(130, 17));
        lblPlanta.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblPlanta,constraintsLabels);
        constraintsLabels.insets = new Insets(5, 5, 5, 0);

        //TextField Nombre de Planta
        constraintsTextfields.gridx = 1; //Va al lado del Label
        constraintsTextfields.gridy = 2;
        this.txtPlanta = new JTextField(0);
        this.txtPlanta.setMinimumSize(new Dimension(200,20));
        this.add(txtPlanta,constraintsTextfields);

        //Error Nombre de Planta
        constraintsErrores.gridx = 0; //Columna 0
        constraintsErrores.gridy = 3; //Fila 2
        this.lblErrorPlanta.setFont(new Font("Calibri", Font.PLAIN, 13));
        this.lblErrorPlanta.setForeground(Color.RED);
        this.add(this.lblErrorPlanta, constraintsErrores);
        this.lblErrorPlanta.setVisible(false);

        //Botón Agregar
        constraintsBotones.gridx = 2;
        constraintsBotones.gridy = 2;
        constraintsBotones.anchor = GridBagConstraints.LINE_END;
        this.btnAceptar = new JButton("Agregar");
        this.btnAceptar.setPreferredSize(new Dimension(90,25));
        this.btnAceptar.addActionListener(e -> {

            limpiarErrores();
            try {
                this.controller.guardar(this);

            } catch (Exception e1) {
                e1.printStackTrace();
                this.mostrarError("Error al guardar", e1.getMessage());
                return;
            }
            this.limpiarFormulario();
            this.actualizarTablas();

        });
        this.add(btnAceptar,constraintsBotones);

        //Botón Cancelar
        constraintsBotones.gridx = 3;
        constraintsBotones.gridy = 2;
        constraintsBotones.anchor = GridBagConstraints.LINE_START;
        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.setPreferredSize(new Dimension(90,25));
        this.add(btnCancelar,constraintsBotones);
        this.btnCancelar.addActionListener(e -> {

/*            limpiarErrores();
            this.limpiarFormulario();
            this.actualizarTablas();*/
            this.removeAll();
            revalidate();
            repaint();

        });
        this.add(btnCancelar,constraintsBotones);

        //Subtitulo "Lista de Plantas":
        constraintsSubtitulos.gridx = 0;
        constraintsSubtitulos.gridy = 4;
        lblSubtitulo2.setPreferredSize(new Dimension(130, 17));
        lblSubtitulo2.setFont(new Font("Calibri", Font.BOLD, 15));
        this.add(lblSubtitulo2,constraintsSubtitulos);

        //Tabla Plantas - Page Rank
        constraintsTablas.gridx = 0;
        constraintsTablas.gridy = 5;
        this.modeloTablaPageRank = new PageRankTableModel(this.controller.getListaPlantas(), this.controller.getPageRank());
        this.tablaPageRank = new JTable();
        tablaPageRank.setModel(modeloTablaPageRank);
        tablaPageRank.setFillsViewportHeight(true);
        tablaPageRank.setMaximumSize(new Dimension(100,10));
        JScrollPane scrollPane = new JScrollPane(tablaPageRank);
        scrollPane.setMaximumSize( new Dimension(100, 10));
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tablaPageRank.getModel());
        tablaPageRank.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);
        this.add(scrollPane,constraintsTablas);


        //Subtitulo "Matriz Caminos Mínimos":
        constraintsSubtitulos.gridx = 4;
        constraintsSubtitulos.gridy = 1;
        lblSubtitulo3.setPreferredSize(new Dimension(130, 17));
        lblSubtitulo3.setFont(new Font("Calibri", Font.BOLD, 15));
        this.add(lblSubtitulo3,constraintsSubtitulos);

        //Label Matriz de caminos
        constraintsLabels.gridx = 4;
        constraintsLabels.gridy = 2;
        this.lblCaminos.setPreferredSize(new Dimension(160, 17));
        this.lblCaminos.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblCaminos,constraintsLabels);

        //ComboBox Matriz de Caminos
        constraintsTextfields.gridx = 5; //Va al lado del Label
        constraintsTextfields.gridy = 2;
        constraintsTextfields.fill = GridBagConstraints.HORIZONTAL;
        constraintsTextfields.insets = new Insets(5, 15, 5, 20);
        String[] matriz = {"Caminos Mínimos por Hora", "Caminos Mínimos por Km"};
        this.txtCaminos= new JComboBox<String>(matriz);
        this.txtCaminos.setPreferredSize(new Dimension(200, 20));
        this.add(txtCaminos,constraintsTextfields);

        //Botón Calcular
        constraintsBotones.gridx = 5;
        constraintsBotones.gridy = 3;
        constraintsBotones.anchor = GridBagConstraints.LINE_END;
        constraintsBotones.insets = new Insets(5, 15, 10, 20);
        this.btnCalcular = new JButton("Calcular");
        this.btnCalcular.setPreferredSize(new Dimension(90,25));
        this.btnCalcular.addActionListener(e -> {

            limpiarErrores();
            try {

                matrizPopUp.setVisible(false);
                this.controller.calcularMatriz(this.txtCaminos.getSelectedIndex());
                matrizPopUp.actualizarTabla();
                matrizPopUp.setTitle("Matriz de Caminos Mínimos");
                matrizPopUp.setSize(new Dimension(550, 450));
                matrizPopUp.setLocation(((int)this.getPreferredSize().getWidth())-200, ((int)this.getPreferredSize().getHeight())-370);
                matrizPopUp.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                matrizPopUp.setVisible(true);

            } catch (Exception e1) {
                e1.printStackTrace();
                this.mostrarError("Error al Calcular", e1.getMessage());
                return;
            }
            this.limpiarFormulario();
            this.actualizarTablas();

        });
        this.add(btnCalcular,constraintsBotones);


        //Subtitulo "Matriz Caminos Mínimos":
        constraintsSubtitulos.gridx = 4;
        constraintsSubtitulos.gridy = 4;
        lblSubtitulo4.setPreferredSize(new Dimension(130, 17));
        lblSubtitulo4.setFont(new Font("Calibri", Font.BOLD, 15));
        this.add(lblSubtitulo4,constraintsSubtitulos);

        //Tabla Caminos Minimos 1
        constraintsTablas.gridx = 4;
        constraintsTablas.gridy = 5;
        matrizPopUp.setTitle("Matriz de Caminos Mínimos");
        matrizPopUp.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        matrizPopUp.setLocation(((int)this.getPreferredSize().getWidth())-150, ((int)this.getPreferredSize().getHeight())-350);
        matrizPopUp.setSize(new Dimension(550, 450));

/*        this.modeloTablaCaminosMinimos = new MatrizCaminoTableModel(this.controller.getListaPlantas(), this.controller.getMatrizCaminos());
        this.tablaCaminosMinimos = new JTable();
        tablaCaminosMinimos.setModel(modeloTablaCaminosMinimos);
        JScrollPane scrollPane2 = new JScrollPane(tablaCaminosMinimos);
        tablaCaminosMinimos.setFillsViewportHeight(true);
        tablaCaminosMinimos.setRowHeight(tablaCaminosMinimos.getRowHeight() + 5);
        scrollPane2.setMinimumSize( scrollPane2.getPreferredSize());
        this.add(scrollPane2,constraintsTablas);*/


    }

    public void mostrarError(String titulo,String detalle) {
        JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
        JOptionPane.showMessageDialog(padre,
                detalle,titulo,
                JOptionPane.ERROR_MESSAGE);
    }


    private void limpiarFormulario() {

        this.txtPlanta.setText("");

    }

    private void limpiarErrores(){

        this.lblErrorPlanta.setVisible(false);

    }

    public void actualizarTablas(){

        //this.modeloTablaCaminosMinimos.fireTableDataChanged();
        this.modeloTablaPageRank.actualizar(this.controller.getPageRank());
        this.modeloTablaPageRank.fireTableDataChanged();

    }

    public void mostrarErrores(List<Integer> campos){

        for(Integer campo : campos){
            if (campo == 0) {
                this.lblErrorPlanta.setVisible(true);
            }
        }
    }

    public void mostrarErrores(Boolean[] campos){

        int i=0;
        while(i<campos.length) {
            if(!campos[i]) {
                if (i == 0) {
                    this.lblErrorPlanta.setVisible(true);
                }
            }
            i++;
        }
    }




    //Getters para el Controller a la hora de ejecutar el save o guardar
    public JTextField getTxtPlanta() {
        return txtPlanta;
    }

    public JComboBox<String> getTxtCaminos() {
        return txtCaminos;
    }
}
