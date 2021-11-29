package ui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;

import business.Mensaje;

import javax.swing.ListModel;
import javax.swing.border.LineBorder;

import persistence.DataBaseManager;

import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UseDebate extends JFrame {

	private String redactor;
	private String idArticulo;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Mensaje> listMensaje;
	private JTextField textField;
	private DefaultListModel<Mensaje> Model;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UseDebate frame = new UseDebate("id", "red");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param idArticulo
	 * @param redactor
	 */
	public UseDebate(String idArticulo, String redactor) {
		this.redactor = redactor;
		this.idArticulo = idArticulo;
		setResizable(false);
		this.Model = new DefaultListModel<>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getlistMensaje());
		contentPane.add(getTextField());
		contentPane.add(getBtnNewButton());
	}

	private JList<Mensaje> getlistMensaje() {
		if (listMensaje == null) {
			listMensaje = new JList<Mensaje>((ListModel<Mensaje>) Model);
			listMensaje.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (listMensaje.getSelectedIndex() != -1) {
						JOptionPane.showMessageDialog(listMensaje, listMensaje.getSelectedValue().getTexto());
					}
				}
			});

			listMensaje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listMensaje.setSelectedIndex(-1);
			listMensaje.setBorder(new LineBorder(new Color(0, 0, 0)));
			listMensaje.setBounds(10, 11, 416, 192);
			update();
		}
		return listMensaje;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(10, 214, 209, 36);
			textField.setColumns(10);
		}
		return textField;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Enviar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DataBaseManager.addMensajeToDebate(textField.getText(), idArticulo, redactor, Model.getSize());
					update();
					textField.setText("");
				}
			});
			btnNewButton.setBounds(285, 221, 89, 23);
		}
		return btnNewButton;
	}

	public void update() {
		listMensaje.setSelectedIndex(-1);
		Model.removeAllElements();
		List<Mensaje> mensajes = DataBaseManager.SelectAlMensajesForChat(idArticulo);
		for (Mensaje a : mensajes)
			Model.addElement(a);
	}
}
