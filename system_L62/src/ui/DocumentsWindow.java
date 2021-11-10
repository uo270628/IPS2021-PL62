package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Articulo.ArticleState;
import persistence.DataBaseArticle;

public class DocumentsWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPresentationCard;
	private JTextField textFieldPresentationCard;
	private JLabel lblCV;
	private JTextField textFieldCVAuthor;
	private JButton btnAddCV;
	private JCheckBox chckbxCopyright;
	private JButton btnSave;
	private JButton btnDeleteCV;
	private JScrollPane scrollPaneCV;
	private JTextArea textAreaCV;
	private JLabel lblSrcFile;
	private JTextField textFieldSrcFile;
	private JButton btnBack;

	private UploadWindow cw;
	private List<String> cvAuthors;
	private Articulo newArticle;
	private Articulo oldArticle;
	private JButton btnSend;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public DocumentsWindow(UploadWindow cw, Articulo newArticle, Articulo oldArticle) {
		this.cw = cw;
		this.newArticle = newArticle;
		this.oldArticle = oldArticle;
		if (oldArticle != null) {
			this.cvAuthors = oldArticle.getCvAuthors();
		} else {
			this.cvAuthors = new LinkedList<>();
		}

		setModal(true);
		setResizable(false);
		setTitle("Ventana de archivos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblPresentationCard());
		contentPane.add(getTextFieldPresentationCard());
		contentPane.add(getLblCV());
		contentPane.add(getTextFieldCVAuthor());
		contentPane.add(getBtnAddCV());
		contentPane.add(getChckbxCopyright());
		contentPane.add(getBtnSave());
		contentPane.add(getBtnDeleteCV());
		contentPane.add(getScrollPaneCV());
		contentPane.add(getLblSrcFile());
		contentPane.add(getTextFieldSrcFile());
		contentPane.add(getBtnBack());
		contentPane.add(getBtnSend());
		contentPane.add(getBtnNewButton());
	}

	public JLabel getLblPresentationCard() {
		if (lblPresentationCard == null) {
			lblPresentationCard = new JLabel("Carta de presentaci\u00F3n:");
			lblPresentationCard.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPresentationCard.setBounds(28, 22, 136, 21);
		}
		return lblPresentationCard;
	}

	public JTextField getTextFieldPresentationCard() {
		if (textFieldPresentationCard == null) {
			textFieldPresentationCard = new JTextField();
			textFieldPresentationCard.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldPresentationCard.setBounds(174, 24, 229, 19);
			textFieldPresentationCard.setColumns(10);
			if (oldArticle != null)
				if (oldArticle.getPresentationCard() != null)
					textFieldPresentationCard.setText(oldArticle.getPresentationCard());
		}
		return textFieldPresentationCard;
	}

	public JLabel getLblCV() {
		if (lblCV == null) {
			lblCV = new JLabel("Curr\u00EDculums de los autores:");
			lblCV.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblCV.setBounds(28, 96, 158, 21);
		}
		return lblCV;
	}

	public JTextField getTextFieldCVAuthor() {
		if (textFieldCVAuthor == null) {
			textFieldCVAuthor = new JTextField();
			textFieldCVAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldCVAuthor.setBounds(188, 98, 215, 19);
			textFieldCVAuthor.setColumns(10);
		}
		return textFieldCVAuthor;
	}

	public JButton getBtnAddCV() {
		if (btnAddCV == null) {
			btnAddCV = new JButton("A\u00F1adir");
			btnAddCV.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addCVAuthor();
				}
			});
			btnAddCV.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAddCV.setBounds(28, 142, 85, 21);
		}
		return btnAddCV;
	}

	public JCheckBox getChckbxCopyright() {
		if (chckbxCopyright == null) {
			chckbxCopyright = new JCheckBox("Confirmo ser el autor del art\u00EDculo");
			chckbxCopyright.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					acceptCopyright();
				}
			});
			chckbxCopyright.setFont(new Font("Tahoma", Font.PLAIN, 12));
			chckbxCopyright.setBounds(28, 178, 207, 21);
		}
		return chckbxCopyright;
	}

	public JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Guardar");
			btnSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					operateArticle();
				}
			});
			btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnSave.setBounds(241, 213, 90, 28);
		}
		return btnSave;
	}

	public JButton getBtnDeleteCV() {
		if (btnDeleteCV == null) {
			btnDeleteCV = new JButton("Borrar");
			btnDeleteCV.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeCVAuthor();
				}
			});
			btnDeleteCV.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnDeleteCV.setBounds(129, 142, 85, 21);
		}
		return btnDeleteCV;
	}

	public JScrollPane getScrollPaneCV() {
		if (scrollPaneCV == null) {
			scrollPaneCV = new JScrollPane();
			scrollPaneCV.setBounds(241, 142, 162, 41);
			scrollPaneCV.setViewportView(getTextAreaCV());
		}
		return scrollPaneCV;
	}

	public JTextArea getTextAreaCV() {
		if (textAreaCV == null) {
			textAreaCV = new JTextArea();
			textAreaCV.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textAreaCV.setEditable(false);
			if (oldArticle != null)
				textAreaCV.setText(listCVAuthors());
		}
		return textAreaCV;
	}

	public JLabel getLblSrcFile() {
		if (lblSrcFile == null) {
			lblSrcFile = new JLabel("Fichero fuente:");
			lblSrcFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSrcFile.setBounds(28, 64, 97, 13);
		}
		return lblSrcFile;
	}

	public JTextField getTextFieldSrcFile() {
		if (textFieldSrcFile == null) {
			textFieldSrcFile = new JTextField();
			textFieldSrcFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldSrcFile.setBounds(174, 62, 229, 19);
			textFieldSrcFile.setColumns(10);
			if (oldArticle != null)
				if (oldArticle.getSrcFile() != null)
					textFieldSrcFile.setText(oldArticle.getSrcFile());
		}
		return textFieldSrcFile;
	}

	public JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Atr\u00E1s");
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					disposeThisWindow();
				}
			});
			btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBack.setBounds(341, 213, 85, 28);
		}
		return btnBack;
	}

	public JButton getBtnSend() {
		if (btnSend == null) {
			btnSend = new JButton("Enviar");
			btnSend.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					finishArticle();
				}
			});
			btnSend.setEnabled(false);
			btnSend.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnSend.setBounds(141, 214, 94, 28);
		}
		return btnSend;
	}

	/**
	 * Añade un currículum vitae de autor a la lista de CVs autores del artículo
	 */
	private void addCVAuthor() {
		if (!getTextFieldCVAuthor().getText().trim().equals("")) {
			cvAuthors.add(getTextFieldCVAuthor().getText().trim());
			getTextFieldCVAuthor().setText("");
			getTextAreaCV().setText(listCVAuthors());
		}
	}

	/**
	 * Borra un currículum vitae de autor de la lista de CVs autores del artículo
	 */
	private void removeCVAuthor() {
		if (listCVAuthors().contains(getTextFieldCVAuthor().getText().trim())) {
			cvAuthors.remove(getTextFieldCVAuthor().getText().trim());
			getTextAreaCV().setText(listCVAuthors());
			getTextFieldCVAuthor().setText("");
		}
	}

	/**
	 * Borra esta ventana y su antecesora
	 */
	private void disposeWindows() {
		cw.disposeAll();
		this.dispose();
	}

	/**
	 * Borra esta ventana
	 */
	private void disposeThisWindow() {
		this.dispose();
	}

	/**
	 * Devuelve los CVs de los autores del artículo uno por línea
	 * 
	 * @return cadena de lista de CVs de autores
	 */
	private String listCVAuthors() {
		String str = "";
		for (String cv : cvAuthors) {
			str += cv + "\n";
		}
		return str.trim();
	}

	/**
	 * Transfiere los nuevos campos al artículo y a la base de datos
	 */
	private void operateArticle() {
		newArticle.setPresentationCard(getTextFieldPresentationCard().getText().trim());
		newArticle.setSrcFile(getTextFieldSrcFile().getText().trim());
		newArticle.setCvAuthors(cvAuthors);
		if (oldArticle == null) {
			uploadArticle();
		} else {
			updateArticle();
		}
	}

	/**
	 * Sube el nuevo artículo a la base de datos
	 */
	private void uploadArticle() {
		boolean made = DataBaseArticle.uploadArticle(newArticle);
		if (made) {
			JOptionPane.showMessageDialog(null, "Se ha creado el artículo.");
			disposeWindows();
		} else {
			JOptionPane.showMessageDialog(null, "Ha habido un error al intentar crear el artículo.");
		}
	}

	/**
	 * Sube los cambios del artículo a la base de datos
	 */
	private void updateArticle() {
		boolean made = DataBaseArticle.updateArticle(newArticle);
		if (made) {
			JOptionPane.showMessageDialog(null, "Se ha actualizado el artículo.");
			disposeWindows();
		} else {
			JOptionPane.showMessageDialog(null, "Ha habido un error al intentar actualizar el artículo.");
		}
	}

	/**
	 * Activa o desactiva el botón de copyright en función del checkBox
	 */
	private void acceptCopyright() {
		if (chckbxCopyright.isSelected()) {
			getBtnSend().setEnabled(true);
		} else {
			getBtnSend().setEnabled(false);
		}
	}

	/**
	 * Envía el artículo al editor para que decida si publicarlo
	 * 
	 * @param article
	 */
	private void publishArticle(Articulo article) {
		DataBaseArticle.publishArticle(article.getId());
		JOptionPane.showMessageDialog(null, "El artículo está en proceso de ser publicado.");
		disposeWindows();
	}

	/**
	 * Finaliza el artículo para que un editor lo pueda evaluar
	 * 
	 * @param article
	 */
	private void sendArticleToApprove(Articulo article) {
		if (article.isComplete()) {
			DataBaseArticle.sendArticleToAprove(article.getId());
			JOptionPane.showMessageDialog(null, "El artículo está listo para ser evaluado.");
			disposeWindows();
		} else {
			JOptionPane.showMessageDialog(null, "Faltan campos del artículo por rellenar.");
		}
	}

	/**
	 * Transfiere los nuevos campos al artículo y finaliza la edición del mismo
	 */
	private void finishArticle() {
		newArticle.setPresentationCard(getTextFieldPresentationCard().getText().trim());
		newArticle.setSrcFile(getTextFieldSrcFile().getText().trim());
		newArticle.setCvAuthors(cvAuthors);
		boolean present = false;
		if (DataBaseArticle.searchArticle(newArticle.getId()) != null)
			present = true;
		boolean made;
		if (!present)
			made = DataBaseArticle.uploadArticle(newArticle);
		else
			made = DataBaseArticle.updateArticle(newArticle);
		if (made) {
			if (newArticle.getState().equals(ArticleState.CREATED.toString())) {
				sendArticleToApprove(newArticle);
			} else if (newArticle.getState().equals(ArticleState.ACCEPTED_WITH_CHANGES.toString())) {
				publishArticle(newArticle);
			}
			disposeWindows();
		} else {
			JOptionPane.showMessageDialog(null, "Ha habido un error al intentar actualizar el artículo.");
		}
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Rec. Revisores");
			btnNewButton.setEnabled(newArticle.getState().equals(ArticleState.CREATED.toString()));
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SugerirEditor se = new SugerirEditor(newArticle);
					se.setVisible(true);
				}
			});
			btnNewButton.setBounds(10, 217, 115, 23);
		}
		return btnNewButton;
	}
}
