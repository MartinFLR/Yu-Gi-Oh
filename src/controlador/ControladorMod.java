package controlador;

import vista.VistaMod;

public class ControladorMod {

	private VistaMod vista;
	
	public ControladorMod() {
		this.vista = new VistaMod(this);
		this.vista.setVisible(true);
	}
	
}
