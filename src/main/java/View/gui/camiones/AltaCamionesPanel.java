package View.gui.camiones;

import View.guiController.CamionGuiController;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AltaCamionesPanel extends JPanel{
	
	//Titulos
	private JLabel lblTitulo = new JLabel("Alta de Camiones: ");
	private JLabel lblSubtitulo1 = new JLabel("Agregar Camion: ");
	private JLabel lblSubtitulo2 = new JLabel("Tabla de Camiones: ");

	//Campos
	private JLabel lblPatente = new JLabel("Patente: *");
	private JTextField txtPatente;
	private JLabel lblModelo = new JLabel("Modelo: *");
	private JTextField txtModelo;
	private JLabel lblMarca = new JLabel("Marca: *");
	private JTextField txtMarca;

	private JLabel lblFecha = new JLabel("Fecha de Compra: * (DD/MM/YYYY)");
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JFormattedTextField txtFechaCompra = new JFormattedTextField(df);	
	private JLabel lblKm = new JLabel("Km Recorridos: *");
	private JTextField txtKm;
	private JLabel lblCostoKm = new JLabel("Costo por KM: *");
	private JTextField txtCostoKm;
	private JLabel lblCostoHs = new JLabel("Costo por Hora: * ");
	private JTextField txtCostoHs;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	//Tabla Camiones
	private JTable tablaCamiones;
	private CamionTableModel modeloTablaCamion;

	//Errores
	private JLabel lblErrorPatente = new JLabel("Campo Alfanumérico y Obligatorio.");
	private JLabel lblErrorModelo = new JLabel("Campo Alfanumérico y Obligatorio.");
	private JLabel lblErrorMarca = new JLabel("Campo Alfanumérico y Obligatorio.");
	private JLabel lblErrorKm = new JLabel("Campo Numérico y Obligatorio.");
	private JLabel lblErrorFecha = new JLabel("Campo con Formato (DD/MM/YYYY) y Obligatorio.");
	private JLabel lblErrorCostoKm = new JLabel("Campo Numérico y Obligatorio.");
	private JLabel lblErrorCostoHs = new JLabel("Campo Numérico y Obligatorio.");


	//Otros
	private CamionGuiController controller;



	public AltaCamionesPanel() {
		super();
		this.controller = CamionGuiController.getCamionController();
		this.armarPanel();
	}
	
	private void armarPanel() {
		
		//Agrega un Layout
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);

		GridBagConstraints constraintsTitulo = new GridBagConstraints();
		constraintsTitulo.gridx = 0;
		constraintsTitulo.gridy = 0;
		constraintsTitulo.fill = GridBagConstraints.VERTICAL;
		constraintsTitulo.anchor = GridBagConstraints.CENTER;
		constraintsTitulo.gridwidth=8;
		constraintsTitulo.insets = new Insets(0, 0, 15, 0);

		GridBagConstraints constraintsSubtitulos = new GridBagConstraints();
		constraintsSubtitulos.gridx = 0; //Columna 0
		constraintsSubtitulos.gridy = 1; //Fila 2
		constraintsSubtitulos.gridwidth=8;
		constraintsSubtitulos.weightx=1.0;
		constraintsSubtitulos.weighty=(double)(1/16);
		constraintsSubtitulos.insets = new Insets(0, 10, 10, 0);
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

		GridBagConstraints constraintsTextFields = new GridBagConstraints();
		constraintsTextFields.gridwidth = 1;
		constraintsTextFields.weightx=0.12; //
		constraintsTextFields.weighty=0;
		constraintsTextFields.gridheight=1;
		constraintsTextFields.insets = new Insets(5, 5, 5, 0); //-> Este determina la separación entre objetos.
		constraintsTextFields.ipadx = 5;
		constraintsTextFields.ipady=5;
		constraintsTextFields.fill = GridBagConstraints.HORIZONTAL;
		constraintsTextFields.anchor = GridBagConstraints.LINE_START;

		GridBagConstraints constraintsErrores = new GridBagConstraints();
		constraintsErrores.gridwidth=2;
		constraintsErrores.gridheight=1;
		constraintsErrores.weightx=1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
		constraintsErrores.weighty=(double)(1/16);
		constraintsErrores.insets = new Insets(0, 5, 10, 5);
		constraintsErrores.anchor = GridBagConstraints.LAST_LINE_END;
		constraintsErrores.fill = GridBagConstraints.VERTICAL;

		GridBagConstraints constraintsBotones = new GridBagConstraints();
		constraintsBotones.gridwidth = 1;
		constraintsBotones.gridheight=1;
		constraintsBotones.weightx=0.12;
		constraintsBotones.weighty=0;
		constraintsBotones.ipadx = 5;
		constraintsBotones.ipady=5;
		constraintsBotones.fill = GridBagConstraints.NONE;
		constraintsBotones.anchor = GridBagConstraints.LINE_END;
		constraintsBotones.insets = new Insets(20, 15, 15, 0);

		GridBagConstraints constraintsTabla = new GridBagConstraints();
		constraintsTabla.gridwidth=8;
		constraintsTabla.gridheight=1;
		constraintsTabla.weighty=1.0;
		constraintsTabla.weightx=1.0;
		constraintsTabla.anchor = GridBagConstraints.FIRST_LINE_START;
		constraintsTabla.fill = GridBagConstraints.HORIZONTAL;
		constraintsTabla.insets = new Insets(0, 10, 0, 10);

		//Titulo
		lblTitulo.setFont(new Font("System", Font.BOLD, 20));
		lblTitulo.setForeground(Color.BLACK);
		this.add(lblTitulo,constraintsTitulo);

		//Subtitulo: Agregar Camión
		lblSubtitulo1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSubtitulo1.setForeground(Color.BLACK);
		this.add(lblSubtitulo1,constraintsSubtitulos);


		//Label Patente
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 2;
		constraintsLabels.insets = new Insets(5, 15, 5, 0);
		lblPatente.setPreferredSize(new Dimension(55, 17));
		lblPatente.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblPatente,constraintsLabels);
		constraintsLabels.insets = new Insets(5, 5, 5, 0);
		
		//TextField Patente
		constraintsTextFields.gridx = 1; //Va al lado del Label
		constraintsTextFields.gridy = 2;
		this.txtPatente = new JTextField(0);
		this.txtPatente.setMinimumSize(new Dimension(100,20));
		this.add(txtPatente, constraintsTextFields);

		//Error Patente Incompleta
		constraintsErrores.gridx = 0; //Columna 0
		constraintsErrores.gridy = 3; //Fila 2
		this.lblErrorPatente.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorPatente.setForeground(Color.RED);
		this.add(this.lblErrorPatente,constraintsErrores);
		this.lblErrorPatente.setVisible(false);


		//Label Modelo
		constraintsLabels.gridx = 2;
		constraintsLabels.gridy = 2;
		this.lblModelo.setPreferredSize(new Dimension(55, 17));
		this.lblModelo.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblModelo,constraintsLabels);
		
		//TextField Modelo
		constraintsTextFields.gridx = 3; //Va al lado del Label
		constraintsTextFields.gridy = 2;
		this.txtModelo = new JTextField(0);
		this.txtModelo.setMinimumSize(new Dimension(100,20));
		this.add(txtModelo, constraintsTextFields);

		//Error Modelo Incompleto
		constraintsErrores.gridx = 2;
		constraintsErrores.gridy = 3;
		this.lblErrorModelo.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorModelo.setForeground(Color.RED);
		this.add(this.lblErrorModelo,constraintsErrores);
		this.lblErrorModelo.setVisible(false);


		//Label Marca
		constraintsLabels.gridx = 4;
		constraintsLabels.gridy = 2;
		this.lblMarca.setPreferredSize(new Dimension(55, 17));
		this.lblMarca.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblMarca,constraintsLabels);
		
		//TextField Marca
		constraintsTextFields.gridx = 5; //Va al lado del Label
		constraintsTextFields.gridy = 2;
		this.txtMarca = new JTextField(0);
		this.txtMarca.setMinimumSize(new Dimension(100,20));
		this.add(txtMarca, constraintsTextFields);

		//Error Marca Incompleto
		constraintsErrores.gridx = 4; //Columna 0
		constraintsErrores.gridy = 3; //Fila 2
		this.lblErrorMarca.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorMarca.setForeground(Color.RED);
		this.add(this.lblErrorMarca,constraintsErrores);
		this.lblErrorMarca.setVisible(false);

		//Label Km Recorridos
		constraintsLabels.gridx = 6;
		constraintsLabels.gridy = 2;
		this.lblKm.setPreferredSize(new Dimension(100, 17));
		this.lblKm.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblKm,constraintsLabels);
		
		//TextField Km Recorridos
		constraintsTextFields.gridx = 7;
		constraintsTextFields.gridy = 2;
		this.txtKm = new JTextField(0);	
		this.txtKm.setPreferredSize(new Dimension(200, 20));	
		this.add(txtKm, constraintsTextFields);

		//Error Km Recorridos
		constraintsErrores.gridx = 6; //Columna 0
		constraintsErrores.gridy = 3; //Fila 2
		this.lblErrorKm.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorKm.setForeground(Color.RED);
		this.add(this.lblErrorKm,constraintsErrores);
		this.lblErrorKm.setVisible(false);

		//Label Fecha de Compra
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 4;
		constraintsLabels.insets = new Insets(5, 15, 5, 0);
		this.lblFecha.setPreferredSize(new Dimension(80, 17));
		this.lblFecha.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblFecha,constraintsLabels);
		constraintsLabels.insets = new Insets(5, 5, 5, 0);

		//TextField Fecha de Compra:
		constraintsTextFields.gridx = 1;
		constraintsTextFields.gridy = 4;
		this.txtFechaCompra = new JFormattedTextField();
		this.txtFechaCompra.setPreferredSize(new Dimension(200, 20));	
		this.add(txtFechaCompra, constraintsTextFields);

		//Error Fecha de Compra
		constraintsErrores.gridx = 0; //Columna 0
		constraintsErrores.gridy = 5; //Fila 2
		this.lblErrorFecha.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorFecha.setForeground(Color.RED);
		this.add(this.lblErrorFecha,constraintsErrores);
		this.lblErrorFecha.setVisible(false);


		//Label Costo por Km:
		constraintsLabels.gridx = 2;
		constraintsLabels.gridy = 4;
		this.lblCostoKm.setPreferredSize(new Dimension(77, 17));
		this.lblCostoKm.setFont(new Font("System", Font.PLAIN, 12));
		this.add(lblCostoKm,constraintsLabels);
		
		//TextField Costo por Km:
		constraintsTextFields.gridx = 3;
		constraintsTextFields.gridy = 4;
		this.txtCostoKm = new JTextField(0);
		this.txtCostoKm.setPreferredSize(new Dimension(200, 20));	
		this.add(txtCostoKm, constraintsTextFields);

		//Error Costo por Km
		constraintsErrores.gridx = 2; //Columna 0
		constraintsErrores.gridy = 5; //Fila 2
		this.lblErrorCostoKm.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorCostoKm.setForeground(Color.RED);
		this.add(this.lblErrorCostoKm,constraintsErrores);
		this.lblErrorCostoKm.setVisible(false);


		//Label Costo por Hora:
		constraintsLabels.gridx = 4;
		constraintsLabels.gridy = 4;
		this.lblCostoHs.setPreferredSize(new Dimension(86, 17));
		this.lblCostoHs.setFont(new Font("System", Font.PLAIN, 12));
		this.add(lblCostoHs,constraintsLabels);
		
		//TextField Costo por Hora:
		constraintsTextFields.gridx = 5;
		constraintsTextFields.gridy = 4;
		this.txtCostoHs = new JTextField(0);
		this.txtCostoHs.setPreferredSize(new Dimension(200, 20));	
		this.add(txtCostoHs, constraintsTextFields);

		//Error Costo por Hora
		constraintsErrores.gridx = 4; //Columna 0
		constraintsErrores.gridy = 5; //Fila 2
		this.lblErrorCostoHs.setFont(new Font("Calibri", Font.PLAIN, 13));
		this.lblErrorCostoHs.setForeground(Color.RED);
		this.add(this.lblErrorCostoHs,constraintsErrores);
		this.lblErrorCostoHs.setVisible(false);


		//BOTONES:
		//Botón Agregar
		constraintsBotones.gridx = 6;
		constraintsBotones.gridy = 6;
		this.btnGuardar = new JButton("Agregar");
		this.btnGuardar.setPreferredSize(new Dimension(80,25));
		this.btnGuardar.addActionListener( e ->
			{
				try {
					limpiarErrores();
					controller.guardar(this);
				} catch (Exception e1) {
					this.mostrarError("Error al guardar", e1.getMessage());
					e1.printStackTrace();
					return;
				}
				this.limpiarFormulario();
				modeloTablaCamion.fireTableDataChanged();
				
			}
		);
		this.add(btnGuardar,constraintsBotones);
		
		//Botón Cancelar
		constraintsBotones.gridx = 7;
		constraintsBotones.gridy = 6;
		constraintsBotones.anchor = GridBagConstraints.LINE_START;
		constraintsBotones.insets = new Insets(20, 15, 15, 0);
		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.setPreferredSize(new Dimension(80,25));
		this.btnCancelar.addActionListener( e -> {

/*
			this.limpiarFormulario();
			this.limpiarErrores();
			modeloTablaCamion.fireTableDataChanged();
*/
					this.removeAll();
					revalidate();
					repaint();

		}
		);
		this.add(btnCancelar,constraintsBotones);
		
		//Subtitulo 2: Tabla de Camiones
		constraintsSubtitulos.gridx = 0; //Columna 0
		constraintsSubtitulos.gridy = 7; //Fila 2
		lblSubtitulo2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSubtitulo2.setForeground(Color.BLACK);
		this.add(lblSubtitulo2,constraintsSubtitulos);

		//Tabla Camiones
		constraintsTabla.gridx = 0;
		constraintsTabla.gridy = 8;
		this.modeloTablaCamion = new CamionTableModel(controller.listarTodos());
		this.tablaCamiones = new JTable();
		tablaCamiones.setModel(modeloTablaCamion);
		JScrollPane scrollPane = new JScrollPane(tablaCamiones);
		tablaCamiones.setFillsViewportHeight(true);
		scrollPane.setMinimumSize( scrollPane.getPreferredSize());
		this.add(scrollPane,constraintsTabla);
		
		
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private void limpiarFormulario() {
		
		this.txtPatente.setText("");
		this.txtModelo.setText("");
		this.txtMarca.setText("");
		this.txtKm.setText("");
		this.txtFechaCompra.setText("");
		this.txtCostoHs.setText("");
		this.txtCostoKm.setText("");
		
	}

	private void limpiarErrores(){
		this.lblErrorPatente.setVisible(false);
		this.lblErrorModelo.setVisible(false);
		this.lblErrorMarca.setVisible(false);
		this.lblErrorKm.setVisible(false);
		this.lblErrorFecha.setVisible(false);
		this.lblErrorCostoKm.setVisible(false);
		this.lblErrorCostoHs.setVisible(false);
	}

	public void mostrarErrores(List<Integer> campos){

		for(Integer campo : campos){
			switch (campo){
				case 0: this.lblErrorPatente.setVisible(true); break;
				case 1: this.lblErrorModelo.setVisible(true); break;
				case 2: this.lblErrorMarca.setVisible(true); break;
				case 3: this.lblErrorKm.setVisible(true); break;
				case 4: this.lblErrorFecha.setVisible(true); break;
				case 5: this.lblErrorCostoKm.setVisible(true); break;
				case 6: this.lblErrorCostoHs.setVisible(true); break;
			}
		}

	}

	public void mostrarErrores(Boolean[] campos){

		int i=0;
		while(campos[i]){
			i++;
		}

		switch(i){

			case 0: this.lblErrorPatente.setVisible(true); break;
			case 1: this.lblErrorModelo.setVisible(true); break;
			case 2: this.lblErrorMarca.setVisible(true); break;
			case 3: this.lblErrorKm.setVisible(true); break;
			case 4: this.lblErrorFecha.setVisible(true); break;
			case 5: this.lblErrorCostoKm.setVisible(true); break;
			case 6: this.lblErrorCostoHs.setVisible(true); break;

		}

	}

	//Getters para el Controller a la hora de ejecutar el save o guardar
	public JTextField getTxtPatente() {
		return txtPatente;
	}

	public JTextField getTxtModelo() {
		return txtModelo;
	}

	public JTextField getTxtMarca() {
		return txtMarca;
	}

	public DateTimeFormatter getDf() {
		return df;
	}

	public JFormattedTextField getTxtFechaCompra() {
		return txtFechaCompra;
	}

	public JTextField getTxtKm() {
		return txtKm;
	}

	public JTextField getTxtCostoKm() {
		return txtCostoKm;
	}

	public JTextField getTxtCostoHs() {
		return txtCostoHs;
	}

	public CamionGuiController getController() {
		return controller;
	}
	
	
}
