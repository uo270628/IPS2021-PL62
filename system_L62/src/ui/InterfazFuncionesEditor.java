package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Articulo.ArticleState;
import business.Articulo.ArticleVersion;
import persistence.DataBaseManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;

	/**
	 * Create the dialog.
	 */
	public InterfazFuncionesEditor(Articulo articulo) {
		this.articulo = articulo;
		setResizable(false);
		setBounds(100, 100, 605, 384);
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
		contentPanel.add(getBtnNewButton_2());
		contentPanel.add(getBtnNewButton_3());
		setLocationRelativeTo(null);

	}

	private JLabel getLblArticulo() {
		if (lblArticulo == null) {
			lblArticulo = new JLabel("Articulo: " + articulo.getTitle());
			lblArticulo.setHorizontalAlignment(SwingConstants.CENTER);
			lblArticulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblArticulo.setBounds(39, 21, 493, 26);
		}
		return lblArticulo;
	}

	private JButton getBtnValidarArticulo() {
		if (btnValidarArticulo == null) {
			btnValidarArticulo = new JButton("Validar articulo");
			btnValidarArticulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (articulo.getStateEnum() == ArticleState.WITH_EDITOR ) {
						InterfazAvisarAlAutor i = new InterfazAvisarAlAutor(articulo);
						i.setVisible(true);
					}
					else {
						if(articulo.getStateEnum()==ArticleState.REJECTED)
						JOptionPane.showMessageDialog(null, "El articulo ya ha sido rechazado",
								"Comentarios", JOptionPane.ERROR_MESSAGE);
						else {
							JOptionPane.showMessageDialog(null, "El articulo ya fue aceptado anteriormente",
									"Comentarios", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			btnValidarArticulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnValidarArticulo.setBounds(10, 133, 183, 34);
		}
		return btnValidarArticulo;
	}

	private JButton getBtnDecisionFinal() {
		if (btnDecisionFinal == null) {
			btnDecisionFinal = new JButton("Decision final");
			btnDecisionFinal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comprobarSiEstaParaEnviar() && !articulo.getComentarios().isEmpty()) {
						InterfazDecisionFinal i = new InterfazDecisionFinal(articulo);
						i.setVisible(true);}
					else {
						if(articulo.getStateEnum()==ArticleState.REJECTED)
							JOptionPane.showMessageDialog(null, "El articulo ya ha sido rechazado", "Articulo",
									JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "El articulo no esta listo para publicar", "Articulo",
									JOptionPane.ERROR_MESSAGE);
					}
				}

				
			});
			btnDecisionFinal.setBounds(203, 133, 183, 34);
		}
		return btnDecisionFinal;
	}
	private boolean comprobarSiEstaParaEnviar() {
		if(articulo.getStateEnum()==ArticleState.IN_EDITION)
			return true;
		return false;
	}
	private JButton getBtnVerComentarios() {
		if (btnVerComentarios == null) {
			btnVerComentarios = new JButton("Ver Comentarios");
			btnVerComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(articulo.getStateEnum()==ArticleState.REJECTED)
						JOptionPane.showMessageDialog(null, "El articulo ya ha sido rechazado", "Articulo",
						JOptionPane.ERROR_MESSAGE);
					else if (!articulo.getComentarios().isEmpty()) {
						VerComentariosEditor i = new VerComentariosEditor(articulo.getId());
						i.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "Todavia no hay comentarios para este artículo",
								"Comentarios", JOptionPane.ERROR_MESSAGE);
				}
			});

			btnVerComentarios.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnVerComentarios.setBounds(203, 243, 183, 34);
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
					if(articulo.getStateEnum()==ArticleState.ACCEPTED&& articulo.getListOfRevisoresParaRevisar().isEmpty()&&!articulo.getVersion().equals(ArticleVersion.MINOR_CHANGES.toString())) {
						InterfazElegirRevisores i = new InterfazElegirRevisores(articulo);
						i.setVisible(true);}
					else 
					{
						if(articulo.getStateEnum()==ArticleState.REJECTED)
							JOptionPane.showMessageDialog(null, "El articulo ya ha sido rechazado", "Articulo",
							JOptionPane.ERROR_MESSAGE);
						else if(articulo.getStateEnum()==ArticleState.WITH_EDITOR)
							JOptionPane.showMessageDialog(null, "Hay que aceptar el articulo para poder asignar revisores", "Articulo",
									JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "Ya estan los revisores asignados", "Articulo",
									JOptionPane.ERROR_MESSAGE);}
				}
			});
			btnRevisores.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnRevisores.setBounds(10, 191, 183, 34);
		}
		return btnRevisores;
	}

	private JButton getBtnEnviarComentarios() {
		if (btnEnviarComentarios == null) {
			btnEnviarComentarios = new JButton("Enviar comentarios");
			btnEnviarComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(articulo.getStateEnum()== ArticleState.IN_REVISION && !articulo.getComentarios().isEmpty()) {
					InterfazEnviarComentariosAlAutor i = new InterfazEnviarComentariosAlAutor(articulo);
					i.setVisible(true);
				}
					else{
						if(articulo.getStateEnum()==ArticleState.REJECTED)
							JOptionPane.showMessageDialog(null, "El articulo ya ha sido rechazado", "Articulo",
							JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "No hay comentarios de los revisores todavia", "Articulo",
									JOptionPane.ERROR_MESSAGE);}
				}
					
			});
			btnEnviarComentarios.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnEnviarComentarios.setBounds(203, 191, 183, 34);
		}
		return btnEnviarComentarios;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Ver debate");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(articulo.getStateEnum()==ArticleState.REJECTED)
						JOptionPane.showMessageDialog(null, "El articulo ya ha sido rechazado", "Articulo",
						JOptionPane.ERROR_MESSAGE);
					else if (DataBaseManager.getDebate(articulo.getId()) != null) {
						UseDebate i = new UseDebate(articulo.getId(), "Editor");
						i.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "El debate no ha sido iniciado", "Debate",
								JOptionPane.ERROR_MESSAGE);
				}
			});
			btnNewButton.setBounds(396, 243, 183, 34);
		}
		return btnNewButton;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Aceptar cambios menores");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(articulo.getStateEnum()==ArticleState.ACCEPTED_WITH_MINOR_CHANGES) {
					InterfazAceptarConCambiosMenores i = new InterfazAceptarConCambiosMenores(articulo);
					i.setVisible(true);}
					else {
						if(articulo.getStateEnum()==ArticleState.REJECTED)
							JOptionPane.showMessageDialog(null, "El articulo ya ha sido rechazado", "Articulo",
							JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "El articulo no fue aceptado con cambios menores todavia", "Articulo",
									JOptionPane.ERROR_MESSAGE);}
					}
						
				
			});
			btnNewButton_1.setBounds(10, 243, 183, 34);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("Crear debate");
			btnNewButton_2.setBounds(396, 133, 183, 34);
		}
		return btnNewButton_2;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("Finalizar debate");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton_3.setBounds(396, 191, 183, 34);
		}
		return btnNewButton_3;
	}
}

