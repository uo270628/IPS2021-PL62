package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;

import ui.listeners.ActionListenerCrearVentanaComentarioRevisor;
import ui.listeners.ActionListenerCrearVentanaRevisorArticulDisplay;
import ui.listeners.ActionListenerCrearVentanaRevisorValorarArticulos;

import javax.swing.JLabel;

public class RevisorInterface {

	private JFrame frmRevisor;
	private JButton btnComentario;
	private JTextPane nombreTextPane;
	private JButton btnVerArticulos;
	private JLabel lblNombre;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RevisorInterface window = new RevisorInterface();
					window.frmRevisor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RevisorInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRevisor = new JFrame();
		frmRevisor.setTitle("Revisor");
		frmRevisor.setResizable(false);
		frmRevisor.setBounds(100, 100, 450, 300);
		frmRevisor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevisor.getContentPane().setLayout(null);
		frmRevisor.getContentPane().add(getBtnComentario());	
		frmRevisor.getContentPane().add(getNombreTextPane());		
		frmRevisor.getContentPane().add(getBtnVerArticulos());
		frmRevisor.getContentPane().add(getLblNombre());
		frmRevisor.getContentPane().add(getBtnNewButton());
	}
	private JButton getBtnComentario() {
		if (btnComentario == null) {
			btnComentario = new JButton("Iniciar Comentario");
			btnComentario.addActionListener(new ActionListenerCrearVentanaComentarioRevisor(this) {
			});
			btnComentario.setBounds(228, 222, 191, 23);
		}
		return btnComentario;
	}
	private JTextPane getNombreTextPane() {
		if (nombreTextPane == null) {
			nombreTextPane = new JTextPane();
			nombreTextPane.setBounds(79, 11, 140, 20);
		}
		return nombreTextPane;
	}
	private JButton getBtnVerArticulos() {
		if (btnVerArticulos == null) {
			btnVerArticulos = new JButton("Ver Articulos Propios");
			btnVerArticulos.addActionListener(new ActionListenerCrearVentanaRevisorArticulDisplay(this) {
			});
			btnVerArticulos.setBounds(228, 183, 191, 23);
		}
		return btnVerArticulos;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 11, 59, 20);
		}
		return lblNombre;
	}
	public String getNombre() {
		return nombreTextPane.getText();
		
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Ver Articulos Propuestos");
			btnNewButton.addActionListener(new ActionListenerCrearVentanaRevisorValorarArticulos(this) {
			});
			btnNewButton.setBounds(228, 183, 191, 23);
			btnNewButton.setBounds(228, 149, 191, 23);
		}
		return btnNewButton;
	}
}
