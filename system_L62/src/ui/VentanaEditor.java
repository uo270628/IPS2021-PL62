package ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Articulo.ArticleState;
import business.Editor;
import persistence.DataBaseArticle;

public class VentanaEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelCard;
	private JPanel panelGestor;
	private JComboBox<Articulo> comboBoxArticulosConEditor;
	private JButton btnOrdenarArticulos;
	private JTextField textFieldFiltrarPorTitulo;
	private JTextField textFieldFiltrarPorAutor;
	private JButton btnFiltrarPorTitulo;
	private JButton btnFiltrarPorAutor;
	private JPanel panelRegistro;
	private JLabel lblInsertarID;
	private JTextField textFieldIdEditor;
	private JButton btnAccederEditor;
	
	private Editor editor;
	private JLabel lblEditor;
	private JPanel panelANuevos;
	private JLabel lblArticulosNuevos;
	private JComboBox<Articulo> comboBoxArticulosEnviados;
	private JLabel lblAutor;
	private JTextField textFieldResumen;
	private JLabel lblResumen;
	private JButton btnVisto;
	private JButton btnElegirRevisores;
	
	private List<Articulo> articulos;
	private JButton btnVerNuevosArticulos;
	private JButton btnAtras;

	/**
	 * Create the frame.
	 */
	public VentanaEditor() {
		articulos = DataBaseArticle.loadArticles();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 317);
		panelCard = new JPanel();
		panelCard.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelCard);
		panelCard.setLayout(new CardLayout(0, 0));
		
		panelCard.add(getPanelRegistro(), "registro");
		panelCard.add(getPanelGestor(), "gestion");
		panelCard.add(getPanelANuevos(), "a_nuevos");
		editor = null;
	}

	private JPanel getPanelGestor() {
		if (panelGestor == null) {
			panelGestor = new JPanel();
			panelGestor.setLayout(null);
			panelGestor.add(getComboBoxArticulosConEditor());
			panelGestor.add(getBtnOrdenarArticulos());
			panelGestor.add(getTextFieldFiltrarPorTitulo());
			panelGestor.add(getTextFieldFiltrarPorAutor());
			panelGestor.add(getBtnFiltrarPorTitulo());
			panelGestor.add(getBtnFiltrarPorAutor());
			panelGestor.add(getLblEditor());
			panelGestor.add(getBtnVerNuevosArticulos());
		}
		return panelGestor;
	}
	private JComboBox<Articulo> getComboBoxArticulosConEditor() {
		if (comboBoxArticulosConEditor == null) {
			comboBoxArticulosConEditor = new JComboBox<Articulo>();
			ArrayList<Articulo> articulo = new ArrayList<Articulo>();
			for(Articulo art:articulos) {
				if(art.getState().equals(ArticleState.WITH_EDITOR.toString())) {
					articulo.add(art);
				}
			}
			comboBoxArticulosConEditor.setModel(new DefaultComboBoxModel<Articulo>(articulo.toArray( new Articulo[articulo.size()])));
			comboBoxArticulosConEditor.setBounds(41, 64, 350, 22);
		}
		return comboBoxArticulosConEditor;
	}

	private JButton getBtnOrdenarArticulos() {
		if (btnOrdenarArticulos == null) {
			btnOrdenarArticulos = new JButton("Ordenar");
			btnOrdenarArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.print("Método no implementado. ORDER BY ATRIBUTO");
				}
			});
			btnOrdenarArticulos.setBounds(279, 113, 89, 23);
		}
		return btnOrdenarArticulos;
	}
	private JTextField getTextFieldFiltrarPorTitulo() {
		if (textFieldFiltrarPorTitulo == null) {
			textFieldFiltrarPorTitulo = new JTextField();
			textFieldFiltrarPorTitulo.setBounds(41, 114, 96, 20);
			textFieldFiltrarPorTitulo.setColumns(10);
		}
		return textFieldFiltrarPorTitulo;
	}
	private JTextField getTextFieldFiltrarPorAutor() {
		if (textFieldFiltrarPorAutor == null) {
			textFieldFiltrarPorAutor = new JTextField();
			textFieldFiltrarPorAutor.setBounds(41, 145, 96, 20);
			textFieldFiltrarPorAutor.setColumns(10);
		}
		return textFieldFiltrarPorAutor;
	}
	private JButton getBtnFiltrarPorTitulo() {
		if (btnFiltrarPorTitulo == null) {
			btnFiltrarPorTitulo = new JButton("Filtrar");
			btnFiltrarPorTitulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String titulo = getTextFieldFiltrarPorTitulo().getText();
					ArrayList<Articulo> articulo = new ArrayList<Articulo>();
					for(Articulo art:articulos) {
						if(art.getTitle().equals(titulo)) {
							articulo.add(art);
						}
					}
					comboBoxArticulosConEditor.setModel(new DefaultComboBoxModel<Articulo>(articulo.toArray(new Articulo[articulo.size()])))
				;
				}
			});
			btnFiltrarPorTitulo.setBounds(147, 113, 89, 23);
		}
		return btnFiltrarPorTitulo;
	}
	private JButton getBtnFiltrarPorAutor() {
		if (btnFiltrarPorAutor == null) {
			btnFiltrarPorAutor = new JButton("Filtrar");
			btnFiltrarPorAutor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String autor = getTextFieldFiltrarPorAutor().getText();
					
					ArrayList<Articulo> articulo = new ArrayList<Articulo>();
					for(Articulo art:articulos) {
						if(art.getAuthor().getName().equals(autor) && !art.getState().equals(ArticleState.SENT.toString())) {
							articulo.add(art);
						}
					}
					comboBoxArticulosConEditor.setModel(new DefaultComboBoxModel<Articulo>(articulo.toArray(new Articulo[articulo.size()])));
				}
			});
			btnFiltrarPorAutor.setBounds(147, 144, 89, 23);
		}
		return btnFiltrarPorAutor;
	}
	private JPanel getPanelRegistro() {
		if (panelRegistro == null) {
			panelRegistro = new JPanel();
			panelRegistro.setLayout(null);
			panelRegistro.add(getLblInsertarID());
			panelRegistro.add(getTextFieldIdEditor());
			panelRegistro.add(getBtnAccederEditor());
		}
		return panelRegistro;
	}
	private JLabel getLblInsertarID() {
		if (lblInsertarID == null) {
			lblInsertarID = new JLabel("Introduce tu Identificador");
			lblInsertarID.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 18));
			lblInsertarID.setHorizontalAlignment(SwingConstants.CENTER);
			lblInsertarID.setBounds(63, 30, 301, 46);
		}
		return lblInsertarID;
	}
	private JTextField getTextFieldIdEditor() {
		if (textFieldIdEditor == null) {
			textFieldIdEditor = new JTextField();
			textFieldIdEditor.setBounds(136, 135, 168, 20);
			textFieldIdEditor.setColumns(10);
		}
		return textFieldIdEditor;
	}
	private JButton getBtnAccederEditor() {
		if (btnAccederEditor == null) {
			btnAccederEditor = new JButton("Acceder");
			btnAccederEditor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editor = new Editor(getTextFieldIdEditor().getText());
					CardLayout cl = (CardLayout)panelCard.getLayout();
					cl.show(panelCard, "gestion");
					getLblEditor().setText(getLblEditor().getText() + editor.getName());
				}
			});
			btnAccederEditor.setBounds(346, 234, 89, 23);
		}
		return btnAccederEditor;
	}
	private JLabel getLblEditor() {
		if (lblEditor == null) {
			lblEditor = new JLabel("Editor: ");
			lblEditor.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 11));
			lblEditor.setBounds(10, 11, 127, 22);
		}
		return lblEditor;
	}
	private JPanel getPanelANuevos() {
		if (panelANuevos == null) {
			panelANuevos = new JPanel();
			panelANuevos.setLayout(null);
			panelANuevos.add(getLblArticulosNuevos());
			panelANuevos.add(getComboBoxArticulosEnviados());
			panelANuevos.add(getLblAutor());
			panelANuevos.add(getTextFieldResumen());
			panelANuevos.add(getLblResumen());
			panelANuevos.add(getBtnVisto());
			panelANuevos.add(getBtnElegirRevisores());
			panelANuevos.add(getBtnAtras());
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
			ArrayList<Articulo> articulo = new ArrayList<Articulo>();
			for(Articulo art:articulos) {
				if(art.getState().equals(ArticleState.SENT.toString())) {
					articulo.add(art);
				}
			}
			comboBoxArticulosEnviados.setModel(new DefaultComboBoxModel<Articulo>(articulo.toArray(new Articulo[articulo.size()]) ));
			comboBoxArticulosEnviados.setBounds(10, 47, 233, 25);
		}
		return comboBoxArticulosEnviados;
	}
	private JLabel getLblAutor() {
		if (lblAutor == null) {
			lblAutor = new JLabel("Autor: ");
			lblAutor.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 14));
			lblAutor.setBounds(253, 47, 182, 25);
		}
		return lblAutor;
	}
	private JTextField getTextFieldResumen() {
		if (textFieldResumen == null) {
			textFieldResumen = new JTextField();
			textFieldResumen.setEditable(false);
			textFieldResumen.setBounds(10, 129, 233, 78);
			textFieldResumen.setColumns(10);
		}
		return textFieldResumen;
	}
	private JLabel getLblResumen() {
		if (lblResumen == null) {
			lblResumen = new JLabel("Resumen");
			lblResumen.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 14));
			lblResumen.setBounds(10, 83, 118, 35);
		}
		return lblResumen;
	}
	private JButton getBtnVisto() {
		if (btnVisto == null) {
			btnVisto = new JButton("Visto");
			btnVisto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Articulo aux = (Articulo) getComboBoxArticulosEnviados().getSelectedItem();
					
					aux.setState(ArticleState.WITH_EDITOR);
					
					ArrayList<Articulo> articulo = new ArrayList<Articulo>();
					for(Articulo art:articulos) {
						if(art.getState().equals(ArticleState.SENT.toString())) {
							articulo.add(art);
						}
					}
					comboBoxArticulosEnviados.setModel(new DefaultComboBoxModel<Articulo>(new Articulo[articulo.size()]));
					
					
					
					articulo = new ArrayList<Articulo>();
					
					for(Articulo art:articulos) {
						if(art.getState().equals(ArticleState.WITH_EDITOR.toString())) {
							articulo.add(art);
						}
					}
					comboBoxArticulosConEditor.setModel(new DefaultComboBoxModel<Articulo>(articulo.toArray( new Articulo[articulo.size()])));
					
					
				}
			});
			btnVisto.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 12));
			btnVisto.setBounds(346, 184, 89, 23);
		}
		return btnVisto;
	}
	private JButton getBtnElegirRevisores() {
		if (btnElegirRevisores == null) {
			btnElegirRevisores = new JButton("Elegir revisores");
			btnElegirRevisores.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chooseRevisores();
				}
			});
			btnElegirRevisores.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 12));
			btnElegirRevisores.setBounds(297, 234, 138, 23);
		}
		return btnElegirRevisores;
	}
	private JButton getBtnVerNuevosArticulos() {
		if (btnVerNuevosArticulos == null) {
			btnVerNuevosArticulos = new JButton("Ver articulos nuevos");
			btnVerNuevosArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout cl = (CardLayout)panelCard.getLayout();
					cl.show(panelCard, "a_nuevos");
					getLblEditor().setText(getLblEditor().getText() + editor.getName());
				}
			});
			btnVerNuevosArticulos.setBounds(296, 234, 139, 23);
		}
		return btnVerNuevosArticulos;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CardLayout cl = (CardLayout)panelCard.getLayout();
					cl.show(panelCard, "gestion");
					getLblEditor().setText(getLblEditor().getText() + editor.getName());
				}
			});
			btnAtras.setBounds(10, 235, 89, 23);
		}
		return btnAtras;
	}
	
	private void chooseRevisores() {
		Articulo a = (Articulo) getComboBoxArticulosEnviados().getSelectedItem();
		InterfazElegirRevisores interfaz = new InterfazElegirRevisores(a);
		interfaz.setVisible(true);
	}
}
