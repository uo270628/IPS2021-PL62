package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import business.Articulo;
import business.Comentario;
import persistence.DataBaseManager;

public class VerComentariosEditor {

	private JFrame frame;
	private JComboBox<Comentario> comboBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextPane textPane;
	private JTextArea textArea;
	private Comentario comentario;
	private Articulo articulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerComentariosEditor window = new VerComentariosEditor("prueba");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param string
	 */
	public VerComentariosEditor(String idArticulo) {
		initialize();
		articulo = DataBaseManager.getArticleFromId(idArticulo);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().add(getTextPane());
		frame.getContentPane().add(getTextArea());
		frame.getContentPane().add(getComboBox());
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JComboBox<Comentario> getComboBox() {
		if (comboBox == null) {
			if (articulo != null) {
				List<Comentario> list = DataBaseManager.SelectComentsVisibleForEditor(articulo.getId());
				if (!list.isEmpty()) {
					comboBox = new JComboBox<Comentario>();
					comboBox.setModel(new DefaultComboBoxModel<Comentario>(list.toArray(new Comentario[list.size()])));
					comentario = (Comentario) comboBox.getSelectedItem();
					setTexts();
					comboBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							comentario = (Comentario) comboBox.getSelectedItem();
							setTexts();
						}
					});
				}
				else {
					comboBox = new JComboBox<Comentario>();
				}
			} else {
				comboBox = new JComboBox<Comentario>();
			}
			comboBox.setBounds(274, 200, 160, 22);
		}
		return comboBox;
	}

	private void setTexts() {
		textPane.setText(DataBaseManager.getNombreRevisor(comentario.getIdRevisor()));
		textArea.setText(comentario.getTexto());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Revisor:");
			lblNewLabel.setBounds(10, 11, 46, 14);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Texto:");
			lblNewLabel_1.setBounds(10, 48, 46, 14);
		}
		return lblNewLabel_1;
	}

	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setBounds(55, 11, 230, 20);
		}
		return textPane;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBounds(55, 43, 230, 120);
		}
		return textArea;
	}
}
