package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.RevisorVerComentInterface;
import ui.RevisorArticulosDisplay;

public class ActionListenerCrearVentanaVerComentarios implements ActionListener {
	private RevisorArticulosDisplay revisorArticulosDisplay;
	public ActionListenerCrearVentanaVerComentarios(RevisorArticulosDisplay revisorArticulosDisplay) {
		this.revisorArticulosDisplay = revisorArticulosDisplay;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		RevisorVerComentInterface rci = new RevisorVerComentInterface(revisorArticulosDisplay.getRevisor());
		
	}
}
