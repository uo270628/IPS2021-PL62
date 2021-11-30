package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import business.Articulo;
import persistence.DataBaseManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RevisorArticuloActions extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private String revisor;
	private Articulo articulo;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RevisorArticuloActions frame = new RevisorArticuloActions(null, "Pedro");
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
	 * @param ri
	 */
	public RevisorArticuloActions(Articulo articulo, String revisor) {
		setResizable(false);
		new DefaultListModel<>();

		this.articulo = articulo;
		this.revisor = revisor;
		contentPane = (JPanel) getContentPane();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getTextField_1());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getBtnNewButton_4());
		if(DataBaseManager.getDebate(articulo.getId())!=null) {
			contentPane.add(getBtnNewButton_5());
		}
		if (articulo.getState().equals("WITH_EDITOR")) {
			contentPane.add(getBtnNewButton_3());
		}
		setLocationRelativeTo(null);

	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Articulo:");
			lblNewLabel.setBounds(10, 11, 46, 14);
		}
		return lblNewLabel;
	}

	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(66, 8, 232, 20);
			textField_1.setColumns(10);
			textField_1.setText(articulo.getTitle());
		}
		return textField_1;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("DECISION");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					@SuppressWarnings("unused")
					RevisorComentInterface rci = new RevisorComentInterface(revisor, "Decision propuesta",articulo);
				}
			});
			btnNewButton.setBounds(10, 39, 136, 23);
		}
		return btnNewButton;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("EDITOR");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					@SuppressWarnings("unused")
					RevisorComentInterface rci = new RevisorComentInterface(revisor, "Notas para editor",articulo);
				}
			});
			btnNewButton_1.setBounds(10, 73, 136, 23);
		}
		return btnNewButton_1;
	}

	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("AUTOR");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					@SuppressWarnings("unused")
					RevisorComentInterface rci = new RevisorComentInterface(revisor, "Comentario para autor",articulo);
					
				}
			});
			btnNewButton_2.setBounds(10, 107, 136, 23);
		}
		return btnNewButton_2;
	}

	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("RECHAZAR");
			RevisorArticuloActions frame = this;
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DataBaseManager.removeArticleFromCandidates(articulo, revisor);
					frame.dispose();
				}
			});
			btnNewButton_3.setBounds(305, 39, 119, 23);
		}
		return btnNewButton_3;
	}
	private JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("COMENTARIOS");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					@SuppressWarnings("unused")
					RevisorVerComentInterface r = new RevisorVerComentInterface(revisor,articulo);
				}
			});
			btnNewButton_4.setBounds(10, 141, 136, 23);
		}
		return btnNewButton_4;
	}
	private JButton getBtnNewButton_5() {
		if (btnNewButton_5 == null) {
			btnNewButton_5 = new JButton("DEBATE");
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UseDebate r = new UseDebate(articulo.getId(), revisor);
					r.setVisible(true);
				}
			});
			btnNewButton_5.setBounds(10, 175, 107, 23);
		}
		return btnNewButton_5;
	}
}
