package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.EditorStarUp;
import ui.VerComentariosEditor;

public class ActionListenerVerComentarioEditor implements ActionListener {
	private EditorStarUp esu;
	public ActionListenerVerComentarioEditor(EditorStarUp esu) {
		this.esu = esu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		VerComentariosEditor rci = new VerComentariosEditor(esu.getArticulo());
		
	}

}
