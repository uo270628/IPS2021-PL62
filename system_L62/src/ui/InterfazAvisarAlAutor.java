package ui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Autor;
import business.Articulo.ArticleState;
import persistence.DataBaseArticle;
import persistence.DataBaseRevisor;
import business.Tema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class InterfazAvisarAlAutor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTema;
	private JLabel lblTemaArt;
	private Articulo articulo;
	private JButton btnAceptar;
	private JButton btnRechazar;
	private JButton btnInformar;
	private JLabel lblNewLabel;
	private JLabel lblRevisoresDisponibles;

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
			//Articulo a = new Articulo("a", "e", new Autor("Pedro"), list, "a", list2,list2, new Tema( "Biologia"));

			DataBaseArticle.uploadArticle(a);
			InterfazAvisarAlAutor dialog = new InterfazAvisarAlAutor(a);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazAvisarAlAutor(Articulo articulo) {
		this.articulo=articulo;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblTema());
		contentPanel.add(getLblTemaArt());
		contentPanel.add(getBtnAceptar());
		contentPanel.add(getBtnRechazar());
		contentPanel.add(getBtnInformar());
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getLblRevisoresDisponibles());
	}
	private JLabel getLblTema() {
		if (lblTema == null) {
			lblTema = new JLabel("TemaDelArticulo:");
			lblTema.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTema.setBounds(32, 28, 157, 38);
		}
		return lblTema;
	}
	private JLabel getLblTemaArt() {
		if (lblTemaArt == null) {
			lblTemaArt = new JLabel("");
			lblTemaArt.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTemaArt.setBounds(243, 35, 201, 25);
			lblTemaArt.setText(articulo.getTema().getNombre());
		}
		return lblTemaArt;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setMnemonic('a');
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aceptarEstado();
				}
			});
			btnAceptar.setBackground(Color.GREEN);
			btnAceptar.setBounds(44, 159, 89, 23);
		}
		return btnAceptar;
	}
	protected void aceptarEstado() {
		getBtnAceptar().setEnabled(false);
		getBtnRechazar().setEnabled(false);
		getBtnInformar().setEnabled(true);
		if(Integer.parseInt(getLblRevisoresDisponibles().getText())>0)
		articulo.setState(ArticleState.ACCEPTED);
		else {
			JOptionPane.showMessageDialog(null,
					"Como no hay suficientes revisores para este articulo sera rechazado automaticamente", "Revisores",
					JOptionPane.INFORMATION_MESSAGE);
			articulo.setState(ArticleState.REJECTED);
		}
	}

	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.setMnemonic('r');
			btnRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rechazarArticulo();
					
				}
			});
			btnRechazar.setBackground(Color.RED);
			btnRechazar.setBounds(219, 159, 89, 23);
		}
		return btnRechazar;
	}

	protected void rechazarArticulo() {
		getBtnAceptar().setVisible(false);
		getBtnRechazar().setVisible(false);
		getBtnInformar().setEnabled(true);
		articulo.setState(ArticleState.REJECTED);
		
		
	}
	private JButton getBtnInformar() {
		if (btnInformar == null) {
			btnInformar = new JButton("Informar");
			btnInformar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarArticulo();
					dispose();
				}
			});
			btnInformar.setMnemonic('i');
			btnInformar.setEnabled(false);
			btnInformar.setBounds(305, 213, 107, 37);
		}
		return btnInformar;
	}
	protected void actualizarArticulo() {
		DataBaseArticle.updateArticle(articulo);
		
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Numero de revisores disponibles para este tema");
			lblNewLabel.setBounds(32, 89, 333, 25);
		}
		return lblNewLabel;
	}
	private JLabel getLblRevisoresDisponibles() {
		if (lblRevisoresDisponibles == null) {
			lblRevisoresDisponibles = new JLabel("");
			lblRevisoresDisponibles.setBounds(332, 92, 22, 19);
			lblRevisoresDisponibles.setText(getRevisoresDisponibles()+"");
		}
		return lblRevisoresDisponibles;
	}

	private int getRevisoresDisponibles() {
		DataBaseRevisor db = new DataBaseRevisor();
		return db.getRevisores(articulo.getTema().getNombre()).size();
	}
}
