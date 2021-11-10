package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.RevisorInterface;
import ui.RevisorValorarArticulosDisplay;

public class ActionListenerCrearVentanaRevisorValorarArticulos implements ActionListener {

	private RevisorInterface revisorInterface;

	public ActionListenerCrearVentanaRevisorValorarArticulos(RevisorInterface revisorInterface) {
		this.revisorInterface = revisorInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		RevisorValorarArticulosDisplay rad = new RevisorValorarArticulosDisplay(revisorInterface.getNombre());
		
	}

}
