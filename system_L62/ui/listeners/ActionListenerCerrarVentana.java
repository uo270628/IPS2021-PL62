package ui.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Ventana;

public class ActionListenerCerrarVentana implements ActionListener{
	
	public ActionListenerCerrarVentana(Ventana vent) {
		super();
		this.vent = vent;
	}
	Ventana vent;
	@Override
	public void actionPerformed(ActionEvent e) {
		vent.close();
	}

}

