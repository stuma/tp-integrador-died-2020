package View.gui.ordenes;

import View.guiController.OrdenPedidoGuiController;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AgregarOrdenPanel extends JPanel{

    //Planta Destino
    //Fecha Máximo de Entrega
    //Items:
        //Insumo
        //Cantidad
        //Boton Agregar Item
    //Lista de Items con la columna precio
    //Botón Agregar Orden Pedido
    //Botón Cancelar

    private JLabel lblTitulo = new JLabel("Alta de Orden de Pedido");
    private JLabel lblSubtitulo1 = new JLabel("Agregar Orden: ");
    private JLabel lblSubtitulo2 = new JLabel("Agregar Item al Pedido: ");

    private JLabel lblPlanta = new JLabel("Planta Destino: ");
    private JComboBox<String> txtPlanta;

    private JLabel lblFecha = new JLabel("Fecha de Entrega: (DD/MM/YYYY) *");
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private JFormattedTextField txtFecha = new JFormattedTextField(df);

    private JLabel lblInsumo = new JLabel("Descripción de Insumo: ");
    private JComboBox<String> txtInsumo;
    private JLabel lblCantidad = new JLabel("Cantidad: *");
    private JTextField txtCantidad;
    private JButton btnAgregarItem;

    private JTable tablaItems;
    private ItemTableModel modeloTablaItem;

    private JButton btnAgregar;
    private JButton btnCancelar;

    private JLabel lblErrorFecha = new JLabel("Campo con Formato (DD/MM/YYYY) y Obligatorio.");
    private JLabel lblErrorCantidad = new JLabel("Campo Numérico y Obligatorio.");



    private OrdenPedidoGuiController controller;

    public AgregarOrdenPanel() {
        super();
        try {
            this.controller = OrdenPedidoGuiController.getOrdenPedidoController();
        } catch (Exception e) {
            mostrarError("Error al armar la pantalla", e.getMessage());
            e.printStackTrace();
        }
        this.armarPanel();
    }

    private void armarPanel() {

        //Agrega un Layout
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);

        GridBagConstraints constraintsTitulos = new GridBagConstraints();
        constraintsTitulos.fill = GridBagConstraints.VERTICAL;
        constraintsTitulos.anchor = GridBagConstraints.CENTER;
        constraintsTitulos.gridwidth=5;
        constraintsTitulos.insets = new Insets(0, 0, 15, 0);

        GridBagConstraints constraintsSubtitulos = new GridBagConstraints();
        constraintsSubtitulos.gridwidth=5;
        constraintsSubtitulos.weightx=1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
        constraintsSubtitulos.weighty=(double)(1/16);
        constraintsSubtitulos.insets = new Insets(20, 10, 10, 0);
        constraintsSubtitulos.fill = GridBagConstraints.HORIZONTAL;

        GridBagConstraints constraintsLabels= new GridBagConstraints();
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

        GridBagConstraints constraintsErrores = new GridBagConstraints();
        constraintsErrores.gridwidth=2;
        constraintsErrores.gridheight=1;
        constraintsErrores.weightx=1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
        constraintsErrores.weighty=(double)(1/16);
        constraintsErrores.insets = new Insets(0, 5, 10, 5);
        constraintsErrores.anchor = GridBagConstraints.FIRST_LINE_END;
        constraintsErrores.fill = GridBagConstraints.VERTICAL;

        GridBagConstraints constraintsBotones = new GridBagConstraints();
        constraintsBotones.fill = GridBagConstraints.NONE;
        constraintsBotones.anchor = GridBagConstraints.CENTER;
        constraintsBotones.insets = new Insets(5, 15, 5, 0);

        GridBagConstraints constraintsTabla = new GridBagConstraints();
        constraintsTabla.gridwidth=2;
        constraintsTabla.gridheight=1;
        constraintsTabla.weighty=1.0;
        constraintsTabla.weightx=1.0;
        constraintsTabla.anchor = GridBagConstraints.FIRST_LINE_START;
        constraintsTabla.fill = GridBagConstraints.HORIZONTAL;
        constraintsTabla.insets = new Insets(20, 10, 0, 10);


        //Titulo
        constraintsTitulos.gridx = 0;
        constraintsTitulos.gridy = 0;
        lblTitulo.setFont(new Font("System", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        this.add(lblTitulo,constraintsTitulos);

        //Subtitulo: Agregar Orden
        constraintsSubtitulos.gridx = 0; //Columna 0
        constraintsSubtitulos.gridy = 1; //Fila 2
        lblSubtitulo1.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo1.setForeground(Color.BLACK);
        this.add(lblSubtitulo1,constraintsSubtitulos);



        //Label Planta Destino
        constraintsLabels.gridx = 0;
        constraintsLabels.gridy = 2;
        constraintsLabels.insets = new Insets(5, 15, 5, 0);
        lblPlanta.setPreferredSize(new Dimension(100, 17));
        lblPlanta.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblPlanta,constraintsLabels);

        constraintsLabels.insets = new Insets(5, 5, 5, 0);

        //ComboBox Unidad de Medida
        constraintsTextfields.gridx = 1; //Va al lado del Label
        constraintsTextfields.gridy = 2;
        String[] plantaDestino = this.controller.getPlantas();
        this.txtPlanta = new JComboBox<String>(plantaDestino);
        this.txtPlanta.setPreferredSize(new Dimension(200, 20));
        this.add(txtPlanta,constraintsTextfields);

        //Label Fecha Máxima de Entrega
        constraintsLabels.gridx = 2;
        constraintsLabels.gridy = 2;
        this.lblFecha.setPreferredSize(new Dimension(200, 17));
        this.lblFecha.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblFecha,constraintsLabels);

        //TextField Fecha Máxima de Entrega
        constraintsTextfields.gridx = 3; //Va al lado del Label
        constraintsTextfields.gridy = 2;
        this.txtFecha = new JFormattedTextField();
        this.txtFecha.setPreferredSize(new Dimension(200, 20));
        this.add(txtFecha,constraintsTextfields);

        //Error Fecha Maxima
        constraintsErrores.gridx =2; //Columna 0
        constraintsErrores.gridy = 3; //Fila 2
        this.lblErrorFecha.setFont(new Font("Calibri", Font.PLAIN, 13));
        this.lblErrorFecha.setForeground(Color.RED);
        this.add(this.lblErrorFecha,constraintsErrores);
        this.lblErrorFecha.setVisible(false);


        //Subtitulo: Agregar Item a la Orden
        constraintsSubtitulos.gridx = 0; //Columna 0
        constraintsSubtitulos.gridy = 4; //Fila 2
        lblSubtitulo2.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo2.setForeground(Color.BLACK);
        this.add(lblSubtitulo2,constraintsSubtitulos);


        //Label Insumo
        constraintsLabels.gridx = 0;
        constraintsLabels.gridy = 5;
        constraintsLabels.insets = new Insets(5, 15, 5, 0);
        lblInsumo.setPreferredSize(new Dimension(80, 17));
        lblInsumo.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblInsumo,constraintsLabels);
        constraintsLabels.insets = new Insets(5, 5, 5, 0);

        //ComboBox Insumo
        constraintsTextfields.gridx = 1; //Va al lado del Label
        constraintsTextfields.gridy = 5;
        String[] insumo = this.controller.getInsumos();
        this.txtInsumo = new JComboBox<String>(insumo);
        this.txtInsumo.setPreferredSize(new Dimension(200, 20));
        this.add(txtInsumo,constraintsTextfields);


        //Label Cantidad
        constraintsLabels.gridx = 2;
        constraintsLabels.gridy = 5;
        this.lblCantidad.setPreferredSize(new Dimension(55, 17));
        this.lblCantidad.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblCantidad, constraintsLabels);

        //TextField Cantidad
        constraintsTextfields.gridx = 3; //Va al lado del Label
        constraintsTextfields.gridy = 5;
        this.txtCantidad = new JTextField(0);
        this.txtCantidad.setMinimumSize(new Dimension(200,20));
        this.add(txtCantidad,constraintsTextfields);

        //Error Fecha Maxima
        constraintsErrores.gridx =2; //Columna 0
        constraintsErrores.gridy = 6; //Fila 2
        this.lblErrorCantidad.setFont(new Font("Calibri", Font.PLAIN, 13));
        this.lblErrorCantidad.setForeground(Color.RED);
        this.add(this.lblErrorCantidad,constraintsErrores);
        this.lblErrorCantidad.setVisible(false);


        //Botón Agregar Item
        constraintsBotones.gridx = 4;
        constraintsBotones.gridy = 5;
        constraintsBotones.insets = new Insets(13, 15, 15, 0);
        this.btnAgregarItem = new JButton("Agregar Item");
        this.btnAgregarItem.setPreferredSize(new Dimension(110,25));
        this.btnAgregarItem.addActionListener( e ->
                {
                    limpiarErrores();
                    try {
                        controller.agregarItem(this);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        this.mostrarError("Error al guardar", e1.getMessage());
                    }
                    this.limpiarFormularioItems();
                    actualizarTabla();

                }
        );
        this.add(btnAgregarItem,constraintsBotones);

        //Tabla Items
        constraintsTabla.gridx = 1;
        constraintsTabla.gridy = 7;
        this.modeloTablaItem = new ItemTableModel(this.controller.getListaItems());
        this.tablaItems = new JTable();
        tablaItems.setModel(modeloTablaItem);
        JScrollPane scrollPane = new JScrollPane(tablaItems);
        tablaItems.setFillsViewportHeight(true);
        scrollPane.setMinimumSize( scrollPane.getPreferredSize());
        this.add(scrollPane,constraintsTabla);

        //Botones
        //Botón Agregar
        constraintsBotones.gridx = 2;
        constraintsBotones.gridy = 8;
        constraintsBotones.anchor = GridBagConstraints.LINE_END;
        constraintsBotones.insets = new Insets(20, 15, 15, 0);
        this.btnAgregar = new JButton("Agregar");
        this.btnAgregar.setPreferredSize(new Dimension(110,25));
        this.btnAgregar.addActionListener( e ->
                {
                    limpiarErrores();

                    try {
                        controller.guardar(this);
                    } catch (Exception e1) {
                        this.mostrarError("Error al guardar", e1.getMessage());
                        return;
                    }

                    this.limpiarFormulario();
                    actualizarTabla();

                }
        );
        this.add(btnAgregar,constraintsBotones);

        //Botón Cancelar
        constraintsBotones.gridx = 3;
        constraintsBotones.gridy = 8;
        constraintsBotones.anchor = GridBagConstraints.LINE_START;
        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.setPreferredSize(new Dimension(110,25));
        this.add(btnCancelar,constraintsBotones);
        this.btnCancelar.addActionListener(e->{
/*            limpiarErrores();
            limpiarFormulario();*/
            this.removeAll();
            revalidate();
            repaint();
        });

    }


    public void mostrarError(String titulo,String detalle) {
        JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
        JOptionPane.showMessageDialog(padre,
                detalle,titulo,
                JOptionPane.ERROR_MESSAGE);
    }

    private Integer pedirConfirmacion(String titulo, String mensaje){
        JFrame padre = (JFrame)SwingUtilities.getWindowAncestor(this);
        return JOptionPane.showConfirmDialog(padre,
                titulo, mensaje, JOptionPane.OK_CANCEL_OPTION);
    }

    private void limpiarFormulario() {

        this.txtCantidad.setText("");
        this.txtInsumo.setSelectedIndex(0);
        this.txtPlanta.setSelectedIndex(0);
        this.txtFecha.setText("");

        this.controller.vaciarTablaItems();

        actualizarTabla();

    }

    private void limpiarFormularioItems(){

        this.txtCantidad.setText("");
        this.txtInsumo.setSelectedIndex(0);
    }

    private void limpiarErrores(){
        this.lblErrorCantidad.setVisible(false);
        this.lblErrorFecha.setVisible(false);
    }

    public void mostrarErrores(List<Integer> campos){

        for(Integer campo : campos){
            switch (campo){
                case 0: this.lblErrorFecha.setVisible(true); break;
                case 1: this.lblErrorCantidad.setVisible(true); break;

            }
        }
    }

    public void mostrarErrores(Boolean[] campos){

        int i=0;
        while(i<campos.length) {
            if(!campos[i]) {
                switch (i) {
                    case 0:
                        this.lblErrorFecha.setVisible(true);
                        break;
                    case 1:
                        this.lblErrorCantidad.setVisible(true);
                        break;
                }

            }
            i++;
        }
    }

    public void actualizarTabla() {

        modeloTablaItem.fireTableDataChanged();

    }


    //Getters and Setters
    public JComboBox<String> getTxtPlanta() {
        return txtPlanta;
    }

    public JFormattedTextField getTxtFecha() {
        return txtFecha;
    }

    public JComboBox<String> getTxtInsumo() {
        return txtInsumo;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public DateTimeFormatter getDf() {
        return df;
    }
}
