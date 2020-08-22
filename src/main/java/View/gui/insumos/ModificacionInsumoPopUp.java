package View.gui.insumos;

import View.guiController.InsumoGuiController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModificacionInsumoPopUp extends JFrame {

    //Titulos
    private JLabel lblTitulo = new JLabel("Modificación de Insumo:");
    private JLabel lblSubtitulo = new JLabel("Agregar Insumo:");

    //Campos
    private JLabel lblDescripcion = new JLabel("Descripción: *");
    private JTextField txtDescripcion;
    private JLabel lblTipo = new JLabel("Tipo de Insumo: *");
    private JComboBox<String> txtTipo;
    private JLabel lblUnidad = new JLabel("Unidad de Medida: *");
    private JComboBox<String> txtUnidad;
    private JLabel lblCostoU = new JLabel("Costo por Unidad de Medida: *");
    private JTextField txtCostoU;
    private JLabel lblDensidad = new JLabel("Densidad: (Kg/Unidad de Medida) *");
    private JTextField txtDensidad;
    private JLabel lblPeso = new JLabel("Peso (Kg): ");


    //Botones
    private JButton btnAgregar;
    private JButton btnCancelar;

    //Errores
    private JLabel lblErrorDescripcion = new JLabel("Campo Alfanumérico y Obligatorio.");
    private JLabel lblErrorCostoPorUnidad = new JLabel("Campo Numérico y Obligatorio.");
    private JLabel lblErrorDensidad = new JLabel("Campo Numérico y Obligatorio.");

    //Otros
    private InsumoGuiController controller;
    private InsumoPanel panelInsumo;
    private Integer fila;

    public ModificacionInsumoPopUp(InsumoPanel panel) {

        super();
        //Primero debo inicializar el controller ya que armarPanel requiere de controller
        this.controller = InsumoGuiController.getInsumoController();
        this.panelInsumo = panel;

        this.armarPanel();

    }
    private void armarPanel() {

        //Agrega un Layout
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);

        GridBagConstraints constraintsTitulos = new GridBagConstraints();
        constraintsTitulos.fill = GridBagConstraints.VERTICAL;
        constraintsTitulos.anchor = GridBagConstraints.CENTER;
        constraintsTitulos.gridwidth = 8;
        constraintsTitulos.insets = new Insets(0, 0, 15, 0);

        GridBagConstraints constraintsSubtitulos = new GridBagConstraints();
        constraintsSubtitulos.gridwidth = 8;
        constraintsSubtitulos.weightx = 1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
        constraintsSubtitulos.weighty = (double) (1 / 16);
        constraintsSubtitulos.insets = new Insets(0, 10, 20, 0);
        constraintsSubtitulos.fill = GridBagConstraints.HORIZONTAL;

        GridBagConstraints constraintsLabels = new GridBagConstraints();
        constraintsLabels.gridwidth = 1;
        constraintsLabels.gridheight = 1;
        constraintsLabels.weightx = 0.12; //
        constraintsLabels.weighty = 0;
        constraintsLabels.insets = new Insets(5, 5, 5, 5); //-> Este determina la separación entre objetos.
        constraintsLabels.ipadx = 5;
        constraintsLabels.ipady = 5;
        constraintsLabels.fill = GridBagConstraints.NONE;
        constraintsLabels.anchor = GridBagConstraints.LINE_END;

        GridBagConstraints constraintsTextFields = new GridBagConstraints();
        constraintsTextFields.gridwidth = 1;
        constraintsTextFields.gridheight = 1;
        constraintsTextFields.weightx = 1.0; //
        constraintsTextFields.weighty = 0;
        constraintsTextFields.insets = new Insets(5, 5, 5, 5); //-> Este determina la separación entre objetos.
        constraintsTextFields.ipadx = 5;
        constraintsTextFields.ipady = 5;
        constraintsTextFields.fill = GridBagConstraints.HORIZONTAL;
        constraintsTextFields.anchor = GridBagConstraints.LINE_START;

        GridBagConstraints constraintsErrores = new GridBagConstraints();
        constraintsErrores.gridwidth = 2;
        constraintsErrores.gridheight = 1;
        constraintsErrores.weightx = 1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
        constraintsErrores.weighty = (double) (1 / 16);
        constraintsErrores.insets = new Insets(0, 5, 10, 5);
        constraintsErrores.anchor = GridBagConstraints.FIRST_LINE_END;
        constraintsErrores.fill = GridBagConstraints.NONE;

        //Botones
        GridBagConstraints constraintsBotones = new GridBagConstraints();
        constraintsBotones.insets = new Insets(20, 15, 15, 0);
        constraintsBotones.anchor = GridBagConstraints.LINE_END;
        constraintsBotones.fill = GridBagConstraints.NONE;
        constraintsBotones.gridwidth = 1;
        constraintsBotones.gridheight = 1;

        //Tablas
        GridBagConstraints constraintsTablas = new GridBagConstraints();
        constraintsTablas.gridwidth = 5;
        constraintsTablas.gridheight = 2;
        constraintsTablas.fill = GridBagConstraints.BOTH;
        constraintsTablas.anchor = GridBagConstraints.FIRST_LINE_START;
        constraintsTablas.insets = new Insets(0, 10, 0, 0);


        //Titulo
        constraintsTitulos.gridx = 0;
        constraintsTitulos.gridy = 0;
        lblTitulo.setFont(new Font("System", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        this.add(lblTitulo, constraintsTitulos);


        //Subtitulo:
        constraintsSubtitulos.gridx = 0; //Columna 0
        constraintsSubtitulos.gridy = 1; //Fila 2
        lblSubtitulo.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo.setForeground(Color.BLACK);
        this.add(lblSubtitulo, constraintsSubtitulos);


        //Label Descripción
        constraintsLabels.gridx = 1;
        constraintsLabels.gridy = 2;
        lblDescripcion.setPreferredSize(new Dimension(70, 17));
        lblDescripcion.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblDescripcion, constraintsLabels);

        //TextField Descripción
        constraintsTextFields.gridx = 2; //Va al lado del Label
        constraintsTextFields.gridy = 2;
        this.txtDescripcion = new JTextField(0);
        this.txtDescripcion.setPreferredSize(new Dimension(200, 20));
        this.add(txtDescripcion, constraintsTextFields);

        //Error Descripcion
        constraintsErrores.gridx = 1; //Columna 0
        constraintsErrores.gridy = 3; //Fila 2
        this.lblErrorDescripcion.setFont(new Font("Calibri", Font.PLAIN, 13));
        this.lblErrorDescripcion.setForeground(Color.RED);
        this.add(this.lblErrorDescripcion, constraintsErrores);
        this.lblErrorDescripcion.setVisible(false);

        //Label Tipo de Insumo
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 2;
        this.lblTipo.setPreferredSize(new Dimension(92, 17));
        this.lblTipo.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblTipo, constraintsLabels);

        //ComboBox Tipo de Insumo
        constraintsTextFields.gridx = 4; //Va al lado del Label
        constraintsTextFields.gridy = 2;
        constraintsTextFields.weightx = 1.0;
        this.txtTipo = new JComboBox<String>(this.controller.getTiposDeInsumos());
        this.txtTipo.setPreferredSize(new Dimension(200, 20));
        this.add(txtTipo, constraintsTextFields);
        this.txtTipo.addItemListener(e -> {

            limpiarErrores();
            if (txtTipo.getSelectedIndex() == 1) {

                this.lblPeso.setVisible(false);
                this.lblDensidad.setVisible(true);

            }
            if (txtTipo.getSelectedIndex() == 0) {

                this.lblPeso.setVisible(true);
                this.lblDensidad.setVisible(false);

            }
        });

        //Label Unidad de Medida
        constraintsLabels.gridx = 1;
        constraintsLabels.gridy = 4;
        this.lblUnidad.setPreferredSize(new Dimension(110, 17));
        this.lblUnidad.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblUnidad, constraintsLabels);

        //ComboBox Unidad de Medida
        constraintsTextFields.gridx = 2; //Va al lado del Label
        constraintsTextFields.gridy = 4;
        this.txtUnidad = new JComboBox<String>(this.controller.getUnidadesDeMedida());
        this.txtUnidad.setPreferredSize(new Dimension(200, 20));
        this.add(txtUnidad, constraintsTextFields);


        //Label Costo por unidad de Medida
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 4;
        this.lblCostoU.setPreferredSize(new Dimension(170, 17));
        this.lblCostoU.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblCostoU, constraintsLabels);

        //TextField Costo por Unidad de Medida
        constraintsTextFields.gridx = 4;
        constraintsTextFields.gridy = 4;
        this.txtCostoU = new JTextField(0);
        this.txtCostoU.setPreferredSize(new Dimension(200, 20));
        this.add(txtCostoU, constraintsTextFields);

        //Error Costo por Unidad de Medida
        constraintsErrores.gridx = 3; //Columna 0
        constraintsErrores.gridy = 5; //Fila 2
        this.lblErrorCostoPorUnidad.setFont(new Font("Calibri", Font.PLAIN, 13));
        this.lblErrorCostoPorUnidad.setForeground(Color.RED);
        this.add(this.lblErrorCostoPorUnidad, constraintsErrores);
        this.lblErrorCostoPorUnidad.setVisible(false);

        //Label Densidad
        constraintsLabels.gridx = 1;
        constraintsLabels.gridy = 6;
        this.lblDensidad.setPreferredSize(new Dimension(200, 17));
        this.lblDensidad.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblDensidad, constraintsLabels);
        this.lblDensidad.setVisible(false);

        //Label Peso
        constraintsLabels.gridx = 1;
        constraintsLabels.gridy = 6;
        this.lblPeso.setPreferredSize(new Dimension(75, 17));
        this.lblPeso.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblPeso, constraintsLabels);
        this.lblPeso.setVisible(true);

        //TextField Densidad:
        constraintsTextFields.gridx = 2;
        constraintsTextFields.gridy = 6;
        this.txtDensidad = new JTextField(0);
        this.txtDensidad.setPreferredSize(new Dimension(200, 20));
        this.add(txtDensidad, constraintsTextFields);
        this.txtDensidad.setVisible(true);

        //Error Densidad
        constraintsErrores.gridx = 1; //Columna 0
        constraintsErrores.gridy = 7; //Fila 2
        constraintsErrores.anchor = GridBagConstraints.FIRST_LINE_END;
        this.lblErrorDensidad.setFont(new Font("Calibri", Font.PLAIN, 13));
        this.lblErrorDensidad.setForeground(Color.RED);
        this.add(this.lblErrorDensidad, constraintsErrores);
        this.lblErrorDensidad.setVisible(false);

        //Botón Agregar
        constraintsBotones.gridx = 4;
        constraintsBotones.gridy = 7;
        constraintsErrores.anchor = GridBagConstraints.FIRST_LINE_END;
        this.btnAgregar = new JButton("Aceptar");
        this.btnAgregar.setPreferredSize(new Dimension(95, 25));
        this.btnAgregar.addActionListener(e -> {

            limpiarErrores();
            try {
                System.out.println(this.fila);
                controller.modificar(this, this.fila);

            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println("Excepción:");
                System.out.println(e1.getMessage());
                this.mostrarError("Error al guardar", e1.getMessage());
                return;
            }
            this.limpiarFormulario();
            this.setVisible(false);
            this.panelInsumo.actualizarTabla();

        });
        this.add(btnAgregar, constraintsBotones);

        //Botón Cancelar
        constraintsBotones.gridx = 5;
        constraintsBotones.gridy = 7;
        constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_START;
        constraintsBotones.insets = new Insets(20, 15, 15, 10);
        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.setPreferredSize(new Dimension(95, 25));
        this.add(btnCancelar, constraintsBotones);
        this.btnCancelar.addActionListener( e ->
        {

            this.setVisible(false);
            this.panelInsumo.actualizarTabla();

        });

    }

    public void mostrarError(String titulo,String detalle) {
        JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
        JOptionPane.showMessageDialog(padre,
                detalle,titulo,
                JOptionPane.ERROR_MESSAGE);
    }

    private void limpiarFormulario() {

        this.txtCostoU.setText("");
        this.txtDensidad.setText("");
        this.txtDescripcion.setText("");
        this.txtTipo.setSelectedIndex(0);
        this.txtUnidad.setSelectedIndex(0);

    }

    private void limpiarErrores(){
        this.lblErrorDensidad.setVisible(false);
        this.lblErrorCostoPorUnidad.setVisible(false);
        this.lblErrorDescripcion.setVisible(false);
    }

    public void mostrarErrores(List<Integer> campos){

        for(Integer campo : campos){
            switch (campo){
                case 0: this.lblErrorDescripcion.setVisible(true); break;
                case 1: this.lblErrorCostoPorUnidad.setVisible(true); break;
                case 2: this.lblErrorDensidad.setVisible(true); break;
            }
        }
    }

    public void mostrarErrores(Boolean[] campos){

        int i=0;
        while(campos[i]) {
            i++;
        }
        switch (i){
            case 0: this.lblErrorDescripcion.setVisible(true); break;
            case 1: this.lblErrorCostoPorUnidad.setVisible(true); break;
            case 2: this.lblErrorDensidad.setVisible(true); break;
        }

    }


    //Getters y Setters


    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(String txtDescripcion) {
        this.txtDescripcion.setText(txtDescripcion);

    }

    public JComboBox<String> getTxtTipo() {
        return txtTipo;
    }

    public void setTxtTipo(Integer txtTipo) {
        this.txtTipo.setSelectedIndex(txtTipo);
    }

    public JComboBox<String> getTxtUnidad() {
        return txtUnidad;
    }

    public void setTxtUnidad(Integer txtUnidad) {
        this.txtUnidad.setSelectedIndex(txtUnidad);
    }

    public JTextField getTxtCostoU() {
        return txtCostoU;
    }

    public void setTxtCostoU(String txtCostoU) {
        this.txtCostoU.setText(txtCostoU);
    }

    public JTextField getTxtDensidad() {
        return txtDensidad;
    }

    public void setTxtDensidad(String txtDensidad) {
        this.txtDensidad.setText(txtDensidad);

        this.lblDensidad.setVisible(true);
        this.txtDensidad.setVisible(true);
    }

    public void setFila(Integer entero){
        this.fila = entero;
    }
/*        this.lblPeso.setVisible(false);
        this.txtPeso.setVisible(false);*/


/*
    public JTextField getTxtPeso() {
        return txtPeso;
    }
*/

/*    public void setTxtPeso(String txtPeso) {
        this.txtPeso.setText(txtPeso);

        this.txtDensidad.setVisible(false);
        this.lblDensidad.setVisible(false);
    }*/
}
