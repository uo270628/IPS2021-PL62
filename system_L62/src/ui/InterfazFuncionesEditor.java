package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazFuncionesEditor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblArticulo;
	private Articulo articulo;
	private JButton btnValidarArticulo;
	private JButton btnDecisionFinal;
	private JButton btnVerComentarios;
	private JButton btnRevisores;
	private JButton btnEnviarComentarios;
	private JButton btnNewButton;
	private JButton btnNewButton_1;


	/**
	 * Create the dialog.
	 */
	public InterfazFuncionesEditor(Articulo articulo) {
		this.articulo=articulo;
		setResizable(false);
		setBounds(100, 100, 567, 384);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblArticulo());
		contentPanel.add(getBtnValidarArticulo());
		contentPanel.add(getBtnDecisionFinal());
		contentPanel.add(getBtnVerComentarios());
		contentPanel.add(getBtnRevisores());
		contentPanel.add(getBtnEnviarComentarios());
		contentPanel.add(getBtnNewButton());
		contentPanel.add(getBtnNewButton_1());
	}
	private JLabel getLblArticulo() {
		if (lblArticulo == null) {
			lblArticulo = new JLabel("Articulo: "+articulo.getTitle());
			lblArticulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblArticulo.setBounds(59, 21, 312, 26);
		}
		return lblArticulo;
	}
	private JButton getBtnValidarArticulo() {
		if (btnValidarArticulo == null) {
			btnValidarArticulo = new JButton("Validar articulo");
			btnValidarArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					InterfazAvisarAlAutor i= new InterfazAvisarAlAutor(articulo);
					i.setVisible(true);
					
				}
			});
			btnValidarArticulo.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btnValidarArticulo.setBounds(47, 190, 118, 23);
		}
		return btnValidarArticulo;
	}
	private JButton getBtnDecisionFinal() {
		if (btnDecisionFinal == null) {
			btnDecisionFinal = new JButton("Decision final");
			btnDecisionFinal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InterfazDecisionFinal i= new InterfazDecisionFinal(articulo);
					
					i.setVisible(true);
				}
			});
			btnDecisionFinal.setBounds(223, 190, 148, 23);
		}
		return btnDecisionFinal;
	}
	private JButton getBtnVerComentarios() {
		if (btnVerComentarios == null) {
			btnVerComentarios = new JButton("Ver Comentarios");
			btnVerComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VerComentariosEditor i= new VerComentariosEditor(articulo.getId());
					i.setVisible(true);
					
				}
			});
			
			btnVerComentarios.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btnVerComentarios.setBounds(408, 190, 118, 23);
		}
		return btnVerComentarios;
	}
	
	public Articulo getArticulo() {
		return articulo;
	}
	private JButton getBtnRevisores() {
		if (btnRevisores == null) {
			btnRevisores = new JButton("Asignar Revisores");
			btnRevisores.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InterfazElegirRevisores i= new InterfazElegirRevisores(articulo);
					i.setVisible(true);
				}
			});
			btnRevisores.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btnRevisores.setBounds(31, 243, 148, 23);
		}
		return btnRevisores;
	}
	private JButton getBtnEnviarComentarios() {
		if (btnEnviarComentarios == null) {
			btnEnviarComentarios = new JButton("Enviar comentarios");
			btnEnviarComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InterfazEnviarComentariosAlAutor i= new InterfazEnviarComentariosAlAutor(articulo);
					i.setVisible(true);
				}
			});
			btnEnviarComentarios.setFont(new Font("Tahoma", Font.PLAIN, 8));
			btnEnviarComentarios.setBounds(233, 244, 138, 23);
		}
		return btnEnviarComentarios;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Ver debate");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UseDebate i = new UseDebate(articulo.getId(), "Editor");
					i.setVisible(true);
				}
			});
			btnNewButton.setBounds(411, 243, 100, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Aceptar cambios menores");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InterfazAceptarConCambiosMenores i = new InterfazAceptarConCambiosMenores(articulo);
					i.setVisible(true);
				}
			});
			btnNewButton_1.setBounds(59, 301, 187, 23);
		}
		return btnNewButton_1;
	}
}
