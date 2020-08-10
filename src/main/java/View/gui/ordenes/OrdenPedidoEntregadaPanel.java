package View.gui.ordenes;

import Service.ElementoNoEncontradoException;
import View.guiController.OrdenPedidoGuiController;

import javax.swing.*;
import java.awt.*;


public class OrdenPedidoEntregadaPanel extends JPanel {

    private JLabel lblTitulo = new JLabel("Gestión de Entrega de Pedidos:");
    private JLabel lblSubtitulo1 = new JLabel("Tabla de Pedidos a Entregar: ");

    private JTable tablaPedidos;
    private PedidosProcesadosTableModel modeloTablaPedidos;

    private JButton btnSeleccionar;
    private JButton btnCancelar;

    private OrdenPedidoGuiController controller;

    public OrdenPedidoEntregadaPanel() {
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
        constraintsTabla.gridheight=4;
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

        //Subtitulo: Lista de Pedidos
        constraintsSubtitulos.gridx = 0; //Columna 0
        constraintsSubtitulos.gridy = 1; //Fila 2
        lblSubtitulo1.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo1.setForeground(Color.BLACK);
        this.add(lblSubtitulo1,constraintsSubtitulos);


        //Tabla Ordenes Pedidos
        constraintsTabla.gridx = 1;
        constraintsTabla.gridy = 2;
        try {
            this.modeloTablaPedidos = new PedidosProcesadosTableModel(this.controller.pedidosProcesados());
        } catch (ElementoNoEncontradoException e) {
            mostrarError("Error al armar la tabla", e.getMessage());
            e.printStackTrace();
        }
        this.tablaPedidos = new JTable();
        tablaPedidos.setModel(modeloTablaPedidos);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        tablaPedidos.setFillsViewportHeight(true);
        tablaPedidos.setRowHeight(tablaPedidos.getRowHeight() + 20);
        tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(5);
        tablaPedidos.getColumnModel().getColumn(4).setPreferredWidth(200);
        scrollPane.setMinimumSize( scrollPane.getPreferredSize());
        this.add(scrollPane,constraintsTabla);

        //Botones
        //Botón Entregar
        constraintsBotones.gridx = 3;
        constraintsBotones.gridy = 2;
        constraintsBotones.anchor = GridBagConstraints.LINE_START;
        constraintsBotones.insets = new Insets(20, 15, 15, 10);
        this.btnSeleccionar = new JButton("Entregar");
        this.btnSeleccionar.setPreferredSize(new Dimension(110,25));
        this.btnSeleccionar.addActionListener( e -> {

            //Con esto puedo obtener la fila que está seleccionada
            int fila = this.tablaPedidos.getSelectedRow();

            if (fila < 0) {

                this.mostrarError("Error al Seleccionar", "Debe seleccionar una fila de Tabla de Pedidos a Entregar y luego oprimir el botón Entregar");
                return;

            }

            System.out.println("Fila seleccionada");
            System.out.println(fila);
            if(pedirConfirmacion ("Entregar Pedido", "¿Desea llevar a cabo la entrega del pedido").equals(0)){
                this.controller.entregarPedido(fila);
            }
            actualizarTabla();


        });
        this.add(btnSeleccionar,constraintsBotones);


        //Botón Cancelar
        constraintsBotones.gridx = 3;
        constraintsBotones.gridy = 4;
        constraintsBotones.anchor = GridBagConstraints.LINE_START;
        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.setPreferredSize(new Dimension(110,25));
        this.add(btnCancelar,constraintsBotones);
        this.btnCancelar.addActionListener(e->{

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

    public void actualizarTabla() {

        modeloTablaPedidos.fireTableDataChanged();

    }



}
