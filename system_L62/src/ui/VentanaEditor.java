package ui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Articulo.ArticleState;
import business.Comentario;
import persistence.DataBaseArticle;

public class VentanaEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelCard;
	private JPanel panelGestor;
	private JComboBox<Articulo> comboBoxArticulosConEditor;
	private JTextField textFieldFiltrarPorTitulo;
	private JTextField textFieldFiltrarPorAutor;
	private JButton btnFiltrarPorTitulo;
	private JButton btnFiltrarPorAutor;

	private JPanel panelANuevos;
	private JLabel lblArticulosNuevos;
	private JComboBox<Articulo> comboBoxArticulosEnviados;
	private JTextField textFieldResumen;
	private JLabel lblResumen;

	private List<Articulo> articulos;
	private JButton btnVerNuevosArticulos;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private JPanel panel;
	private JButton btnNewButton;
	private JLabel lblNewLabel_3;
	private JTextField textField_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JLabel lblNewLabel_4;
	private JTextField textField_2;
	private JLabel lblNewLabel_5;
	private JTextField textField_3;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_8;
	private JTextField textField_5;
	private JButton btnNewButton_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VentanaEditor window = new VentanaEditor();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public VentanaEditor() {
		articulos = DataBaseArticle.loadArticles();
		cambiarArticulosAWithEditor();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 653);
		panelCard = new JPanel();
		panelCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelCard);
		panelCard.setLayout(new CardLayout(0, 0));

		panelCard.add(getPanelANuevos(), "a_nuevos");
		panelCard.add(getPanelGestor(), "gestion");
		panelCard.add(getPanel(), "aceptarCC");
	}

	private void cambiarArticulosAWithEditor() {		
		for(Articulo a: articulos) {
			a.setState(ArticleState.WITH_EDITOR);
			DataBaseArticle.publishArticleState(a);
		}
	}
	
	
	
	private JPanel getPanelGestor() {
		if (panelGestor == null) {
			panelGestor = new JPanel();
			panelGestor.setLayout(null);
			panelGestor.add(getComboBoxArticulosConEditor());
			panelGestor.add(getTextFieldFiltrarPorTitulo());
			panelGestor.add(getTextFieldFiltrarPorAutor());
			panelGestor.add(getBtnFiltrarPorTitulo());
			panelGestor.add(getBtnFiltrarPorAutor());
			panelGestor.add(getBtnVerNuevosArticulos());

			JLabel lblNewLabel = new JLabel("T\u00EDtulo");
			lblNewLabel.setBounds(10, 97, 96, 14);
			panelGestor.add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("Autor");
			lblNewLabel_1.setBounds(10, 156, 96, 14);
			panelGestor.add(lblNewLabel_1);
			panelGestor.add(getTextField());
			panelGestor.add(getLblNewLabel_2());
			panelGestor.add(getBtnNewButton());
		}
		return panelGestor;
	}

	private JComboBox<Articulo> getComboBoxArticulosConEditor() {
		if (comboBoxArticulosConEditor == null) {
			comboBoxArticulosConEditor = new JComboBox<Articulo>();
			comboBoxArticulosConEditor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textField.setText(((Articulo) getComboBoxArticulosConEditor().getSelectedItem()).getResumen());
				}
			});
			comboBoxArticulosConEditor
			.setModel(new DefaultComboBoxModel<Articulo>(articulos.toArray(new Articulo[articulos.size()])));
			comboBoxArticulosConEditor.setBounds(10, 64, 349, 22);
		}
		return comboBoxArticulosConEditor;
	}

	private JTextField getTextFieldFiltrarPorTitulo() {
		if (textFieldFiltrarPorTitulo == null) {
			textFieldFiltrarPorTitulo = new JTextField();
			textFieldFiltrarPorTitulo.setBounds(10, 119, 96, 20);
			textFieldFiltrarPorTitulo.setColumns(10);
		}
		return textFieldFiltrarPorTitulo;
	}

	private JTextField getTextFieldFiltrarPorAutor() {
		if (textFieldFiltrarPorAutor == null) {
			textFieldFiltrarPorAutor = new JTextField();
			textFieldFiltrarPorAutor.setBounds(10, 181, 96, 20);
			textFieldFiltrarPorAutor.setColumns(10);
		}
		return textFieldFiltrarPorAutor;
	}

	private JButton getBtnFiltrarPorTitulo() {
		if (btnFiltrarPorTitulo == null) {
			btnFiltrarPorTitulo = new JButton("Filtrar");
			btnFiltrarPorTitulo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String titulo = getTextFieldFiltrarPorTitulo().getText();
					ArrayList<Articulo> articulo = new ArrayList<Articulo>();
					for (Articulo art : articulos) {
						if (art.getTitle().equals(titulo)) {
							articulo.add(art);
						}
					}
					comboBoxArticulosConEditor.setModel(
							new DefaultComboBoxModel<Articulo>(articulo.toArray(new Articulo[articulo.size()])));
				}
			});
			btnFiltrarPorTitulo.setBounds(116, 118, 89, 23);
		}
		return btnFiltrarPorTitulo;
	}

	private JButton getBtnFiltrarPorAutor() {
		if (btnFiltrarPorAutor == null) {
			btnFiltrarPorAutor = new JButton("Filtrar");
			btnFiltrarPorAutor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String autor = getTextFieldFiltrarPorAutor().getText();

					ArrayList<Articulo> articulo = new ArrayList<Articulo>();
					for (Articulo art : articulos) {
						if (art.getAuthor().getName().equals(autor)
								&& !art.getState().equals(ArticleState.SENT.toString())) {
							articulo.add(art);
						}
					}
					comboBoxArticulosConEditor.setModel(
							new DefaultComboBoxModel<Articulo>(articulo.toArray(new Articulo[articulo.size()])));
				}
			});
			btnFiltrarPorAutor.setBounds(116, 180, 89, 23);
		}
		return btnFiltrarPorAutor;
	}

	private JPanel getPanelANuevos() {
		if (panelANuevos == null) {
			panelANuevos = new JPanel();
			panelANuevos.setLayout(null);
			panelANuevos.add(getLblArticulosNuevos());
			panelANuevos.add(getComboBoxArticulosEnviados());
			panelANuevos.add(getTextFieldResumen());
			panelANuevos.add(getLblResumen());

			JButton btnNewButton_5 = new JButton("Ver todos");
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout cl = (CardLayout) panelCard.getLayout();
					cl.show(panelCard, "gestion");
				}
			});
			btnNewButton_5.setBounds(409, 349, 89, 23);
			panelANuevos.add(btnNewButton_5);
			panelANuevos.add(getLblNewLabel_4());
			panelANuevos.add(getTextField_2());
			panelANuevos.add(getLblNewLabel_5());
			panelANuevos.add(getTextField_3());
			panelANuevos.add(getLblNewLabel_6());
			panelANuevos.add(getLblNewLabel_8());
			panelANuevos.add(getTextField_5());
			panelANuevos.add(getBtnNewButton_6());
		}
		return panelANuevos;
	}

	private JLabel getLblArticulosNuevos() {
		if (lblArticulosNuevos == null) {
			lblArticulosNuevos = new JLabel("Art\u00EDculos nuevos");
			lblArticulosNuevos.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 14));
			lblArticulosNuevos.setBounds(10, 11, 125, 25);
		}
		return lblArticulosNuevos;
	}

	private JComboBox<Articulo> getComboBoxArticulosEnviados() {
		if (comboBoxArticulosEnviados == null) {
			comboBoxArticulosEnviados = new JComboBox<Articulo>();
			comboBoxArticulosEnviados.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					textFieldResumen
					.setText(((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getResumen());
					textField_2.setText(
							((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getAuthors().toString());
					textField_3.setText(
							((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getPresentationCard());
					textField_5.setText(
							((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getComentarios().toString());
					lblNewLabel_6.setText(getLblNewLabel_6().getText()
							+ ((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getSrcFile());
				}
			});
			ArrayList<Articulo> articulo = new ArrayList<Articulo>();
			for (Articulo art : articulos) {
				if (art.getState().equals(ArticleState.WITH_EDITOR.toString())) {
					articulo.add(art);
				}
			}
			comboBoxArticulosEnviados
			.setModel(new DefaultComboBoxModel<Articulo>(articulo.toArray(new Articulo[articulo.size()])));
			comboBoxArticulosEnviados.setBounds(10, 47, 452, 25);
		}
		return comboBoxArticulosEnviados;
	}

	private JTextField getTextFieldResumen() {
		if (textFieldResumen == null) {
			textFieldResumen = new JTextField();
			textFieldResumen.setEditable(false);
			textFieldResumen.setBounds(272, 108, 226, 148);
			textFieldResumen.setColumns(10);
			textFieldResumen.setText(((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getResumen());
		}
		return textFieldResumen;
	}

	private JLabel getLblResumen() {
		if (lblResumen == null) {
			lblResumen = new JLabel("Resumen");
			lblResumen.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 14));
			lblResumen.setBounds(272, 83, 118, 14);
		}
		return lblResumen;
	}

	private JButton getBtnVerNuevosArticulos() {
		if (btnVerNuevosArticulos == null) {
			btnVerNuevosArticulos = new JButton("Ver articulos nuevos");
			btnVerNuevosArticulos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CardLayout cl = (CardLayout) panelCard.getLayout();
					cl.show(panelCard, "a_nuevos");

				}
			});
			btnVerNuevosArticulos.setBounds(10, 349, 139, 23);
		}
		return btnVerNuevosArticulos;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(246, 119, 216, 83);
			textField.setColumns(10);
			textField.setText(((Articulo) getComboBoxArticulosConEditor().getSelectedItem()).getResumen());
		}
		return textField;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Resumen:");
			lblNewLabel_2.setBounds(246, 97, 89, 14);

		}
		return lblNewLabel_2;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblNewLabel_3());
			panel.add(getTextField_1());
			panel.add(getBtnNewButton_1());
			panel.add(getBtnNewButton_2());
			panel.add(getBtnNewButton_3());
			panel.add(getBtnNewButton_4());
		}
		return panel;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Detalles");
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CardLayout cl = (CardLayout) panelCard.getLayout();
					cl.show(panelCard, "aceptarCC");
					lblNewLabel_3.setText(((Articulo) getComboBoxArticulosConEditor().getSelectedItem()).getResumen());
					textField_1.setText(mostrarComentarios());
				}
			});
			btnNewButton.setBounds(369, 64, 89, 23);
		}
		return btnNewButton;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel();
			lblNewLabel_3.setBounds(10, 29, 452, 30);
			lblNewLabel_3.setText(((Articulo) getComboBoxArticulosConEditor().getSelectedItem()).getResumen());
		}
		return lblNewLabel_3;
	}

	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(10, 70, 452, 122);
			textField_1.setColumns(10);
			textField_1.setText(mostrarComentarios());
		}
		return textField_1;
	}

	private String mostrarComentarios() {
		Articulo art = (Articulo) getComboBoxArticulosConEditor().getSelectedItem();
		String tmp = "";
		for (Comentario c : art.getComentarios()) {
			tmp += "-" + c.getIdRevisor() + ": " + c.getTexto() + "\n";
		}
		return tmp;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Aceptar");
			btnNewButton_1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "El articulo ha sido aceptado");

					((Articulo) getComboBoxArticulosConEditor().getSelectedItem()).setState(ArticleState.ACCEPTED);
					Articulo a = ((Articulo) getComboBoxArticulosConEditor().getSelectedItem());
					DataBaseArticle.updateArticle(a);

					CardLayout cl = (CardLayout) panelCard.getLayout();
					cl.show(panelCard, "gestion");
				}
			});
			btnNewButton_1.setBounds(10, 203, 89, 23);
		}
		return btnNewButton_1;
	}

	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("Cambios menores");
			btnNewButton_2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "El articulo ha sido aceptado con cambios menores");

					Articulo a = ((Articulo) getComboBoxArticulosConEditor().getSelectedItem());

					a.setState(ArticleState.ACCEPTED_WITH_MINOR_CHANGES);

					DataBaseArticle.updateArticle(a);

					CardLayout cl = (CardLayout) panelCard.getLayout();
					cl.show(panelCard, "gestion");
				}
			});
			btnNewButton_2.setBounds(109, 203, 125, 23);
		}
		return btnNewButton_2;
	}

	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("Cambios mayores");
			btnNewButton_3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "El articulo ha sido aceptado con cambios mayores");

					((Articulo) getComboBoxArticulosConEditor().getSelectedItem())
					.setState(ArticleState.ACCEPTED_WITH_GREATER_CHANGES);
					Articulo a = ((Articulo) getComboBoxArticulosConEditor().getSelectedItem());
					DataBaseArticle.updateArticle(a);

					CardLayout cl = (CardLayout) panelCard.getLayout();
					cl.show(panelCard, "gestion");
				}
			});
			btnNewButton_3.setBounds(244, 203, 119, 23);
		}
		return btnNewButton_3;
	}

	private JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("Rechazar");
			btnNewButton_4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "El articulo ha sido rechazado");

					((Articulo) getComboBoxArticulosConEditor().getSelectedItem()).setState(ArticleState.REJECTED);
					Articulo a = ((Articulo) getComboBoxArticulosConEditor().getSelectedItem());
					DataBaseArticle.updateArticle(a);

					CardLayout cl = (CardLayout) panelCard.getLayout();
					cl.show(panelCard, "gestion");
				}
			});
			btnNewButton_4.setBounds(373, 203, 89, 23);
		}
		return btnNewButton_4;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Otros autores");
			lblNewLabel_4.setBounds(10, 83, 89, 14);
		}
		return lblNewLabel_4;
	}

	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setBounds(10, 108, 226, 25);
			textField_2.setColumns(10);
			textField_2.setText(((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getAuthors().toString());
		}
		return textField_2;
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Carta de presentacion");
			lblNewLabel_5.setBounds(10, 144, 125, 14);
		}
		return lblNewLabel_5;
	}

	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setEditable(false);
			textField_3.setBounds(10, 169, 226, 87);
			textField_3.setColumns(10);
			textField_3.setText(((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getPresentationCard());
		}
		return textField_3;
	}

	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("Fichero fuente: ");
			lblNewLabel_6.setBounds(272, 267, 226, 14);
			lblNewLabel_6.setText(getLblNewLabel_6().getText()
					+ ((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getSrcFile());
		}
		return lblNewLabel_6;
	}

	private JLabel getLblNewLabel_8() {
		if (lblNewLabel_8 == null) {
			lblNewLabel_8 = new JLabel("Comentarios");
			lblNewLabel_8.setBounds(10, 267, 125, 14);
		}
		return lblNewLabel_8;
	}

	private JTextField getTextField_5() {
		if (textField_5 == null) {
			textField_5 = new JTextField();
			textField_5.setEditable(false);
			textField_5.setBounds(10, 292, 226, 80);
			textField_5.setColumns(10);
			textField_5
			.setText(((Articulo) getComboBoxArticulosEnviados().getSelectedItem()).getComentarios().toString());
		}
		return textField_5;
	}

	private JButton getBtnNewButton_6() {
		if (btnNewButton_6 == null) {
			btnNewButton_6 = new JButton("Gestion de revisores");
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AsignarRevisoresRecomendados dialog = new AsignarRevisoresRecomendados(
							((Articulo) getComboBoxArticulosEnviados().getSelectedItem()));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			});
			btnNewButton_6.setBounds(268, 349, 131, 23);
		}
		return btnNewButton_6;
	}
	
	
}

