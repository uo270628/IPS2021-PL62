package ui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import business.Comentario;

public class InterfazListarComentariosRevisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPaneComentarios;
	private JList<Comentario> listComentarios;

	private DefaultListModel<Comentario> modelComentarios;

	/**
	 * Create the frame.
	 */
	public InterfazListarComentariosRevisor() {
		modelComentarios = new DefaultListModel<>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPaneComentarios());
	}

	public JScrollPane getScrollPaneComentarios() {
		if (scrollPaneComentarios == null) {
			scrollPaneComentarios = new JScrollPane();
			scrollPaneComentarios.setBounds(82, 61, 248, 128);
			scrollPaneComentarios.setViewportView(getListComentarios());
		}
		return scrollPaneComentarios;
	}

	public JList<Comentario> getListComentarios() {
		if (listComentarios == null) {
			listComentarios = new JList<Comentario>(modelComentarios);
		}
		return listComentarios;
	}
}
