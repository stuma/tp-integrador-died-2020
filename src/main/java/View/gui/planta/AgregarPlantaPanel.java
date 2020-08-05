package View.gui.planta;

import Model.Planta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgregarPlantaPanel extends JPanel {

    private JLabel lblTitulo = new JLabel("Alta de Plantas:");
    private JLabel lblSubtitulo1 = new JLabel("Agregar Planta:");
    private JLabel lblSubtitulo2 = new JLabel("Lista de Plantas: ");
    private JLabel lblSubtitulo3 = new JLabel("Matriz de Caminos Mínimos:");


    private JLabel lblPlanta = new JLabel("Nombre de Planta:");
    private JTextField txtPlanta;

    private JLabel lblCaminos = new JLabel("Seleccionar Matriz:");
    private JComboBox<String> txtCaminos;

    private JButton btnAceptar;
    private JButton btnCancelar;

    //Tabla Plantas-PageRank
    private JTable tablaPageRank;
    private PageRankTableModel modeloTablaPageRank;

    //Matriz Caminos Mínimo Hs.
    private JTable tablaCaminosMinimos;
    private MatrizCaminoTableModel modeloTablaCaminosMinimos;

    //TODO Agregar un Controller


    public AgregarPlantaPanel() {
        super();
        //Primero debo inicializar el controller ya que armarPanel requiere de controller
        //this.controller = new CamionController(this);
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
        constraintsLabels.gridx = 1;
        constraintsLabels.gridy = 2;
        lblPlanta.setPreferredSize(new Dimension(130, 17));
        lblPlanta.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblPlanta,constraintsLabels);
        constraintsLabels.insets = new Insets(5, 5, 5, 0);

        //TextField Nombre de Planta
        constraintsTextfields.gridx = 2; //Va al lado del Label
        constraintsTextfields.gridy = 2;
        this.txtPlanta = new JTextField(0);
        this.txtPlanta.setMinimumSize(new Dimension(200,20));
        this.add(txtPlanta,constraintsTextfields);



        //Botón Agregar
        constraintsBotones.gridx = 3;
        constraintsBotones.gridy = 2;
        constraintsBotones.anchor = GridBagConstraints.LINE_END;
        this.btnAceptar = new JButton("Agregar");
        this.btnAceptar.setPreferredSize(new Dimension(80,25));
        this.btnAceptar.addActionListener(e -> {

            //TODO Invocar al controller

                /*
                    try {
                        controller.guardar();
                    } catch (Exception e1) {
                        this.mostrarError("Error al guardar", e1.getMessage());
                    }
                    this.limpiarFormulario();
                    modeloTablaCamion.fireTableDataChanged();
                */
                }

        );
        this.add(btnAceptar,constraintsBotones);

        //Botón Cancelar
        constraintsBotones.gridx = 4;
        constraintsBotones.gridy = 2;
        constraintsBotones.anchor = GridBagConstraints.LINE_START;
        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.setPreferredSize(new Dimension(90,25));
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
        //TODO Cambiar argumentos por lo que sea que devuelva el controller
        this.modeloTablaPageRank = new PageRankTableModel(new ArrayList<Planta>(), new HashMap<Planta, Integer>());
        this.tablaPageRank = new JTable();
        tablaPageRank.setModel(modeloTablaPageRank);
        tablaPageRank.setFillsViewportHeight(true);
        tablaPageRank.setMaximumSize(new Dimension(100,10));
        JScrollPane scrollPane = new JScrollPane(tablaPageRank);
        scrollPane.setMaximumSize( new Dimension(100, 10));
        this.add(scrollPane,constraintsTablas);

        //Subtitulo "Matriz Caminos Mínimos":
        constraintsSubtitulos.gridx = 4;
        constraintsSubtitulos.gridy = 4;
        lblSubtitulo3.setPreferredSize(new Dimension(130, 17));
        lblSubtitulo3.setFont(new Font("Calibri", Font.BOLD, 15));
        this.add(lblSubtitulo3,constraintsSubtitulos);

        //Tabla Caminos Minimos 1
        constraintsTablas.gridx = 4;
        constraintsTablas.gridy = 5;
        //TODO Cambiar argumentos por lo que sea que devuelva el controller.
        List<Planta> p = new ArrayList<Planta>();
        p.add(new Planta("Planta 1"));
        p.add(new Planta("Planta 2"));
        p.add(new Planta("Planta 3"));
        Integer[][] camino = new Integer[p.size()][p.size()];
        this.modeloTablaCaminosMinimos = new MatrizCaminoTableModel(p, camino);

        this.tablaCaminosMinimos = new JTable();
        tablaCaminosMinimos.setModel(modeloTablaCaminosMinimos);
        JScrollPane scrollPane2 = new JScrollPane(tablaCaminosMinimos);
        tablaCaminosMinimos.setFillsViewportHeight(true);
        scrollPane.setMinimumSize( scrollPane.getPreferredSize());
        this.add(scrollPane2,constraintsTablas);

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


    //Getters para el Controller a la hora de ejecutar el save o guardar

    public JTextField getTxtPlanta() {
        return txtPlanta;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }


}
