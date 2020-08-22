package View.gui.ordenes;

import Service.ElementoNoEncontradoException;
import View.guiController.OrdenPedidoGuiController;

import javax.swing.*;
import java.awt.*;

public class ProcesarOrdenPanel extends JPanel {

    //Muestro tablas de todas las ordenes de pedidos: Id, Planta destino, fecha maxima entrega,
    //Selecciona un pedido y se muestra los detalles de dicho pedido -> Mismo que Alta de Orden, solo que sin los campos para agregar item
    //Se muestra una tabla con plantas, rutas y caminos más cortos
    //Cuando se selecciona ruta, el pedido cambia su estado. Termina de procesar Orden.

    //Pantalla 1:
        private JLabel lblTitulo = new JLabel("Gestión de Orden de Pedido: ");
        private JLabel lblSubtitulo1 = new JLabel("Tabla de Orden de Pedido");
        private JLabel lblSubtitulo2 = new JLabel("Detalle de Orden de Pedido:");

        private JLabel lblPlantaDestino = new JLabel("Planta Destino: ");
        private JTextField txtPlantaDestino;
        private JLabel lblFecha = new JLabel("Fecha de Entrega: (DD/MM/YYYY)");
        private JTextField txtFecha;


        private JTable tablaItems;
        private ItemTableModel modeloTablaItems;

        private JTable tablaPedidos;
        private PedidosCreadosTableModel modeloTablaPedidos;

        private JButton btnSeleccionarOrden;
        private JButton btnCancelar;
        private JButton btnAgregar;

    //Pantalla 2:

        private JLabel lblSubtitulo3 = new JLabel("Seleccione Planta Origen para la Orden de Pedido:");

        private JLabel lblRutaElegidaHs = new JLabel("Ruta Asignada (Hs): ");
        private JTextArea txtRutaElegidaHs;
        private JLabel lblRutaElegidaKm = new JLabel("Ruta Asignada (Km): ");
        private JTextArea txtRutaElegidaKm;
        private JLabel lblPlantaOrigen= new JLabel("Planta Origen: ");
        private JTextField txtPlantaOrigen;

        private JTable tablaPlantasDisponibles;
        private PlantaDisponibleTableModel modeloTablaPlantasDisp;

        private JButton btnSeleccionarPlanta;
        private JButton btnAgregarPlanta;
        private JButton btnCancelarPed;

    //Controller
        private OrdenPedidoGuiController controller;

    //GridBagLayout
        private GridBagConstraints constraintsTitulos = new GridBagConstraints();
        private GridBagConstraints constraintsSubtitulos = new GridBagConstraints();
        private GridBagConstraints constraintsLabels = new GridBagConstraints();
        private GridBagConstraints constraintsTexfields = new GridBagConstraints();
        private GridBagConstraints constraintsTablas = new GridBagConstraints();
        private GridBagConstraints constraintsBotones = new GridBagConstraints();

    //Puedo usar el remove all y volver a armar el panel.
    public ProcesarOrdenPanel(){
        super();
        try {
            this.controller = OrdenPedidoGuiController.getOrdenPedidoController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            armarPantalla1();
        } catch (ElementoNoEncontradoException e) {
            mostrarError("Error al armar la pantalla", e.getMessage());
            e.printStackTrace();
        }

    }

    //Pantalla principal.
    private void armarPantalla1() throws ElementoNoEncontradoException {

        //Agrega un Layout
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);

        constraintsTitulos.fill = GridBagConstraints.VERTICAL;
        constraintsTitulos.anchor = GridBagConstraints.CENTER;
        constraintsTitulos.gridwidth=8;
        constraintsTitulos.insets = new Insets(5, 0, 15, 0);

        constraintsSubtitulos.gridwidth=3;
        constraintsSubtitulos.weightx=1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
        constraintsSubtitulos.weighty=(double)(1/16);
        constraintsSubtitulos.insets = new Insets(20, 20, 10, 0);
        constraintsSubtitulos.fill = GridBagConstraints.HORIZONTAL;

        constraintsTablas.gridwidth=3;
        constraintsTablas.gridheight=6;
        constraintsTablas.weighty=1.0;
        constraintsTablas.weightx=1.0;
        constraintsTablas.anchor = GridBagConstraints.FIRST_LINE_START;
        constraintsTablas.fill = GridBagConstraints.BOTH;
        constraintsTablas.insets = new Insets(5, 10, 0, 5);

        constraintsBotones.fill = GridBagConstraints.NONE;
        constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_END;
        constraintsBotones.insets = new Insets(10, 5, 15, 10);

        constraintsLabels.fill = GridBagConstraints.HORIZONTAL;
        constraintsLabels.anchor = GridBagConstraints.LINE_START;
        constraintsLabels.insets = new Insets(5, 25, 5, 10);

        constraintsTexfields.fill = GridBagConstraints.HORIZONTAL;
        constraintsTexfields.anchor = GridBagConstraints.CENTER;
        constraintsTexfields.insets = new Insets(5, 25, 5, 10);

        //Titulo
        constraintsTitulos.gridx = 0;
        constraintsTitulos.gridy = 0;
        lblTitulo.setFont(new Font("System", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        this.add(lblTitulo,constraintsTitulos);

        //Subtitulo: Seleccionar una orden
        constraintsSubtitulos.gridx = 0; //Columna 0
        constraintsSubtitulos.gridy = 1; //Fila 2
        lblSubtitulo1.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo1.setForeground(Color.BLACK);
        this.add(lblSubtitulo1,constraintsSubtitulos);

        //Tabla Ordenes de Pedido
        constraintsTablas.gridx = 0;
        constraintsTablas.gridy = 2;
        this.modeloTablaPedidos = new PedidosCreadosTableModel(this.controller.getPedidosCreados());
        this.tablaPedidos = new JTable();
        tablaPedidos.setModel(modeloTablaPedidos);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        tablaPedidos.setFillsViewportHeight(true);
        scrollPane.setMinimumSize( scrollPane.getPreferredSize());
        this.add(scrollPane,constraintsTablas);

        //Botones
        //Botón Seleccionar
        constraintsBotones.gridx = 3;
        constraintsBotones.gridy = 2;
        constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_START;
        this.btnSeleccionarOrden = new JButton("Seleccionar");
        this.btnSeleccionarOrden.setPreferredSize(new Dimension(105,25));
        this.btnSeleccionarOrden.addActionListener( e -> {

                    //Con esto puedo obtener la fila que está seleccionada
                    int fila = this.tablaPedidos.getSelectedRow();

                    if (fila < 0) {

                        this.mostrarError("Error al Seleccionar", "Debe seleccionar una fila de Tabla de Orden de Pedido y luego oprimir el botón Seleccionar");
                        return;

                    }

                    System.out.println("Fila seleccionada");
                    System.out.println(fila);

                    this.controller.mostrarDetallePedido(this, fila);
                    actualizarTablaItem();

        });
        this.add(btnSeleccionarOrden,constraintsBotones);

        //Botón Cancelar
        constraintsBotones.gridx =3;
        constraintsBotones.gridy = 2;
        constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_END;
        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.setMinimumSize(new Dimension(105,25));
        this.add(btnCancelar,constraintsBotones);
        this.btnCancelar.addActionListener(e->{

/*            this.removeAll();
            this.controller.vaciarListaItems();
            this.armarPantalla1();
            this.revalidate();
            this.repaint();
            actualizarTablaItem();*/
            this.removeAll();
            revalidate();
            repaint();

        });


        //Subtitulo: Detalle de Orden de Pedido
        constraintsSubtitulos.gridx = 4; //Columna 0
        constraintsSubtitulos.gridy = 3; //Fila 2
        constraintsSubtitulos.gridwidth=4;
        lblSubtitulo2.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo2.setForeground(Color.BLACK);
        this.add(lblSubtitulo2,constraintsSubtitulos);
        lblSubtitulo2.setVisible(true);


        //Label Planta Destino
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 4;
        this.lblPlantaDestino.setPreferredSize(new Dimension(100, 17));
        this.lblPlantaDestino.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblPlantaDestino,constraintsLabels);

        //TextField Planta Destino
        constraintsTexfields.gridx = 4;
        constraintsTexfields.gridy = 4;
        this.txtPlantaDestino = new JFormattedTextField();
        this.txtPlantaDestino.setPreferredSize(new Dimension(200, 20));
        this.txtPlantaDestino.setFont(new Font("System", Font.BOLD, 13));
        this.add(txtPlantaDestino,constraintsTexfields);
        this.txtPlantaDestino.setEditable(false);

        //Label Fecha Máxima de Entrega
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 5;
        this.lblFecha.setPreferredSize(new Dimension(205, 17));
        this.lblFecha.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblFecha,constraintsLabels);

        //TextField Fecha Máxima de Entrega
        constraintsLabels.gridx = 4;
        constraintsLabels.gridy = 5;
        this.txtFecha = new JFormattedTextField();
        this.txtFecha.setPreferredSize(new Dimension(200, 20));
        this.txtFecha.setFont(new Font("System", Font.BOLD, 13));
        this.txtFecha.setEditable(false);
        this.add(txtFecha,constraintsLabels);

        //Tabla Items
        constraintsTablas.gridx = 3;
        constraintsTablas.gridy = 6;
        constraintsTablas.gridwidth=2;
        constraintsTablas.gridheight=1;
        constraintsTablas.anchor = GridBagConstraints.CENTER;
        this.modeloTablaItems = new ItemTableModel(this.controller.getItems());
        this.tablaItems = new JTable();
        tablaItems.setModel(modeloTablaItems);
        JScrollPane scrollPane2 = new JScrollPane(tablaItems);
        tablaItems.setFillsViewportHeight(true);
        scrollPane2.setMinimumSize( scrollPane2.getPreferredSize());
        this.add(scrollPane2,constraintsTablas);



        //Botones
        //Botón Agregar Ruta y Origen
        constraintsBotones.gridx = 5;
        constraintsBotones.gridy = 5;
        constraintsBotones.anchor = GridBagConstraints.LINE_END;
        this.btnAgregar = new JButton("Procesar");
        this.btnAgregar.setPreferredSize(new Dimension(105,25));
        this.btnAgregar.addActionListener( e -> {

            //Con esto puedo obtener la fila que está seleccionada
            int fila = this.tablaPedidos.getSelectedRow();

            if (fila < 0) {

                this.mostrarError("Error al Seleccionar", "Debe seleccionar una fila de Tabla de Orden de Pedido y luego oprimir el botón Procesar");
                return;

            }

            System.out.println("Fila seleccionada");
            System.out.println(fila);
            try{

                this.controller.validarPantalla1(this, fila);

            }catch (Exception ex){
                ex.printStackTrace();
                this.mostrarError("Error al Procesar", ex.getMessage());
                return;
            }

            this.removeAll();
            this.armarPantalla2();
            this.revalidate();
            this.repaint();

        });
        this.add(btnAgregar,constraintsBotones);

    }


    //Pantalla que se invoca desde Pantalla1
    private void armarPantalla2(){

        //Agrega un Layout
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);

        constraintsTitulos.fill = GridBagConstraints.VERTICAL;
        constraintsTitulos.anchor = GridBagConstraints.CENTER;
        constraintsTitulos.gridwidth=8;
        constraintsTitulos.insets = new Insets(5, 0, 15, 0);

        constraintsSubtitulos.gridwidth=3;
        constraintsSubtitulos.weightx=1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
        constraintsSubtitulos.weighty=(double)(1/16);
        constraintsSubtitulos.insets = new Insets(20, 20, 10, 0);
        constraintsSubtitulos.fill = GridBagConstraints.HORIZONTAL;

        constraintsTablas.gridwidth=3;
        constraintsTablas.gridheight=9;
        constraintsTablas.weighty=1.0;
        constraintsTablas.weightx=1.0;
        constraintsTablas.anchor = GridBagConstraints.FIRST_LINE_START;
        constraintsTablas.fill = GridBagConstraints.BOTH;
        constraintsTablas.insets = new Insets(5, 10, 0, 5);

        constraintsBotones.fill = GridBagConstraints.NONE;
        constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_END;
        constraintsBotones.insets = new Insets(10, 0, 15, 10);

        constraintsLabels.fill = GridBagConstraints.HORIZONTAL;
        constraintsLabels.anchor = GridBagConstraints.LINE_END;
        constraintsLabels.insets = new Insets(5, 25, 5, 10);

        constraintsTexfields.fill = GridBagConstraints.HORIZONTAL;
        constraintsTexfields.anchor = GridBagConstraints.LINE_START;
        constraintsTexfields.insets = new Insets(5, 25, 5, 10);


        //Titulo
        constraintsTitulos.gridx = 0;
        constraintsTitulos.gridy = 0;
        lblTitulo.setFont(new Font("System", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        this.add(lblTitulo,constraintsTitulos);


        //Subtitulo: Seleccionar Planta Origen
        constraintsSubtitulos.gridx = 0; //Columna 0
        constraintsSubtitulos.gridy = 1; //Fila 2
        lblSubtitulo3.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo3.setForeground(Color.BLACK);
        this.add(lblSubtitulo3,constraintsSubtitulos);

        //Tabla Plantas Disponibles
        constraintsTablas.gridx = 0;
        constraintsTablas.gridy = 2;
        try {
            this.modeloTablaPlantasDisp = new PlantaDisponibleTableModel(this.controller.getListaPlantas(), this.controller.getCaminosHs(), this.controller.getCaminosKm());
        } catch (Exception e) {
            mostrarError("Error al armar la tabla", e.getMessage());
            e.printStackTrace();
        }
        this.tablaPlantasDisponibles = new JTable();
        tablaPlantasDisponibles.setModel(modeloTablaPlantasDisp);
        JScrollPane scrollPane2 = new JScrollPane(tablaPlantasDisponibles);
        tablaPlantasDisponibles.setFillsViewportHeight(true);
        scrollPane2.setMinimumSize( scrollPane2.getPreferredSize());
        this.add(scrollPane2,constraintsTablas);

        //Botones
        //Botón Seleccionar
        constraintsBotones.gridx = 3;
        constraintsBotones.gridy = 2;
        constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_START;
        this.btnSeleccionarPlanta = new JButton("Seleccionar");
        this.btnSeleccionarPlanta.setPreferredSize(new Dimension(105,25));
        this.btnSeleccionarPlanta.addActionListener( e -> {

            //Con esto puedo obtener la fila que está seleccionada
            int fila = this.tablaPlantasDisponibles.getSelectedRow();

            if (fila < 0) {

                this.mostrarError("Error al Seleccionar", "Debe seleccionar una fila de la tabla y luego oprimir el botón modificar");
                return;

            }

            System.out.println("Fila seleccionada");
            System.out.println(fila);

            this.controller.mostrarDetallePlantas(this, fila);
            this.modeloTablaPlantasDisp.fireTableDataChanged();
        });

        this.add(btnSeleccionarPlanta,constraintsBotones);


        //Subtitulo: Detalle de Orden de Pedido
        constraintsSubtitulos.gridx = 4; //Columna 0
        constraintsSubtitulos.gridy = 3; //Fila 2
        constraintsSubtitulos.gridwidth=4;
        lblSubtitulo2.setFont(new Font("Calibri", Font.BOLD, 15));
        lblSubtitulo2.setForeground(Color.BLACK);
        this.add(lblSubtitulo2,constraintsSubtitulos);
        lblSubtitulo2.setVisible(true);


        //Label Planta Destino
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 4;
        this.lblPlantaDestino.setPreferredSize(new Dimension(100, 17));
        this.lblPlantaDestino.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblPlantaDestino,constraintsLabels);

        //TextField Planta Destino
        constraintsTexfields.gridx = 4;
        constraintsTexfields.gridy = 4;
        this.txtPlantaDestino.setPreferredSize(new Dimension(200, 20));
        this.txtPlantaDestino.setFont(new Font("System", Font.BOLD, 13));
        this.add(txtPlantaDestino,constraintsTexfields);
        this.txtPlantaDestino.setEditable(false);

        //Label Fecha Máxima de Entrega
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 5;
        this.lblFecha.setPreferredSize(new Dimension(205, 17));
        this.lblFecha.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblFecha,constraintsLabels);

        //TextField Fecha Máxima de Entrega
        constraintsLabels.gridx = 4;
        constraintsLabels.gridy = 5;
        this.txtFecha.setPreferredSize(new Dimension(200, 20));
        this.txtFecha.setFont(new Font("System", Font.BOLD, 13));
        this.txtFecha.setEditable(false);
        this.add(txtFecha,constraintsLabels);

        //Label Planta Origen
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 6;
        this.lblPlantaOrigen.setPreferredSize(new Dimension(205, 17));
        this.lblPlantaOrigen.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblPlantaOrigen,constraintsLabels);

        //TextField Fecha Máxima de Entrega
        constraintsLabels.gridx = 4;
        constraintsLabels.gridy = 6;
        this.txtPlantaOrigen = new JTextField(0);
        this.txtPlantaOrigen.setPreferredSize(new Dimension(200, 20));
        this.txtPlantaOrigen.setFont(new Font("System", Font.BOLD, 13));
        this.txtPlantaOrigen.setEditable(false);
        this.add(txtPlantaOrigen,constraintsLabels);

        //Label Ruta hs
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 7;
        this.lblRutaElegidaHs.setPreferredSize(new Dimension(205, 17));
        this.lblRutaElegidaHs.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblRutaElegidaHs,constraintsLabels);

        //TextField Ruta elegida
        constraintsLabels.gridx = 4;
        constraintsLabels.gridy = 7;
        this.txtRutaElegidaHs = new JTextArea();
        this.txtRutaElegidaHs.setPreferredSize(new Dimension(300, 50));
        this.txtRutaElegidaHs.setFont(new Font("System", Font.BOLD, 13));
        this.txtRutaElegidaHs.setEditable(false);
        this.add(txtRutaElegidaHs,constraintsLabels);

        //Label Ruta km
        constraintsLabels.gridx = 3;
        constraintsLabels.gridy = 8;
        this.lblRutaElegidaKm.setPreferredSize(new Dimension(205, 17));
        this.lblRutaElegidaKm.setFont(new Font("System", Font.PLAIN, 13));
        this.add(lblRutaElegidaKm,constraintsLabels);

        //TextField Ruta elegida
        constraintsLabels.gridx = 4;
        constraintsLabels.gridy = 8;
        this.txtRutaElegidaKm = new JTextArea();
        this.txtRutaElegidaKm.setPreferredSize(new Dimension(300, 50));
        this.txtRutaElegidaKm.setFont(new Font("System", Font.BOLD, 13));
        this.txtRutaElegidaKm.setEditable(false);
        this.add(txtRutaElegidaKm,constraintsLabels);


        //Botones
        //Botón Agregar Ruta y Origen
        constraintsBotones.gridx = 4;
        constraintsBotones.gridy = 9;
        constraintsBotones.anchor = GridBagConstraints.LINE_START;
        this.btnAgregarPlanta = new JButton("Aceptar");
        this.btnAgregarPlanta.setPreferredSize(new Dimension(105,25));
        this.btnAgregarPlanta.addActionListener( e -> {

            try {
                controller.asignarRuta();
            } catch (Exception e1) {
                e1.printStackTrace();
                this.mostrarError("Error al guardar", e1.getMessage());
                return;
            }

            //Vuelvo a la pantalla de origen
            mostrarConfirmacion("La orden ha sido procesada correctamente.");

            this.removeAll();
            try {
                this.armarPantalla1();
            } catch (ElementoNoEncontradoException ex) {
                mostrarError("Error al armar la pantalla", ex.getMessage());
                ex.printStackTrace();
            }
            this.revalidate();
            this.repaint();

        });
        this.add(btnAgregarPlanta,constraintsBotones);


        //Botón Cancelar
        constraintsBotones.gridx = 4;
        constraintsBotones.gridy = 9;
        constraintsBotones.anchor = GridBagConstraints.CENTER;
        this.btnCancelarPed = new JButton("Cancelar");
        this.btnCancelarPed.setPreferredSize(new Dimension(105,25));
        this.add(btnCancelarPed,constraintsBotones);
        this.btnCancelarPed.addActionListener(e->{
            this.removeAll();
            try {
                this.armarPantalla1();
            } catch (ElementoNoEncontradoException ex) {
                mostrarError("Error al armar la pantalla", ex.getMessage());
                ex.printStackTrace();
            }
            this.revalidate();
            this.repaint();

        });

    }


    public void mostrarError(String titulo,String detalle) {
        JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
        JOptionPane.showMessageDialog(padre,
                detalle,titulo,
                JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarConfirmacion(String detalle) {
        JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
        JOptionPane.showMessageDialog(padre,
                detalle);
    }

    public void actualizarTablaItem(){

        this.modeloTablaItems.fireTableDataChanged();
    }

    public void actualizarTabla(){

        this.modeloTablaPedidos.fireTableDataChanged();

    }
    //Getters and setters
    public JTextField getTxtPlantaDestino() {
        return txtPlantaDestino;
    }

    public void setTxtPlantaDestino(JTextField txtPlantaDestino) {
        this.txtPlantaDestino = txtPlantaDestino;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(JTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    public JTextArea getTxtRutaElegidaHs() {
        return txtRutaElegidaHs;
    }

    public void setTxtRutaElegidaHs(JTextArea txtRutaElegidaHs) {
        this.txtRutaElegidaHs = txtRutaElegidaHs;
    }

    public JTextArea getTxtRutaElegidaKm() {
        return txtRutaElegidaKm;
    }

    public void setTxtRutaElegidaKm(JTextArea txtRutaElegidaKm) {
        this.txtRutaElegidaKm = txtRutaElegidaKm;
    }

    public JTextField getTxtPlantaOrigen() {
        return txtPlantaOrigen;
    }

    public void setTxtPlantaOrigen(JTextField txtPlantaOrigen) {
        this.txtPlantaOrigen = txtPlantaOrigen;
    }
}
