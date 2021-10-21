package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import business.Articulo;
import persistence.DataBaseManager;
import ui.listeners.ActionListenerCerrarVentana;

import javax.swing.JButton;

public class RevisorArticulosDisplay implements Ventana {

	private JFrame frame;
	private JComboBox<Articulo> comboBox;
	private JLabel lblTitulo;
	private JTextPane textPaneTitulo;
	private String revisor;
	Articulo articulo;
	private JTextPane textPaneAutor;
	private JLabel lblAutor;
	private JLabel lblEstado;
	private JTextPane textPaneEstado;
	private JButton btnVolver;
	
	/**
	 * Create the application.
	 */
	public RevisorArticulosDisplay( String revisor) {
		this.revisor = revisor;
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblTitulo());
		frame.getContentPane().add(getTextPaneTitulo());
		frame.getContentPane().add(getLblAutor());
		frame.getContentPane().add(getTextPaneAutor());
		frame.getContentPane().add(getLblEstado());
		frame.getContentPane().add(getTextPaneEstado());		
		frame.getContentPane().add(textPaneAutor);
		frame.getContentPane().add(lblEstado);
		frame.getContentPane().add(getComboBox());
		frame.getContentPane().add(getBtnVolver());
		frame.setVisible(true);
	}
	private JComboBox<Articulo> getComboBox() {
		if (comboBox == null) {
			List<Articulo> list = DataBaseManager.SelectAllArticlesForRevisor(revisor);
			if(!list.isEmpty()) {
				comboBox= new JComboBox<Articulo>();
				comboBox.setModel(new DefaultComboBoxModel<Articulo>(list.toArray(new Articulo[list.size()])));
				articulo = (Articulo) comboBox.getSelectedItem();
				setTexts();
				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						articulo = (Articulo) comboBox.getSelectedItem();
						setTexts();
					}
				});
			}
			else {
				comboBox= new JComboBox<Articulo>();
			}
			comboBox.setBounds(274, 228, 150, 22);
		}
		return comboBox;
	}
	private JLabel getLblAutor() {
		if (lblAutor == null) {
			lblAutor = new JLabel("Titulo:");
			lblAutor.setBounds(10, 11, 45, 20);
		}
		return lblAutor;
	}
	private JTextPane getTextPaneAutor() {
		if (textPaneAutor == null) {
			textPaneAutor = new JTextPane();
			textPaneAutor.setEditable(false);
			textPaneAutor.setBounds(66, 42, 358, 20);
		}
		return textPaneAutor;
	}
	private JLabel getLblEstado() {
		if (lblEstado == null) {
			lblEstado = new JLabel("Titulo:");
			lblEstado.setBounds(10, 79, 46, 14);
		}
		return lblEstado;
	}
	private JTextPane getTextPaneEstado() {
		if (textPaneEstado == null) {
			textPaneEstado = new JTextPane();
			textPaneEstado.setEditable(false);
			textPaneEstado.setBounds(66, 73, 358, 20);
		}
		return textPaneEstado;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Autor:");
			lblTitulo.setBounds(11, 42, 45, 20);
		}
		return lblTitulo;
	}
	private JTextPane getTextPaneTitulo() {
		if (textPaneTitulo == null) {
			textPaneTitulo = new JTextPane();
			textPaneTitulo.setEditable(false);
			textPaneTitulo.setBounds(66, 11, 358, 20);
		}
		return textPaneTitulo;
	}
	private void setTexts() {
		textPaneTitulo.setText(articulo.getTitle());
		textPaneAutor.setText(articulo.getAuthor().getName());
		textPaneEstado.setText(articulo.getState());
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListenerCerrarVentana(this) {
			});
			btnVolver.setBounds(11, 227, 89, 23);
		}
		return btnVolver;
	}

	@Override
	public void close() {
		frame.dispose();
		
	}
}
