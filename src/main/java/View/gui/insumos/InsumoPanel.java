package View.gui.insumos;

import View.guiController.InsumoGuiController;
import View.guiController.StockGuiController;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class InsumoPanel extends JPanel{
	
	
	//Titulos
	private JLabel lblTitulo = new JLabel("Administración de Insumos:");
	private JLabel lblSubtitulo = new JLabel("Agregar Insumo:");
	private JLabel lblSubtitulo2 = new JLabel("Tabla de Insumos: ");
	private JLabel lblSubtitulo3 = new JLabel("([Ctrl + Click Izq] para seleccionar multiples filas)");

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
	
	//Tabla Insumos
	private JTable tablaInsumos;
	private InsumoTableModel modeloTablaInsumo;
	private JButton btnEliminar;
	private JButton btnModificar;

	//Errores
	private JLabel lblErrorDescripcion = new JLabel("Campo Alfanumérico y Obligatorio.");
	private JLabel lblErrorCostoPorUnidad = new JLabel("Campo Numérico y Obligatorio.");
	private JLabel lblErrorDensidad = new JLabel("Campo Numérico y Obligatorio.");

	//Otros
	private InsumoGuiController controller;
	private ModificacionInsumoPopUp modificar;

	public InsumoPanel() {

		super();

		//Primero debo inicializar el controller ya que armarPanel requiere de controller
		this.controller = InsumoGuiController.getInsumoController();
		this.modificar = new ModificacionInsumoPopUp(this);
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
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 2;
		lblDescripcion.setPreferredSize(new Dimension(80, 17));
		lblDescripcion.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblDescripcion, constraintsLabels);

		//TextField Descripción
		constraintsTextFields.gridx = 1; //Va al lado del Label
		constraintsTextFields.gridy = 2;
		this.txtDescripcion = new JTextField(0);
		this.txtDescripcion.setPreferredSize(new Dimension(200, 20));
		this.add(txtDescripcion, constraintsTextFields);

		//Error Descripcion
		constraintsErrores.gridx = 0; //Columna 0
		constraintsErrores.gridy = 3; //Fila 2
		this.lblErrorDescripcion.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorDescripcion.setForeground(Color.RED);
		this.add(this.lblErrorDescripcion, constraintsErrores);
		this.lblErrorDescripcion.setVisible(false);

		//Label Tipo de Insumo
		constraintsLabels.gridx = 2;
		constraintsLabels.gridy = 2;
		this.lblTipo.setPreferredSize(new Dimension(100, 17));
		this.lblTipo.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblTipo, constraintsLabels);

		//ComboBox Tipo de Insumo
		constraintsTextFields.gridx = 3;
		constraintsTextFields.gridy = 2;
		constraintsTextFields.weightx = 1.0;
		this.txtTipo = new JComboBox<>(this.controller.getTiposDeInsumos());
		this.txtTipo.setPreferredSize(new Dimension(200, 20));
		this.add(txtTipo, constraintsTextFields);
		this.txtTipo.addItemListener(e -> {

			limpiarErrores();
			if (txtTipo.getSelectedIndex() == 1) {
				this.lblPeso.setVisible(false);
				this.lblDensidad.setVisible(true);

			}
			if(txtTipo.getSelectedIndex()==0){

				this.lblPeso.setVisible(true);
				this.lblDensidad.setVisible(false);

			}
		});

		//Label Unidad de Medida
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 4;
		this.lblUnidad.setPreferredSize(new Dimension(120, 17));
		this.lblUnidad.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblUnidad, constraintsLabels);

		//ComboBox Unidad de Medida
		constraintsTextFields.gridx = 1; //Va al lado del Label
		constraintsTextFields.gridy = 4;
		this.txtUnidad = new JComboBox<>(this.controller.getUnidadesDeMedida());
		this.txtUnidad.setPreferredSize(new Dimension(200, 20));
		this.add(txtUnidad, constraintsTextFields);


		//Label Costo por unidad de Medida
		constraintsLabels.gridx = 2;
		constraintsLabels.gridy = 4;
		this.lblCostoU.setPreferredSize(new Dimension(180, 17));
		this.lblCostoU.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblCostoU, constraintsLabels);

		//TextField Costo por Unidad de Medida
		constraintsTextFields.gridx = 3;
		constraintsTextFields.gridy = 4;
		this.txtCostoU = new JTextField(0);
		this.txtCostoU.setPreferredSize(new Dimension(200, 20));
		this.add(txtCostoU, constraintsTextFields);

		//Error Costo por Unidad de Medida
		constraintsErrores.gridx = 2; //Columna 0
		constraintsErrores.gridy = 5; //Fila 2
		this.lblErrorCostoPorUnidad.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorCostoPorUnidad.setForeground(Color.RED);
		this.add(this.lblErrorCostoPorUnidad, constraintsErrores);
		this.lblErrorCostoPorUnidad.setVisible(false);

		//Label Densidad
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 6;
		this.lblDensidad.setPreferredSize(new Dimension(200, 17));
		this.lblDensidad.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblDensidad, constraintsLabels);
		this.lblDensidad.setVisible(false);

		//Label Peso
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 6;
		this.lblPeso.setPreferredSize(new Dimension(75, 17));
		this.lblPeso.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblPeso, constraintsLabels);
		this.lblPeso.setVisible(true);

		//TextField Densidad/Peso:
		constraintsTextFields.gridx = 1;
		constraintsTextFields.gridy = 6;
		this.txtDensidad = new JTextField(0);
		this.txtDensidad.setPreferredSize(new Dimension(200, 20));
		this.add(txtDensidad, constraintsTextFields);
		this.txtDensidad.setVisible(true);

		//Error Densidad/Peso
		constraintsErrores.gridx =0; //Columna 0
		constraintsErrores.gridy = 7; //Fila 2
		constraintsErrores.anchor = GridBagConstraints.FIRST_LINE_END;
		this.lblErrorDensidad.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorDensidad.setForeground(Color.RED);
		this.add(this.lblErrorDensidad,constraintsErrores);
		this.lblErrorDensidad.setVisible(false);


		//Botón Agregar
		constraintsBotones.gridx = 4;
		constraintsBotones.gridy = 7;
		constraintsErrores.anchor = GridBagConstraints.FIRST_LINE_END;
		this.btnAgregar = new JButton("Agregar");
		this.btnAgregar.setPreferredSize(new Dimension(95,25));
		this.btnAgregar.addActionListener(e->{

			limpiarErrores();
			try {
				controller.guardar(this);

			} catch (Exception e1) {
				this.mostrarError("Error al guardar", e1.getMessage());
				e1.printStackTrace();
				return;
			}
			this.limpiarFormulario();
			this.actualizarTabla();

		});
		this.add(btnAgregar,constraintsBotones);
		
		//Botón Cancelar
		constraintsBotones.gridx = 5;
		constraintsBotones.gridy = 7;
		constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_START;
		constraintsBotones.insets = new Insets(20, 15, 15, 10);
		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.setPreferredSize(new Dimension(95,25));
		this.add(btnCancelar,constraintsBotones);
		this.btnCancelar.addActionListener(e->{

			this.removeAll();
			revalidate();
			repaint();

		});


		//Subtitulo: Tabla de camiones
		constraintsSubtitulos.gridx = 0; //Columna 0
		constraintsSubtitulos.gridy = 8; //Fila 2
		constraintsSubtitulos.gridwidth = 1;
		lblSubtitulo2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSubtitulo2.setForeground(Color.BLACK);
		this.add(lblSubtitulo2, constraintsSubtitulos);

		//Subtitulo: Ctrl+Click Der
		constraintsSubtitulos.gridx = 1; //Columna 0
		constraintsSubtitulos.gridy = 8; //Fila 2
		//constraintsSubtitulos.anchor = GridBagConstraints.LINE_START;
		lblSubtitulo3.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblSubtitulo3.setForeground(Color.BLACK);
		this.add(lblSubtitulo3, constraintsSubtitulos);

		//Tabla Insumos
		constraintsTablas.gridx = 0;
		constraintsTablas.gridy = 9;
		this.modeloTablaInsumo = new InsumoTableModel(this.controller.listarTodos(), StockGuiController.getStockController());
		this.tablaInsumos = new JTable();
		this.tablaInsumos.setModel(this.modeloTablaInsumo);
		JScrollPane scrollPane = new JScrollPane(this.tablaInsumos);
		tablaInsumos.setFillsViewportHeight(true);	
		this.add(scrollPane,constraintsTablas);


		//Botón Modificar
		constraintsBotones.gridx = 5;
		constraintsBotones.gridy = 9;
		constraintsBotones.anchor = GridBagConstraints.FIRST_LINE_START;
		this.btnModificar = new JButton("Modificar");
		this.btnModificar.setPreferredSize(new Dimension(95,25));
		this.add(btnModificar,constraintsBotones);
		this.btnModificar.addActionListener(e-> {

			//Con esto puedo obtener la fila que está seleccionada
			int fila = this.tablaInsumos.getSelectedRow();

			if (fila < 0) {

				this.mostrarError("Error al Seleccionar", "Debe seleccionar una fila de Tabla de Insumos y luego oprimir el botón Modificar");
				return;

			}

			System.out.println("Fila seleccionada");
			System.out.println(fila);


			this.controller.inicializarPanelModificacion(this.modificar, fila);

			//PopUp: Modificar Camión:
			modificar.setTitle("Modificación de Insumo");
			modificar.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			modificar.setSize(new Dimension(800, 500));
			//Esto hace que la ventana quede centrada
			modificar.setLocationRelativeTo(null);
			modificar.setVisible(true);
			System.out.println("modificar creada");
			this.actualizarTabla();
		});

		//Botón Eliminar
		constraintsBotones.gridx = 5;
		constraintsBotones.gridy = 10;
		this.btnEliminar = new JButton("Eliminar");
		this.btnEliminar.setPreferredSize(new Dimension(95,25));
		this.add(btnEliminar,constraintsBotones);
		this.btnEliminar.addActionListener(e->{

			//Permite eliminar multiples filas en una sola vez
			int[] filas = this.tablaInsumos.getSelectedRows();

			if(filas.length==0){
				this.mostrarError("Error al Seleccionar", "Debe seleccionar una fila de Tabla de Insumos y luego oprimir el botón Eliminar");
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

	public void actualizarTabla() {

		modeloTablaInsumo.fireTableDataChanged();

	}


	//Getters y Setters
	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JComboBox<String> getTxtTipo() {
		return txtTipo;
	}

	public void setTxtTipo(JComboBox<String> txtTipo) {
		this.txtTipo = txtTipo;
	}

	public JComboBox<String> getTxtUnidad() {
		return txtUnidad;
	}

	public void setTxtUnidad(JComboBox<String> txtUnidad) {
		this.txtUnidad = txtUnidad;
	}

	public JTextField getTxtCostoU() {
		return txtCostoU;
	}

	public void setTxtCostoU(JTextField txtCostoU) {
		this.txtCostoU = txtCostoU;
	}

	public JTextField getTxtDensidad() {
		return txtDensidad;
	}

	public void setTxtDensidad(JTextField txtDensidad) {
		this.txtDensidad = txtDensidad;
	}

	public InsumoGuiController getController() {
		return controller;
	}

	public void setController(InsumoGuiController controller) {
		this.controller = controller;
	}

/*	public JTextField getTxtPeso() {
		return txtPeso;
	}*/


}
