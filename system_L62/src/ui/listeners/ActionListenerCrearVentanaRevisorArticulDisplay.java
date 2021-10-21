package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.RevisorArticulosDisplay;
import ui.RevisorInterface;

public class ActionListenerCrearVentanaRevisorArticulDisplay implements ActionListener {

	private RevisorInterface revisorInterface;

	public ActionListenerCrearVentanaRevisorArticulDisplay(RevisorInterface revisorInterface) {
		this.revisorInterface = revisorInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RevisorArticulosDisplay rad = new RevisorArticulosDisplay(revisorInterface.getNombre());
		rad.initialize();
	}
}
