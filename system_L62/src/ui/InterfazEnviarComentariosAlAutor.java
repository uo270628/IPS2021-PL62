package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Carta;
import business.Comentario;
import business.Tema;
import persistence.DataBaseArticle;
import persistence.DataBaseComentario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextPane;

public class InterfazEnviarComentariosAlAutor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnEnviarComentariosAlAutor;
	private JButton btnAceptarArticulo;
	private JButton btnRechazar;
	private JLabel lbLCarta;
	private JTextPane textPaneCarta;
	private Articulo articulo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Articulo a =new Articulo(new Tema( "Peces"),"25");
					a.addComentario(new Comentario(2, "hola", "12", "Aceptar", "fsfsdf", "Temporal"));
					InterfazEnviarComentariosAlAutor frame = new InterfazEnviarComentariosAlAutor(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public InterfazEnviarComentariosAlAutor(Articulo articulo) {
		setResizable(false);
		this.articulo = articulo;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnEnviarComentariosAlAutor());
		contentPane.add(getBtnAceptarArticulo());
		contentPane.add(getBtnRechazar());
		contentPane.add(getLbLCarta());
		contentPane.add(getTextPaneCarta());
		setLocationRelativeTo(null);
	}

	private JButton getBtnEnviarComentariosAlAutor() {
		if (btnEnviarComentariosAlAutor == null) {
			btnEnviarComentariosAlAutor = new JButton("Enviar");
			btnEnviarComentariosAlAutor.setEnabled(false);
			btnEnviarComentariosAlAutor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enviar();
					dispose();
				}
			});
			btnEnviarComentariosAlAutor.setBounds(258, 169, 123, 63);
		}
		return btnEnviarComentariosAlAutor;
	}
	
	protected void enviar() {
		String carta= getTextPaneCarta().getText();
		articulo.setCarta(new Carta(carta));
		DataBaseArticle.publishArticleState(articulo);
		DataBaseComentario.enviarCartaAlAutor(articulo);
		//DataBaseComentario.enviarComentariosAlAutor(articulo);
	}

	private JButton getBtnAceptarArticulo() {
		if (btnAceptarArticulo == null) {
			btnAceptarArticulo = new JButton("Aceptar ");
			btnAceptarArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getBtnEnviarComentariosAlAutor().setEnabled(true);
					getBtnAceptarArticulo().setVisible(false);
					getBtnRechazar().setVisible(false);
					articulo.aceptarArticulo();
				}
			});
			btnAceptarArticulo.setBounds(10, 200, 89, 23);
		}
		return btnAceptarArticulo;
	}
	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getBtnEnviarComentariosAlAutor().setEnabled(true);
					getBtnAceptarArticulo().setVisible(false);
					getBtnRechazar().setVisible(false);
					articulo.rechazarArticulo();
				}
			});
			btnRechazar.setBounds(130, 200, 89, 23);
		}
		return btnRechazar;
	}
	private JLabel getLbLCarta() {
		if (lbLCarta == null) {
			lbLCarta = new JLabel("Carta al autor:");
			lbLCarta.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbLCarta.setBounds(41, 18, 131, 14);
		}
		return lbLCarta;
	}
	private JTextPane getTextPaneCarta() {
		if (textPaneCarta == null) {
			textPaneCarta = new JTextPane();
			textPaneCarta.setBounds(41, 55, 222, 107);
		}
		return textPaneCarta;
	}
}
