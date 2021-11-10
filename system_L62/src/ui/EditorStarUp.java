package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import business.Articulo;
import business.Autor;
import business.Comentario;
import business.Tema;
import persistence.DataBaseArticle;
import ui.listeners.ActionListenerVerComentarioEditor;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditorStarUp {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JTextField tfId;
	private JButton btnNewButton;
	private JButton btnRevisores;
	private JButton btn;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorStarUp window = new EditorStarUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditorStarUp() {
		crearArticuloBaseDeDatos();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getTfId());
		frame.getContentPane().add(getBtnNewButton());
		frame.getContentPane().add(getBtnRevisores());
		frame.getContentPane().add(getBtn());
		frame.getContentPane().add(getBtnNewButton_1());
		frame.getContentPane().add(getBtnNewButton_2());
		frame.setLocationRelativeTo(null);
		

	}
	

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Articulo:");
			lblNewLabel.setBounds(10, 11, 57, 14);
		}
		return lblNewLabel;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setBounds(99, 8, 118, 20);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Ver Comentarios");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btnNewButton.addActionListener(new ActionListenerVerComentarioEditor(this) {
			});
			btnNewButton.setBounds(10, 227, 118, 23);
		}
		return btnNewButton;
	}

	public String getArticulo() {
		return getTfId().getText();
	}
	private JButton getBtnRevisores() {
		if (btnRevisores == null) {
			btnRevisores = new JButton("Asignar Revisores");
			btnRevisores.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Articulo articulo =  DataBaseArticle.searchArticleConTodosLosAtributos(getArticulo());
					if(articulo==null)
						JOptionPane.showMessageDialog(null,
								"No se ha encontrado la id del articulo", "Articulo",
								JOptionPane.ERROR_MESSAGE);
					else {
					
					InterfazElegirRevisores i= new InterfazElegirRevisores(articulo);
					i.setVisible(true);
					}
				}
			});
			btnRevisores.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btnRevisores.setBounds(138, 227, 148, 23);
		}
		return btnRevisores;
	}
	private void crearArticuloBaseDeDatos() {
		List<Autor>list= new ArrayList<Autor>();
		List<String>list2 = new ArrayList<>();
		list.add(new Autor("Pepe"));
		list2.add("a");
		Articulo a = new Articulo("12", "e", new Autor("Pedro"), list, "a", list2,list2, new Tema( "Peces"));
		
		DataBaseArticle.uploadArticleTodosLosAtributos(a);
		
	}
	private JButton getBtn() {
		if (btn == null) {
			btn = new JButton("Validar articulo");
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Articulo articulo =  DataBaseArticle.searchArticleConTodosLosAtributos(getArticulo());
					if(articulo==null)
						JOptionPane.showMessageDialog(null,
								"No se ha encontrado la id del articulo", "Articulo",
								JOptionPane.ERROR_MESSAGE);
					else {
					
					InterfazAvisarAlAutor i= new InterfazAvisarAlAutor(articulo);
					i.setVisible(true);
					}
				}
			});
			btn.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btn.setBounds(10, 187, 118, 23);
		}
		return btn;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Decision final");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Articulo articulo =  DataBaseArticle.searchArticleConTodosLosAtributos(getArticulo());
					if(articulo==null)
						JOptionPane.showMessageDialog(null,
								"No se ha encontrado la id del articulo", "Articulo",
								JOptionPane.ERROR_MESSAGE);
					else {
					
					InterfazDecisionFinal i= new InterfazDecisionFinal(articulo);
					
					i.setVisible(true);
					}
				}
			});
			btnNewButton_1.setBounds(138, 187, 148, 23);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("Enviar comentarios");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Articulo articulo =  DataBaseArticle.searchArticleConTodosLosAtributos(getArticulo());
					
					if(articulo==null)
						JOptionPane.showMessageDialog(null,
								"No se ha encontrado la id del articulo", "Articulo",
								JOptionPane.ERROR_MESSAGE);
					else {
					articulo.addComentario(new Comentario(2, "hola", "12", "Aceptar", "12", "Temporal"));
					InterfazEnviarComentariosAlAutor i= new InterfazEnviarComentariosAlAutor(articulo);
					i.setVisible(true);
					}
				}
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 8));
			btnNewButton_2.setBounds(296, 228, 138, 23);
		}
		return btnNewButton_2;
	}
}
