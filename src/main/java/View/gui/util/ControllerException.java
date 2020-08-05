package View.gui.util;

public class ControllerException extends Exception {
	
	public ControllerException() {
		super("Error actualizando el modelo");
	}

	public ControllerException(String msg) {
		super(msg);
	}

	
}
