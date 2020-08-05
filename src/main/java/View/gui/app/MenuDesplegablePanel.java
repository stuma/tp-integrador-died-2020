package View.gui.app;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class MenuDesplegablePanel extends JPanel {
	
	//Contendrá los botones pertenecientes a: Alta, Busqueda, Eliminacion
	JPanel camiones = new JPanel();
	JPanel insumos = new JPanel();
	
	//Al presionarlo, queda presionado :v
	JToggleButton opcionCamiones = new JToggleButton("Camiones");
	JLabel alta = new JLabel("Alta de Camión");
	JToggleButton opcionInsumos = new JToggleButton("Insumos");
	
	public MenuDesplegablePanel() {
	
		super();
		this.armarApp();
	
	}
	
	void armarApp() {
		
		this.smallMenu();
		this.opcionCamiones.add(alta);
		this.camiones.add(opcionCamiones);
		this.opcionCamiones.setSelected(false);
		this.insumos.add(opcionInsumos);
		this.add(camiones);
		this.add(insumos);

		
	}
	
	private void menuCamionesActionPermormed(ActionEvent e) {
		
		if(this.opcionCamiones.isSelected()) {
			//Menu camiones
			this.camiones.setSize(160,140);
			
			//Menu insumos
			this.insumos.setLocation(0,140);
			this.insumos.setSize(160,140);
			this.opcionInsumos.setSelected(false);
		}
		else {
			this.smallMenu();
		}
	}
	private void menuInsumosActionPermormed(ActionEvent e) {
		
		if(this.opcionInsumos.isSelected()) {
			
			//Menu camiones
			this.camiones.setSize(160,140);
			this.opcionCamiones.setSelected(false);
			
			//Menu insumos
			this.insumos.setLocation(0,140);
			this.insumos.setSize(160,140);
			
		}
		else {
			this.smallMenu();
		}
	}
	
	public void smallMenu() {
		
		this.camiones.setSize(160,40);
		this.opcionCamiones.setSelected(false);
		
		this.insumos.setLocation(0,40);
		this.insumos.setSize(160,40);
		this.opcionInsumos.setSelected(false);
		
	}
}
