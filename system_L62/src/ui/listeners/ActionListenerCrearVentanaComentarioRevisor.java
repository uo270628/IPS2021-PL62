package ui.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.RevisorComentInterface;
import ui.RevisorInterface;

public class ActionListenerCrearVentanaComentarioRevisor implements ActionListener{
	private RevisorInterface revisorInterface;
	public ActionListenerCrearVentanaComentarioRevisor(RevisorInterface revisorInterface) {
		this.revisorInterface = revisorInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RevisorComentInterface rci = new RevisorComentInterface(revisorInterface.getNombre());
		rci.initialize();
	}

}
