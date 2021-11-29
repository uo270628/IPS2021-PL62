package ui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Comentario;
import business.Revisor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazListarComentariosRevisor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPaneComentarios;
	private JList<Comentario> listComentarios;
	private Articulo articulo;
	private DefaultListModel<Comentario> modelComentarios;
	private JButton btnCambiarComentario;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public InterfazListarComentariosRevisor(Articulo articulo,Revisor revisor) {
		this.articulo=articulo;
		modelComentarios = new DefaultListModel<>();
		modelComentarios.addAll(articulo.getComentariosDeUnRevisor(revisor));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPaneComentarios());
		contentPane.add(getBtnCambiarComentario());
		contentPane.add(getBtnNewButton());
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
	private JButton getBtnCambiarComentario() {
		if (btnCambiarComentario == null) {
			btnCambiarComentario = new JButton("Cambiar comentario");
			btnCambiarComentario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InterfazModificarComentariosRevision i= new InterfazModificarComentariosRevision(getListComentarios().getSelectedValue(),articulo);
					i.setVisible(true);
				}
			});
			btnCambiarComentario.setBounds(207, 214, 175, 23);
		}
		return btnCambiarComentario;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Volver");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton.setBounds(21, 214, 107, 23);
		}
		return btnNewButton;
	}
}
