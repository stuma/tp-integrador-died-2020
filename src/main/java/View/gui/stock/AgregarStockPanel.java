package View.gui.stock;

import Service.ElementoNoEncontradoException;
import View.guiController.StockGuiController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.*;


public class AgregarStockPanel extends JPanel{
	
	//Titulos
	private JLabel lblTitulo = new JLabel("Administración de Stock de Insumos:");
	private JLabel lblSubtitulo1 = new JLabel("Agregar Stock de Insumo:");
	private JLabel lblSubtitulo2 = new JLabel("Tabla de Stock de Insumos:");
	private JLabel lblSubtitulo3 = new JLabel("([Ctrl + Click Izq] para seleccionar multiples filas)");
	
	//Campos
	private JLabel lblPlanta = new JLabel("Nombre de Planta:");
	private JComboBox<String> txtPlanta;
	private JLabel lblInsumo = new JLabel("Descripción de Insumo: *");
	private JComboBox<String> txtInsumo;
	private JLabel lblCantidad = new JLabel("Cantidad de Insumos: *");
	private JTextField txtCantidad;
	private JLabel lblPuntoPedido = new JLabel("Punto de Pedido: *");
	private JTextField txtPuntoPedido;

	
	//Botones
	private JButton btnAgregar;
	private JButton btnCancelar;

	//Errores
	private JLabel lblErrorCantidad = new JLabel("Campo Numérico y Obligatorio.");
	private JLabel lblErrorPuntoPedido = new JLabel("Campo Numérico y Obligatorio.");



	//Tabla Insumos
	private JTable tablaStock;
	private StockTableModel modeloTablaStock;
	private JButton btnEliminar;
	private JButton btnModificar;
	
	//Otros
	private StockGuiController controller;
	private ModificacionStockPopUp modificar;


	public AgregarStockPanel() {
		super();
		//Primero debo inicializar el controller ya que armarPanel requiere de controller
		this.controller = StockGuiController.getStockController();
		this.modificar = new ModificacionStockPopUp(this);
		this.armarPanel();
	}
	
	private void armarPanel() {
		
		//Agrega un Layout
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);

		GridBagConstraints constraintsTitulos = new GridBagConstraints();
		constraintsTitulos.fill = GridBagConstraints.VERTICAL;
		constraintsTitulos.anchor = GridBagConstraints.CENTER;
		constraintsTitulos.gridwidth=8;
		constraintsTitulos.insets = new Insets(0, 0, 15, 0);

		GridBagConstraints constraintsSubtitulos = new GridBagConstraints();
		constraintsSubtitulos.gridwidth=7;
		constraintsSubtitulos.weightx=1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
		constraintsSubtitulos.insets = new Insets(0, 10, 20, 0);
		constraintsSubtitulos.fill = GridBagConstraints.HORIZONTAL;

		GridBagConstraints constraintsLabels = new GridBagConstraints();
		constraintsLabels.gridwidth = 1;
		constraintsLabels.gridheight=1;
		constraintsLabels.weightx=0.12; //
		constraintsLabels.weighty=0;
		constraintsLabels.ipadx = 5;
		constraintsLabels.ipady = 5;
		constraintsLabels.insets = new Insets(5, 5, 5, 5); //-> Este determina la separación entre objetos.
		constraintsLabels.fill = GridBagConstraints.NONE;
		constraintsLabels.anchor = GridBagConstraints.LINE_END;

		GridBagConstraints constraintsTextFields = new GridBagConstraints();
		constraintsTextFields.gridwidth = 1;
		constraintsTextFields.gridheight=1;
		constraintsTextFields.weightx=1.0; //
		constraintsTextFields.weighty=0;
		constraintsTextFields.ipadx = 5;
		constraintsTextFields.ipady = 5;
		constraintsTextFields.insets = new Insets(5, 5, 5, 5); //-> Este determina la separación entre objetos.
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

		GridBagConstraints constraintsBotones = new GridBagConstraints();
		constraintsBotones.fill = GridBagConstraints.NONE;
		constraintsBotones.anchor = GridBagConstraints.LINE_END;
		constraintsBotones.insets = new Insets(20, 15, 15, 0);
		constraintsBotones.gridwidth = 1;
		constraintsBotones.gridheight = 1;

		GridBagConstraints constraintsTabla = new GridBagConstraints();
		constraintsTabla.gridwidth=5;
		constraintsTabla.gridheight=2;
		constraintsTabla.fill = GridBagConstraints.BOTH;
		constraintsTabla.anchor = GridBagConstraints.FIRST_LINE_START;
		constraintsTabla.insets = new Insets(0, 10, 0, 0);

		//Titulo
		constraintsTitulos.gridx = 0;
		constraintsTitulos.gridy = 0;
		lblTitulo.setFont(new Font("System", Font.BOLD, 20));
		lblTitulo.setForeground(Color.BLACK);
		this.add(lblTitulo,constraintsTitulos);

		
		//Subtitulo 1: "Agregar Stock de Insumo"
		constraintsSubtitulos.gridx = 0; //Columna 0
		constraintsSubtitulos.gridy = 1; //Fila 2
		lblSubtitulo1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSubtitulo1.setForeground(Color.BLACK);
		this.add(lblSubtitulo1,constraintsSubtitulos);
		
		
		//Campos:
		//Label Nombre de Planta
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 2;
		lblPlanta.setPreferredSize(new Dimension(110, 17));
		lblPlanta.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblPlanta,constraintsLabels);

		//TextField Nombre de Planta
		constraintsTextFields.gridx = 1; //Va al lado del Label
		constraintsTextFields.gridy = 2;
		this.txtPlanta = new JComboBox<String>(this.controller.getPlantas());
		this.txtPlanta.setPreferredSize(new Dimension(200, 20));
		this.add(txtPlanta,constraintsTextFields);


		//Label Descripción de Insumo
		constraintsLabels.gridx = 2;
		constraintsLabels.gridy = 2;
		this.lblInsumo.setPreferredSize(new Dimension(140, 17));
		this.lblInsumo.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblInsumo,constraintsLabels);
		
		//TextField Descripción de Insumo
		constraintsTextFields.gridx = 3; //Va al lado del Label
		constraintsTextFields.gridy = 2;
		//constraintsTextFields.insets= new Insets(5, 5, 5, 10);
		try {
			this.txtInsumo = new JComboBox<String>(this.controller.getInsumos());
		} catch (ElementoNoEncontradoException e) {
			e.printStackTrace();
		}
		this.txtInsumo.setPreferredSize(new Dimension(200, 20));
		this.add(txtInsumo,constraintsTextFields);

		//constraintsTextFields.insets= new Insets(5, 5, 15, 5);



		//Label Cantidad de Insumos
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 3;
		this.lblCantidad.setPreferredSize(new Dimension(130, 17));
		this.lblCantidad.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblCantidad,constraintsLabels);
		
		//TextField Cantidad de Insumos
		constraintsTextFields.gridx = 1; //Va al lado del Label
		constraintsTextFields.gridy = 3;
		this.txtCantidad = new JTextField(0);
		this.txtCantidad.setPreferredSize(new Dimension(200, 20));
		this.add(txtCantidad,constraintsTextFields);

		//Error Cantidad de Insumos
		constraintsErrores.gridx = 0; //Columna 0
		constraintsErrores.gridy = 4; //Fila 2
		this.lblErrorCantidad.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorCantidad.setForeground(Color.RED);
		this.add(this.lblErrorCantidad, constraintsErrores);
		this.lblErrorCantidad.setVisible(false);


		//Label Punto de Pedido
		constraintsLabels.gridx = 2;
		constraintsLabels.gridy = 3;
		this.lblPuntoPedido.setPreferredSize(new Dimension(110, 17));
		this.lblPuntoPedido.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblPuntoPedido,constraintsLabels);
		
		//TextField Punto de Pedido
		constraintsTextFields.gridx = 3;
		constraintsTextFields.gridy = 3;
		//constraintsTextFields.insets= new Insets(5, 5, 15, 10);
		this.txtPuntoPedido = new JTextField(0);	
		this.txtPuntoPedido.setPreferredSize(new Dimension(200, 20));
		this.add(txtPuntoPedido,constraintsTextFields);

		constraintsTextFields.insets= new Insets(5, 5, 15, 5);

		//Error Error punto de pedido
		constraintsErrores.gridx = 2; //Columna 0
		constraintsErrores.gridy = 4; //Fila 2
		this.lblErrorPuntoPedido.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorPuntoPedido.setForeground(Color.RED);
		this.add(this.lblErrorPuntoPedido, constraintsErrores);
		this.lblErrorPuntoPedido.setVisible(false);


		//Botones
		//Botón Agregar
		constraintsBotones.gridx = 2;
		constraintsBotones.gridy = 5;
		constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_END;
		this.btnAgregar = new JButton("Agregar");
		this.btnAgregar.setPreferredSize(new Dimension(90,25));
		this.btnAgregar.addActionListener( e -> {

			limpiarErrores();
			try {
				controller.guardar(this);

			} catch (Exception e1) {
				this.mostrarError("Error al guardar", e1.getMessage());
				return;
			}
			this.limpiarFormulario();
			this.actualizarTabla();

		});
		this.add(btnAgregar,constraintsBotones);
		
		//Botón Cancelar
		constraintsBotones.gridx = 3;
		constraintsBotones.gridy = 5;
		constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_START;
		constraintsBotones.insets = new Insets(20, 15, 15, 10);
		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.setPreferredSize(new Dimension(90,25));
		this.btnCancelar.addActionListener( e -> {

/*			limpiarErrores();
			limpiarFormulario();
			actualizarTabla();*/
			this.removeAll();
			revalidate();
			repaint();

		});
		this.add(btnCancelar,constraintsBotones);
		
		
		//Subtitulo 2: "Lista de Stock de Insumos"
		constraintsSubtitulos.gridx = 0; //Columna 0
		constraintsSubtitulos.gridy = 6; //Fila 5
		constraintsSubtitulos.gridwidth=1;
		constraintsSubtitulos.insets = new Insets(20, 10, 20, 0);
		lblSubtitulo2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSubtitulo2.setForeground(Color.BLACK);
		this.add(lblSubtitulo2,constraintsSubtitulos);

		//Subtitulo 3: "Ctrl+click derecho"
		constraintsSubtitulos.gridx = 1; //Columna 0
		constraintsSubtitulos.gridy = 6;
		constraintsSubtitulos.gridwidth=7;
		lblSubtitulo3.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblSubtitulo3.setForeground(Color.BLACK);
		this.add(lblSubtitulo3,constraintsSubtitulos);
		
		//Tabla Insumos con su stock
		constraintsTabla.gridx = 0;
		constraintsTabla.gridy = 7;
		this.modeloTablaStock = new StockTableModel(StockGuiController.getStockController());
		this.tablaStock = new JTable();
		this.tablaStock.setModel(this.modeloTablaStock);
		JScrollPane scrollPane = new JScrollPane(this.tablaStock);
		tablaStock.setFillsViewportHeight(true);
		this.add(scrollPane,constraintsTabla);

		//Botón Modificar:
		constraintsBotones.gridx = 6;
		constraintsBotones.gridy = 7;
		constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_START;
		this.btnModificar = new JButton("Modificar");
		this.btnModificar.setPreferredSize(new Dimension(95,25));
		this.add(btnModificar,constraintsBotones);
		this.btnModificar.addActionListener(e-> {

			//Con esto puedo obtener la fila que está seleccionada
			int fila = this.tablaStock.getSelectedRow();

			if (fila < 0) {

				this.mostrarError("Error al Seleccionar", "Debe seleccionar una fila de Tabla de Stock de Insumo y luego oprimir el botón Modificar");
				return;

			}

			System.out.println("Fila seleccionada");
			System.out.println(fila);


			this.controller.inicializarPanelModificacion(this.modificar, fila);

			//PopUp: Modificar Stock:
			modificar.setTitle("Modificación de Stock");
			modificar.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			modificar.setSize(new Dimension(800, 500));
			//Esto hace que la ventana quede centrada
			modificar.setLocationRelativeTo(null);
			modificar.setVisible(true);
			System.out.println("modificar creada");
			this.actualizarTabla();
		});

		//Botón Eliminar
		constraintsBotones.gridx = 6;
		constraintsBotones.gridy = 8;
		this.btnEliminar = new JButton("Eliminar");
		this.btnEliminar.setPreferredSize(new Dimension(95,25));
		this.add(btnEliminar,constraintsBotones);
		this.btnEliminar.addActionListener(e->{

			//Permite eliminar multiples filas en una sola vez
			int[] filas = this.tablaStock.getSelectedRows();

			if(filas.length==0){
				this.mostrarError("Error al Seleccionar", "Debe seleccionar una fila de Tabla de Stock de Insumo y luego oprimir el botón Eliminar");
				return;
			}
			for(int i: filas){
				System.out.println("fila: " + i);
			}

			//Llama a un mensaje de confirmación.
			//Si acepta, se invoca al controller con eliminar.
			//Si no, no hace nada
			//Esto hace que la ventana quede centrada

			Integer num = pedirConfirmacion("¿Desea eliminar los elementos seleccionados?", "Eliminar Insumo");
			System.out.println(num);

			if(num.equals(0)){
				this.controller.eliminar(filas);
			}
			this.actualizarTabla();

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
		this.txtPuntoPedido.setText("");
		this.txtInsumo.setSelectedIndex(0);
		this.txtPlanta.setSelectedIndex(0);
	}

	private void limpiarErrores(){

		this.lblErrorCantidad.setVisible(false);
		this.lblErrorPuntoPedido.setVisible(false);
	}

	public void mostrarErrores(List<Integer> campos){

		for(Integer campo : campos){
			switch (campo){
				case 0: this.lblErrorCantidad.setVisible(true); break;
				case 1: this.lblErrorPuntoPedido.setVisible(true); break;
			}
		}
	}

	public void mostrarErrores(Boolean[] campos){

		int i=0;
		while(i<campos.length) {

			if(!campos[i]){

				switch (i){
					case 0: this.lblErrorCantidad.setVisible(true); break;
					case 1: this.lblErrorPuntoPedido.setVisible(true); break;
				}
			}
			i++;
		}


	}

	public void actualizarTabla() {

		modeloTablaStock.fireTableDataChanged();

	}



	//Getters
	public JComboBox<String> getTxtPlanta() {
		return txtPlanta;
	}

	public JComboBox<String> getTxtInsumo() {
		return txtInsumo;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public JTextField getTxtPuntoPedido() {
		return txtPuntoPedido;
	}
}
