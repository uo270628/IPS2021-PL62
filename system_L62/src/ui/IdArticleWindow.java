package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Articulo.ArticleState;
import persistence.DataBaseArticle;

public class IdArticleWindow extends JDialog {

	private static final int UPDATE = 0;
	private static final int SEND_TO_APPROVE = 1;
	private static final int PUBLISH = 2;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNext;
	private JButton btnBack;
	private JTextField textFieldID;
	private JLabel lblID;

	private int action;
	private JLabel lblAuthor;
	private JTextField textFieldAuthor;

	/**
	 * Create the frame.
	 */
	public IdArticleWindow(int action) {
		this.action = action;

		setModal(true);
		setResizable(false);
		setTitle("ID art\u00EDculo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNext());
		contentPane.add(getBtnBack());
		contentPane.add(getTextFieldID());
		contentPane.add(getLblID());
		contentPane.add(getLblAuthor());
		contentPane.add(getTextFieldAuthor());
	}

	public JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Siguiente");
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					modifyArticle();
				}
			});
			btnNext.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNext.setBounds(341, 232, 85, 21);
		}
		return btnNext;
	}

	public JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Atr\u00E1s");
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBack.setBounds(246, 233, 85, 21);
		}
		return btnBack;
	}

	public JTextField getTextFieldID() {
		if (textFieldID == null) {
			textFieldID = new JTextField();
			textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldID.setBounds(76, 73, 333, 21);
			textFieldID.setColumns(10);
		}
		return textFieldID;
	}

	public JLabel getLblID() {
		if (lblID == null) {
			lblID = new JLabel("ID:");
			lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblID.setBounds(29, 73, 37, 21);
		}
		return lblID;
	}

	private JLabel getLblAuthor() {
		if (lblAuthor == null) {
			lblAuthor = new JLabel("Autor:");
			lblAuthor.setBounds(29, 140, 46, 14);
		}
		return lblAuthor;
	}

	private JTextField getTextFieldAuthor() {
		if (textFieldAuthor == null) {
			textFieldAuthor = new JTextField();
			textFieldAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldAuthor.setBounds(85, 137, 324, 20);
			textFieldAuthor.setColumns(10);
		}
		return textFieldAuthor;
	}

	private void updateArticle() {
		Articulo a = DataBaseArticle.searchArticle(getTextFieldID().getText().trim());
		if (a == null)
			JOptionPane.showMessageDialog(null, "No existe ningún artículo con ese id.");
		else {
			UploadWindow cw = new UploadWindow(this, a);
			cw.setVisible(true);
		}
	}

	private void modifyArticle() {
		String id = getTextFieldID().getText().trim();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "Hay algún campo vacío. Por favor, rellene la información solicitada.");
		} else {
			Articulo a = DataBaseArticle.searchArticle(id);
			if (a != null) {
				if (a.getAuthor().getName().equals(getTextFieldAuthor().getText())) {
					if (action == UPDATE) {
						updateArticle();
					} else if (action == SEND_TO_APPROVE) {
						sendArticleToApprove(id, a);
					} else if (action == PUBLISH) {
						publishArticle(id, a);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ese artículo no pertenece a ese autor.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "No se ha encontrado ningún artículo con ese ID.");
			}
		}
	}

	private void publishArticle(String id, Articulo a) {
		if (a.getState().equals(ArticleState.ACCEPTED.toString())) {
			DataBaseArticle.sendArticleToAprove(id);
			JOptionPane.showMessageDialog(null, "El artículo está en proceso de ser publicado.");
		} else {
			JOptionPane.showMessageDialog(null, "El artículo no está en estado aceptado.");
		}
	}

	private void sendArticleToApprove(String id, Articulo a) {
		if (a.isComplete() && a.getState().equals(ArticleState.SENT.toString())) {
			DataBaseArticle.sendArticleToAprove(id);
			JOptionPane.showMessageDialog(null, "El artículo está listo para ser evaluado.");
		} else {
			JOptionPane.showMessageDialog(null, "Faltan campos del artículo por rellenar.");
		}
	}

}
