package View.gui.stock;

import View.controller.StockController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;


public class BuscarStockPanel extends JPanel{	
	
	//Titulos
	private JLabel lblTitulo = new JLabel("Administración de Stock:");
	private JLabel lblSubtitulo1 = new JLabel("Filtrar Stock:");
	private JLabel lblSubtitulo2 = new JLabel("Tabla de Insumos por Debajo del Punto de Pedido:");
	
	//Campos
	private JLabel lblPlanta = new JLabel("Nombre de Planta:");
	private JComboBox<String> txtPlanta;
	private JLabel lblInsumo = new JLabel("Descripción de Insumo:");
	private JComboBox<String> txtInsumo;
	
	//Botones
	private JButton btnFiltrar;
	private JButton btnSalir;

	//Tabla Stock
	private JTable tablaStock;
	private StockTableModel modeloTablaStock;
	private StockController controller;
	
	//Controller
	//private StockController controller;
	
	public BuscarStockPanel() {
		super();
		//Primero debo inicializar el controller ya que armarPanel requiere de controller
		this.controller = StockController.getStockController();
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
		constraintsSubtitulos.insets = new Insets(0, 10, 20, 0);
		constraintsSubtitulos.fill = GridBagConstraints.HORIZONTAL;

		GridBagConstraints constraintsLabels = new GridBagConstraints();
		constraintsLabels.gridwidth = 1;
		constraintsLabels.gridheight=1;
		constraintsLabels.weightx=0.15;
		constraintsLabels.weighty=0;
		constraintsLabels.insets = new Insets(5, 5, 5, 5); //-> Este determina la separación entre objetos.
		constraintsLabels.ipadx = 5; //separación interna
		constraintsLabels.ipady=5;
		constraintsLabels.fill = GridBagConstraints.NONE;
		constraintsLabels.anchor = GridBagConstraints.LINE_END;

		GridBagConstraints constraintsTextfields = new GridBagConstraints();
		constraintsTextfields.gridwidth = 1;
		constraintsTextfields.weightx=0.15;
		constraintsTextfields.weighty=0;
		constraintsTextfields.gridheight=1;
		constraintsTextfields.insets = new Insets(5, 5, 5, 5); //-> Este determina la separación entre objetos.
		constraintsTextfields.ipadx = 5;
		constraintsTextfields.ipady=5;
		constraintsTextfields.fill = GridBagConstraints.HORIZONTAL;
		constraintsTextfields.anchor = GridBagConstraints.LINE_START;

		GridBagConstraints constraintsErrores = new GridBagConstraints();
		constraintsErrores.gridwidth = 2;
		constraintsErrores.gridheight = 1;
		constraintsErrores.weightx = 1.0; //Estira a lo largo, es decir, estira en columnas una misma fila
		constraintsErrores.weighty = (double) (1 / 16);
		constraintsErrores.insets = new Insets(0, 5, 10, 5);
		constraintsErrores.anchor = GridBagConstraints.FIRST_LINE_END;
		constraintsErrores.fill = GridBagConstraints.NONE;

		GridBagConstraints constraintsTablas = new GridBagConstraints();
		constraintsTablas.gridwidth=5;
		constraintsTablas.gridheight=2;
		constraintsTablas.weightx=0;
		constraintsTablas.fill = GridBagConstraints.HORIZONTAL;
		constraintsTablas.anchor = GridBagConstraints.CENTER;
		constraintsTablas.insets = new Insets(10, 10, 10, 10);

		GridBagConstraints constraintsBotones = new GridBagConstraints();
		constraintsBotones.fill = GridBagConstraints.NONE;
		constraintsBotones.anchor = GridBagConstraints.CENTER;
		constraintsBotones.insets = new Insets(20, 15, 15, 0);

		//Titulo
		constraintsTitulos.gridx = 0;
		constraintsTitulos.gridy = 0;
		lblTitulo.setFont(new Font("System", Font.BOLD, 20));
		lblTitulo.setForeground(Color.BLACK);
		this.add(lblTitulo,constraintsTitulos);

		
		//Subtitulo1: "Filtrar por:"
		constraintsSubtitulos.gridx = 0; //Columna 0
		constraintsSubtitulos.gridy = 1; //Fila 1
		lblSubtitulo1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSubtitulo1.setForeground(Color.BLACK);
		this.add(lblSubtitulo1,constraintsSubtitulos);
		
		

		//Label Nombre de Planta
		constraintsLabels.gridx = 0;
		constraintsLabels.gridy = 2;
		lblPlanta.setPreferredSize(new Dimension(110, 17));
		lblPlanta.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblPlanta,constraintsLabels);

		
		//TextField Nombre de Planta
		constraintsTextfields.gridx = 1; //Va al lado del Label
		constraintsTextfields.gridy = 2;
		this.txtPlanta = new JComboBox<String>(this.controller.getPlantasBusq());
		this.txtPlanta.setPreferredSize(new Dimension(100, 25));
		this.txtPlanta.addActionListener(e -> {

			System.out.println("Planta cambió a : " + txtPlanta.getSelectedIndex());
			if (txtPlanta.getSelectedIndex() == 0) {

				this.controller.buscarTodos();
				this.actualizarTabla();

			}else{

				try {

					this.controller.buscarPorPlanta(this);

				} catch (Exception e1) {
					this.mostrarError("Error al Filtrar", e1.getMessage());
					return;
				}
				this.actualizarTabla();
			}


		});
		this.add(txtPlanta,constraintsTextfields);


		//Label Descripción del insumo:
		constraintsLabels.gridx = 2;
		constraintsLabels.gridy = 2;
		this.lblInsumo.setPreferredSize(new Dimension(140, 17));
		this.lblInsumo.setFont(new Font("System", Font.PLAIN, 13));
		this.add(lblInsumo,constraintsLabels);
		
		//TextField Descripción del insumo
		constraintsTextfields.gridx = 3; //Va al lado del Label
		constraintsTextfields.gridy = 2;
		constraintsTextfields.insets = new Insets(0, 0, 15, 10);
		this.txtInsumo =  new JComboBox<String>(this.controller.getInsumosBusq());
		this.txtInsumo.setPreferredSize(new Dimension(100, 25));
		this.txtInsumo.addActionListener(e -> {

			//TODO Esto no anda. Corregirlo
			if (txtInsumo.getSelectedIndex() == 0) {

				this.controller.buscarTodos();
				actualizarTabla();
				return;

			}else{

				try {
					this.controller.buscarPorInsumo(this);
				} catch (Exception e1) {
					this.mostrarError("Error al Filtrar", e1.getMessage());
					return;
				}
				actualizarTabla();
				this.tablaStock.setVisible(false);
				this.tablaStock.setVisible(true);

			}


		});
		this.add(this.txtInsumo,constraintsTextfields);


/*		//Botón Filtrar
		constraints.gridx = 4;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.NONE; 
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(0, 0, 15, 10);
		this.btnFiltrar = new JButton("Filtrar");
		this.btnFiltrar.setPreferredSize(new Dimension(80,25));
//		this.btnAgregar.addActionListener( e ->
//			{
//				try {
//					controller.guardar();
//				} catch (DatosObligatoriosException | FormatoNumeroException | ControllerException e1) {
//					this.mostrarError("Error al guardar", e1.getMessage());
//				}
//				this.limpiarFormulario();
//				modeloTablaCamion.fireTableDataChanged();
//				
//			}
//		);
		this.add(btnFiltrar,constraints);
		*/

		//Subtitulo 2: "Descripcion de Insumos..."
		constraintsSubtitulos.gridx = 0; //Columna 0
		constraintsSubtitulos.gridy = 4; //Fila 1
		lblSubtitulo2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSubtitulo2.setForeground(Color.BLACK);
		this.add(lblSubtitulo2,constraintsSubtitulos);
		
		
		//Tabla Stock
		constraintsTablas.gridx = 0;
		constraintsTablas.gridy = 5;
		this.modeloTablaStock = new StockTableModel(this.controller.listarTodos());
		this.tablaStock = new JTable();
		this.tablaStock.setModel(this.modeloTablaStock);
		JScrollPane scrollPane = new JScrollPane(this.tablaStock);
		tablaStock.setFillsViewportHeight(true);	
		this.add(scrollPane,constraintsTablas);

		
		//Botón Salir
		constraintsBotones.gridx = 4;
		constraintsBotones.gridy = 7;
		this.btnSalir = new JButton("Cancelar");
		this.btnSalir.setPreferredSize(new Dimension(80,25));
		this.btnSalir.addItemListener(e -> {

			limpiarFormulario();
			this.controller.buscarTodos();
			actualizarTabla();
			//TODO Ver que hacer con el botón

		});
		this.add(btnSalir,constraintsBotones);
		
		
	}
	
	public void mostrarError(String titulo,String detalle) {
		JFrame padre= (JFrame) SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(padre,
			    detalle,titulo,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private void limpiarFormulario() {
		this.txtPlanta.setSelectedItem(0);
		this.txtInsumo.setSelectedItem(0);

	}

	public void actualizarTabla() {

		modeloTablaStock.fireTableDataChanged();


	}

	public JComboBox<String> getTxtPlanta() {
		return txtPlanta;
	}

	public void setTxtPlanta(JComboBox<String> txtPlanta) {
		this.txtPlanta = txtPlanta;
	}

	public JComboBox<String> getTxtInsumo() {
		return txtInsumo;
	}

	public void setTxtInsumo(JComboBox<String> txtInsumo) {
		this.txtInsumo = txtInsumo;
	}
}
