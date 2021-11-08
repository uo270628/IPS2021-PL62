package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Autor;
import business.Tema;
import business.Articulo.ArticleState;
import persistence.DataBaseArticle;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class InterfazDecisionFinal extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnAceptar;
	private JButton btnRechazar;
	private Articulo articulo;
	private JButton btnSiguiente;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			List<Autor>list= new ArrayList<Autor>();
			List<String>list2 = new ArrayList<>();
			list.add(new Autor("Pepe"));
			list2.add("a");
			Articulo a = new Articulo("a", "e", new Autor("Pedro"), list, "a", list2,list2, new Tema( "Peces"));
			InterfazDecisionFinal dialog = new InterfazDecisionFinal(a);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazDecisionFinal(Articulo articulo) {
		this.articulo=articulo;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTexto = new JLabel("Desea aceptar finalmente el art\u00EDculo?");
			lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblTexto.setBounds(84, 28, 340, 39);
			contentPanel.add(lblTexto);
		}
		contentPanel.add(getBtnAceptar());
		contentPanel.add(getBtnRechazar());
		contentPanel.add(getBtnSiguiente());
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aceptarArticulo();
				}
			});
			btnAceptar.setBackground(Color.GREEN);
			btnAceptar.setMnemonic('a');
			btnAceptar.setBounds(74, 161, 89, 28);
		}
		return btnAceptar;
	}
	protected void aceptarArticulo() {
		articulo.setState(ArticleState.ACCEPTED);
		getBtnAceptar().setVisible(false);
		getBtnRechazar().setVisible(false);
		getBtnSiguiente().setEnabled(true);
		
	}

	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rechazarArticulo();
				}
			});
			btnRechazar.setBackground(Color.RED);
			btnRechazar.setMnemonic('r');
			btnRechazar.setBounds(246, 161, 104, 28);
		}
		return btnRechazar;
	}

	protected void rechazarArticulo() {
		articulo.setState(ArticleState.REJECTED);
		getBtnAceptar().setVisible(false);
		getBtnRechazar().setVisible(false);
		getBtnSiguiente().setEnabled(true);
		
		
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DataBaseArticle.updateArticle(articulo);
				}
			});
			btnSiguiente.setEnabled(false);
			btnSiguiente.setBounds(322, 222, 89, 28);
		}
		return btnSiguiente;
	}
}
