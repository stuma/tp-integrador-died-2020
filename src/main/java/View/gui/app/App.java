package View.gui.app;

import View.gui.camiones.AltaCamionesPanel;
import View.gui.camiones.BusquedaCamionesPanel;
import View.gui.insumos.InsumoPanel;
import View.gui.ordenes.AgregarOrdenPanel;
import View.gui.ordenes.OrdenPedidoEntregadaPanel;
import View.gui.ordenes.ProcesarOrdenPanel;
import View.gui.planta.AgregarPlantaPanel;
import View.gui.planta.FlujoMaximoPanel;
import View.gui.stock.AgregarStockPanel;
import View.gui.stock.BuscarStockPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.MatteBorder;

// extiende de una ventana en el S.O
public class App extends JFrame {

	//Menú de opciones
	private JPanel panelMenu;
	
	//Menú Camiones
	private JToggleButton btnMenuCamiones;
	private JButton btnAltaCamion;
	private JButton btnBusquedaCamiones;
	
	//Menú Insumos
	private JToggleButton menuInsumos;
	private JButton btnInsumosPanel;
	
	//Menú Planta
	private JToggleButton menuPlanta;
	private JButton btnAltaPlanta;
	private JButton btnFlujoMax;
	
	//Menú Stock
	private JToggleButton menuStock;
	private JButton btnAltaStock;
	private JButton btnVerStock;
	
	//Menú Ordenes de Pedido
	private JToggleButton menuOrdenes;
	private JButton btnAgregarOrden;
	private JButton btnProcesarOrden;
	private JButton btnEntregarOrden;
	
	//Paneles 
	private JPanel panelGenerico;
	private JSplitPane panel; 
	
	//Constructor por defecto.
	private App() {
	}

	private void armarApp() {
		
		//MENÚ GENERAL
		
			//Creo el panel donde estará el menú. Este tendrá todos los botones que representan las diferentes acciones (Camiones, Insumos, Stock, Plantas, etc.)
			this.panelMenu = new JPanel();
			
			//Configura la forma y el color
			this.panelMenu.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
			
			//Indica posición en la pantalla donde estará (coordenadas 0,0) y el tamaño que tendrá el panel de tamaño (width, height)
			this.panelMenu.setBounds(0, 0, 179, 488);
			
			//El panel no tendrá un layout por defecto, por lo que su posicionamiento es fijo
			this.panelMenu.setLayout(null);
			
		//MENÚ CAMIONES
		
			//MENÚ DE CAMIONES:
			//Crea y configura el botón que desplegará el menú con las opciones
			this.btnMenuCamiones = new JToggleButton("Menú de Camiones");
		
			//Por defecto, el botón no está seleccionado
			this.btnMenuCamiones.setSelected(false);
			
			//Configuro el tamaño y la forma del botón
			this.btnMenuCamiones.setBorderPainted(true);
			this.btnMenuCamiones.setBounds(8, 8, 159, 35);
			
			//Le agrego un Listener para las acciones de Mouse que defina el comportamiento del menú desplegable.
			this.btnMenuCamiones.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent me) 
				{
					//Si ocurre un click en el botón de menú (se selecciona)
					if(btnMenuCamiones.isSelected())
					{
						//Despliega la opción de Alta (la muestra, con el setVisible igual true) y la de Busqueda
						btnAltaCamion.setVisible(true);
						btnBusquedaCamiones.setVisible(true);
						
					}
					else
					{
						//Oculta la opción de Alta (con el setVisible igual false) y la de Busqueda
						btnAltaCamion.setVisible(false);
						btnBusquedaCamiones.setVisible(false);
					}
				}
			});
			//Agrego el menú de camiones al panel del menú principal
			this.panelMenu.add(btnMenuCamiones);
		
			//BOTÓN ALTA DE CAMIÓN
			//Según si el botón btnMenuCamiones se seleccione o no, este botón será visible o no
			this.btnAltaCamion = new JButton("Agregar Camión");
			
			//Indica la posición donde va a estar el botón y su tamaño
			this.btnAltaCamion.setBounds(8, 50, 159, 23);
			
			//Por defecto no se muestra
			this.btnAltaCamion.setVisible(false);
			
			//Agrego un Action Listener para que muestre la pantalla de Alta camiones en caso de que este botón se clickee
			this.btnAltaCamion.addActionListener(e->{
				
				//Remueve el panel que previamente haya estado configurado del SplitPane
				this.panel.remove(panelGenerico);
				
				//Asigno un nuevo panel a panelGenérico que será del tipo AltaCamiones, ya que se quiere mostrar dicha interfaz
				this.panelGenerico = new AltaCamionesPanel();
				
				//Asigno el nuevo valor al SplitPane del lado derecho
				this.panel.setRightComponent(panelGenerico);
				
				//Actualiza la ventana por si ocurrió un cambio en los componentes
				this.revalidate();
				this.repaint();
				this.setVisible(true);
			
			});
			
			//Agrego este botón al Panel de Menú
			this.panelMenu.add(this.btnAltaCamion);
			
			//BOTÓN BÚSQUEDA DE CAMIONES:
			
			this.btnBusquedaCamiones = new JButton("Buscar Camión");
			this.btnBusquedaCamiones.setBounds(8, 76, 159, 23);
			this.btnBusquedaCamiones.setVisible(false);
			
			//Agrego un Action Listener para que muestre la pantalla de Busqueda de camiones en caso de que este botón se clickee
			this.btnBusquedaCamiones.addActionListener(e->{
				
				this.panel.remove(panelGenerico);
				
				this.panelGenerico = new BusquedaCamionesPanel();
				this.panel.setRightComponent(panelGenerico);
				
				this.revalidate();
				this.repaint();
				this.setVisible(true);
			});
			
			//Agrego el botón de Busqueda de camiones al panel de camiones
			this.panelMenu.add(this.btnBusquedaCamiones);
			
		//MENU INSUMOS
			
			//BOTÓN MENÚ DE INSUMOS:
			//Configuración del menú que despliega opciones
			this.menuInsumos = new JToggleButton("Menú de Insumos");
			
			//Listener para eventos de mouse
			this.menuInsumos.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent me)
				{
					if(menuInsumos.isSelected())
					{
						//Habilita la visibilidad del botón insumosPanel
						btnInsumosPanel.setVisible(true);
					}
					else
					{
						//Deshabilita la visibilidad del botón insumosPanel
						btnInsumosPanel.setVisible(false);
	
					}
				}
			});
			this.menuInsumos.setSelected(false);
			this.menuInsumos.setBorderPainted(true);
			this.menuInsumos.setBounds(8, 107, 159, 35);
			this.panelMenu.add(this.menuInsumos);
			
			//BOTÓN GESTIÓN DE INSUMOS:
			this.btnInsumosPanel = new JButton("Gestión de Insumos");
			this.btnInsumosPanel.setBounds(8, 148, 159, 23);
			this.btnInsumosPanel.setVisible(false);
			this.btnInsumosPanel.addActionListener(e->{
				
				this.panelGenerico = new InsumoPanel();
				JScrollPane scroll = new JScrollPane(this.panelGenerico);
				this.panel.setRightComponent(scroll);
				this.setVisible(true);

			});
			
			this.panelMenu.add(this.btnInsumosPanel);

		
		//MENÚ DE PLANTAS:
			
			//BOTÓN MENÚ PLANTAS
			this.menuPlanta = new JToggleButton("Menú de Plantas");
			
			//Listener para eventos de mouse
			this.menuPlanta.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent me)
				{
					if(menuPlanta.isSelected())
					{
						//Habilita la visibilidad de las opciones posibles
						btnAltaPlanta.setVisible(true);
						btnFlujoMax.setVisible(true);
						
					}
					else
					{
						//Deshabilita la visibilidad de los botones
						btnAltaPlanta.setVisible(false);
						btnFlujoMax.setVisible(false);
	
					}
				}
			});
			
			this.menuPlanta.setSelected(false);
			this.menuPlanta.setBorderPainted(true);
			this.menuPlanta.setBounds(8, 179, 159, 35);
			this.panelMenu.add(this.menuPlanta);	
			
			//BOTÓN MENÚ AGREGAR PLANTA
			this.btnAltaPlanta = new JButton("Agregar Planta");
			this.btnAltaPlanta.setBounds(8, 223, 159, 23);
			this.btnAltaPlanta.setVisible(false);
			this.btnAltaPlanta.addActionListener(e->{
				//TODO Agregar panel
				this.panelGenerico = new AgregarPlantaPanel();
				JScrollPane scroll = new JScrollPane(this.panelGenerico);
				this.panel.setRightComponent(scroll);
				this.setVisible(true);

			});
			this.panelMenu.add(this.btnAltaPlanta);
			
			//BOTÓN MENÚ FLUJO MÁXIMO
			this.btnFlujoMax = new JButton("Flujo Máximo - Rutas");
			this.btnFlujoMax.setBounds(8, 249, 159, 23);
			this.btnFlujoMax.setVisible(false);
			this.btnFlujoMax.addActionListener(e->{
				//TODO Agregar Panel
				this.panelGenerico = new FlujoMaximoPanel();
				JScrollPane scroll = new JScrollPane(this.panelGenerico);
				this.panel.setRightComponent(this.panelGenerico);
				this.setVisible(true);
			});
			this.panelMenu.add(this.btnFlujoMax);
		
		//MENÚ DE STOCK
			
			//BOTÓN DE MENÚ DE STOCK
			this.menuStock = new JToggleButton("Menú de Stock");
			
			//Listener para eventos de mouse
			this.menuStock.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent me)
				{
					if(menuStock.isSelected())
					{
						//Habilita la visibilidad de las opciones posibles
						btnAltaStock.setVisible(true);
						btnVerStock.setVisible(true);
						
					}
					else
					{
						//Deshabilita la visibilidad de los botones
						btnAltaStock.setVisible(false);
						btnVerStock.setVisible(false);
	
					}
				}
			});
			
			this.menuStock.setSelected(false);
			this.menuStock.setBorderPainted(true);
			this.menuStock.setBounds(8, 280, 159, 35);
			this.panelMenu.add(this.menuStock);
			
			//BOTÓN MENÚ ALTA STOCK
			this.btnAltaStock = new JButton("Agregar Stock");
			this.btnAltaStock.setBounds(8, 322, 159, 23);
			this.btnAltaStock.setVisible(false);
			this.btnAltaStock.addActionListener(e->{
				//TODO Agregar Panel
				this.panelGenerico = new AgregarStockPanel();
				JScrollPane scroll = new JScrollPane(this.panelGenerico);
				this.panel.setRightComponent(scroll);
				this.setVisible(true);

			});
			this.panelMenu.add(this.btnAltaStock);
			
			//BOTÓN MENÚ VER STOCK
			this.btnVerStock = new JButton("Ver Stock");
			this.btnVerStock.setBounds(8, 348, 159, 23);
			this.btnVerStock.setVisible(false);
			this.btnVerStock.addActionListener(e->{
				//TODO Agregar Panel
				this.panelGenerico = new BuscarStockPanel();
				JScrollPane scroll = new JScrollPane(this.panelGenerico);
				this.panel.setRightComponent(this.panelGenerico);
				this.setVisible(true);

			});
			this.panelMenu.add(this.btnVerStock);
		
		//MENÚ ORDENES DE PEDIDO:
			
			//BOTÓN DE MENÚ DE ORDENES
			this.menuOrdenes = new JToggleButton("Menú de Ordenes");
			
			//Listener para eventos de mouse
			this.menuOrdenes.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent me)
				{
					if(menuOrdenes.isSelected())
					{
						//Habilita la visibilidad de las opciones posibles
						btnAgregarOrden.setVisible(true);
						btnProcesarOrden.setVisible(true);
						btnEntregarOrden.setVisible(true);
						
					}
					else
					{
						//Deshabilita la visibilidad de los botones
						btnAgregarOrden.setVisible(false);
						btnProcesarOrden.setVisible(false);
						btnEntregarOrden.setVisible(false);
	
					}
				}
			});
			this.menuOrdenes.setSelected(true);
			this.menuOrdenes.setBorderPainted(true);
			this.menuOrdenes.setBounds(8, 379, 159, 35);
			this.panelMenu.add(this.menuOrdenes);
			
			//BOTÓN MENÚ ALTA ORDEN
			this.btnAgregarOrden = new JButton("Agregar Orden");
			this.btnAgregarOrden.setBounds(8, 421, 159, 23);
			this.btnAgregarOrden.setVisible(false);
			this.btnAgregarOrden.addActionListener(e->{
				//TODO Agregar Panel
				this.panelGenerico = new AgregarOrdenPanel();
				JScrollPane scroll = new JScrollPane(this.panelGenerico);
				this.panel.setRightComponent(this.panelGenerico);
				this.setVisible(true);
			});
			this.panelMenu.add(this.btnAgregarOrden);
		
			//BOTÓN MENÚ PROCESAR ORDEN
			this.btnProcesarOrden = new JButton("Procesar Orden");
			this.btnProcesarOrden.setBounds(8, 447, 159, 23);
			this.btnProcesarOrden.setVisible(false);
			this.btnProcesarOrden.addActionListener(e->{
				//TODO Agregar Panel
				this.panelGenerico = new ProcesarOrdenPanel();
				JScrollPane scroll = new JScrollPane(this.panelGenerico);
				this.panel.setRightComponent(this.panelGenerico);
				this.setVisible(true);
			});
			this.panelMenu.add(this.btnProcesarOrden);

			//BOTÓN MENÚ ENTREGAR ORDEN
			this.btnEntregarOrden = new JButton("Entregar Orden");
			this.btnEntregarOrden.setBounds(8, 473, 159, 23);
			this.btnEntregarOrden.setVisible(false);
			this.btnEntregarOrden.addActionListener(e->{
				//TODO Agregar Panel
				this.panelGenerico = new OrdenPedidoEntregadaPanel();
				JScrollPane scroll = new JScrollPane(this.panelGenerico);
				this.panel.setRightComponent(this.panelGenerico);
				this.setVisible(true);
			});
			this.panelMenu.add(this.btnEntregarOrden);



		this.panelMenu.setMinimumSize(this.panelMenu.getPreferredSize());
		
		//ARMADO DE SPLIT PANEL
			
			this.panelGenerico = new JPanel();
			this.panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.panelMenu, this.panelGenerico);
			this.panel.setOneTouchExpandable(true);
			this.panel.setDividerLocation(175);
			this.setContentPane(panel);

		
	}
	
	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				  try {
			            // Set System L&F
//			          UIManager.setLookAndFeel(
//			          UIManager.getSystemLookAndFeelClassName());
//					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");			    }
					  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");			    }

			    catch (ClassNotFoundException e) {
			       // handle exception
					System.out.println("Clase no encontrada");
			    }
			    catch (InstantiationException e) {
			       // handle exception
					System.out.println("Instancia");
			    }
			    catch (IllegalAccessException e) {
			       // handle exception
					System.out.println("Illegal Access");
			    }
				catch (UnsupportedLookAndFeelException e) {
					  // handle exception

				}
				  App app = new App();
				  app.setTitle("Sistema de gestion logística - TP DIED 2020 ");
				  app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				  app.armarApp();
				  app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				  app.setVisible(true);
				  System.out.println("app creada");
				  
			}
		});
	}
}
