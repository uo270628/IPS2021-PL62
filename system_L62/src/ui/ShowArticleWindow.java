package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Autor;
import persistence.DataBaseArticle;

public class ShowArticleWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblOtherAuthors;
	private JScrollPane scrollPaneOtherAuthors;
	private JTextArea textAreaOtherAuthors;
	private JLabel lblSummary;
	private JScrollPane scrollPaneSummary;
	private JTextArea textAreaSummary;
	private JLabel lblKeywords;
	private JScrollPane scrollPaneKeywords;
	private JTextArea textAreaKeywords;
	private JTextField textFieldTitle;
	private JTextField textFieldAuthor;
	private JLabel lblSrcFile;
	private JTextField textFieldSrcFile;
	private JLabel lblPresentationCard;
	private JTextField textFieldPresentationCard;
	private JLabel lblCVAuthors;
	private JScrollPane scrollPaneCVAuthors;
	private JTextArea textAreaCVAuthors;
	private JLabel lblState;
	private JTextField textFieldState;
	private JButton btnPublish;

	private Articulo article;
	private ArticlesByAuthorWindow window;
	private JButton btnSeeRevisionComments;

	/**
	 * Create the frame.
	 */
	public ShowArticleWindow(Articulo article, ArticlesByAuthorWindow window) {
		this.article = article;
		this.window = window;

		setTitle("Mostrar art\u00EDculo");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 357, 506);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTitle());
		contentPane.add(getLblAuthor());
		contentPane.add(getLblOtherAuthors());
		contentPane.add(getScrollPaneOtherAuthors());
		contentPane.add(getLblSummary());
		contentPane.add(getScrollPaneSummary());
		contentPane.add(getLblKeywords());
		contentPane.add(getScrollPaneKeywords());
		contentPane.add(getTextFieldTitle());
		contentPane.add(getTextFieldAuthor());
		contentPane.add(getLblSrcFile());
		contentPane.add(getTextFieldSrcFile());
		contentPane.add(getLblPresentationCard());
		contentPane.add(getTextFieldPresentationCard());
		contentPane.add(getLblCVAuthors());
		contentPane.add(getScrollPaneCVAuthors());
		contentPane.add(getLblState());
		contentPane.add(getTextFieldState());
		contentPane.add(getBtnPublish());
		contentPane.add(getBtnSeeRevisionComments());
		setLocationRelativeTo(null);

	}

	public JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("T\u00EDtulo:");
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTitle.setBounds(10, 10, 45, 13);
		}
		return lblTitle;
	}

	public JLabel getLblAuthor() {
		if (lblAuthor == null) {
			lblAuthor = new JLabel("Autor principal:");
			lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblAuthor.setBounds(10, 33, 96, 13);
		}
		return lblAuthor;
	}

	public JLabel getLblOtherAuthors() {
		if (lblOtherAuthors == null) {
			lblOtherAuthors = new JLabel("Otros autores:");
			lblOtherAuthors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblOtherAuthors.setBounds(10, 56, 96, 13);
		}
		return lblOtherAuthors;
	}

	public JScrollPane getScrollPaneOtherAuthors() {
		if (scrollPaneOtherAuthors == null) {
			scrollPaneOtherAuthors = new JScrollPane();
			scrollPaneOtherAuthors.setBounds(116, 56, 200, 63);
			scrollPaneOtherAuthors.setViewportView(getTextAreaOtherAuthors());
		}
		return scrollPaneOtherAuthors;
	}

	public JTextArea getTextAreaOtherAuthors() {
		if (textAreaOtherAuthors == null) {
			textAreaOtherAuthors = new JTextArea();
			textAreaOtherAuthors.setEditable(false);
			textAreaOtherAuthors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textAreaOtherAuthors.setText(listAuthorCollection(article.getAuthors()));
		}
		return textAreaOtherAuthors;
	}

	public JLabel getLblSummary() {
		if (lblSummary == null) {
			lblSummary = new JLabel("Resumen:");
			lblSummary.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSummary.setBounds(10, 132, 96, 13);
		}
		return lblSummary;
	}

	public JScrollPane getScrollPaneSummary() {
		if (scrollPaneSummary == null) {
			scrollPaneSummary = new JScrollPane();
			scrollPaneSummary.setBounds(116, 129, 200, 63);
			scrollPaneSummary.setViewportView(getTextAreaSummary());
		}
		return scrollPaneSummary;
	}

	public JTextArea getTextAreaSummary() {
		if (textAreaSummary == null) {
			textAreaSummary = new JTextArea();
			textAreaSummary.setEditable(false);
			textAreaSummary.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textAreaSummary.setText(article.getResumen());
		}
		return textAreaSummary;
	}

	public JLabel getLblKeywords() {
		if (lblKeywords == null) {
			lblKeywords = new JLabel("Palabras clave:");
			lblKeywords.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblKeywords.setBounds(10, 203, 96, 13);
		}
		return lblKeywords;
	}

	public JScrollPane getScrollPaneKeywords() {
		if (scrollPaneKeywords == null) {
			scrollPaneKeywords = new JScrollPane();
			scrollPaneKeywords.setBounds(116, 202, 200, 63);
			scrollPaneKeywords.setViewportView(getTextAreaKeywords());
		}
		return scrollPaneKeywords;
	}

	public JTextArea getTextAreaKeywords() {
		if (textAreaKeywords == null) {
			textAreaKeywords = new JTextArea();
			textAreaKeywords.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textAreaKeywords.setEditable(false);
			textAreaKeywords.setText(listStringCollection(article.getKeywords()));
		}
		return textAreaKeywords;
	}

	public JTextField getTextFieldTitle() {
		if (textFieldTitle == null) {
			textFieldTitle = new JTextField();
			textFieldTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldTitle.setEditable(false);
			textFieldTitle.setBounds(116, 8, 200, 19);
			textFieldTitle.setColumns(10);
			textFieldTitle.setText(article.getTitle());
		}
		return textFieldTitle;
	}

	public JTextField getTextFieldAuthor() {
		if (textFieldAuthor == null) {
			textFieldAuthor = new JTextField();
			textFieldAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldAuthor.setEditable(false);
			textFieldAuthor.setColumns(10);
			textFieldAuthor.setBounds(116, 31, 200, 19);
			textFieldAuthor.setText(article.getAuthor().toString());
		}
		return textFieldAuthor;
	}

	public JLabel getLblSrcFile() {
		if (lblSrcFile == null) {
			lblSrcFile = new JLabel("Fichero fuente:");
			lblSrcFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSrcFile.setBounds(10, 277, 96, 13);
		}
		return lblSrcFile;
	}

	public JTextField getTextFieldSrcFile() {
		if (textFieldSrcFile == null) {
			textFieldSrcFile = new JTextField();
			textFieldSrcFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldSrcFile.setEditable(false);
			textFieldSrcFile.setColumns(10);
			textFieldSrcFile.setBounds(116, 275, 200, 19);
			textFieldSrcFile.setText(article.getSrcFile());
		}
		return textFieldSrcFile;
	}

	public JLabel getLblPresentationCard() {
		if (lblPresentationCard == null) {
			lblPresentationCard = new JLabel("Carta de presentaci\u00F3n:");
			lblPresentationCard.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPresentationCard.setBounds(10, 307, 136, 13);
		}
		return lblPresentationCard;
	}

	public JTextField getTextFieldPresentationCard() {
		if (textFieldPresentationCard == null) {
			textFieldPresentationCard = new JTextField();
			textFieldPresentationCard.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldPresentationCard.setEditable(false);
			textFieldPresentationCard.setColumns(10);
			textFieldPresentationCard.setBounds(156, 304, 160, 19);
			textFieldPresentationCard.setText(article.getPresentationCard());
		}
		return textFieldPresentationCard;
	}

	public JLabel getLblCVAuthors() {
		if (lblCVAuthors == null) {
			lblCVAuthors = new JLabel("CVs de los autores:");
			lblCVAuthors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblCVAuthors.setBounds(10, 333, 115, 13);
		}
		return lblCVAuthors;
	}

	public JScrollPane getScrollPaneCVAuthors() {
		if (scrollPaneCVAuthors == null) {
			scrollPaneCVAuthors = new JScrollPane();
			scrollPaneCVAuthors.setBounds(127, 330, 189, 63);
			scrollPaneCVAuthors.setViewportView(getTextAreaCVAuthors());
		}
		return scrollPaneCVAuthors;
	}

	public JTextArea getTextAreaCVAuthors() {
		if (textAreaCVAuthors == null) {
			textAreaCVAuthors = new JTextArea();
			textAreaCVAuthors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textAreaCVAuthors.setEditable(false);
			textAreaCVAuthors.setText(listStringCollection(article.getCvAuthors()));
		}
		return textAreaCVAuthors;
	}

	public JLabel getLblState() {
		if (lblState == null) {
			lblState = new JLabel("Estado del art\u00EDculo:");
			lblState.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblState.setBounds(10, 408, 115, 13);
		}
		return lblState;
	}

	public JTextField getTextFieldState() {
		if (textFieldState == null) {
			textFieldState = new JTextField();
			textFieldState.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldState.setEditable(false);
			textFieldState.setColumns(10);
			textFieldState.setBounds(127, 403, 189, 19);
			textFieldState.setText(article.getState());
		}
		return textFieldState;
	}

	public JButton getBtnPublish() {
		if (btnPublish == null) {
			btnPublish = new JButton("Publicar");
			btnPublish.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					publishArticle();
				}
			});
			btnPublish.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnPublish.setBounds(187, 434, 129, 21);
			if (article.isAccepted()) {
				btnPublish.setEnabled(true);
			} else {
				btnPublish.setEnabled(false);
			}
		}
		return btnPublish;
	}

	public JButton getBtnSeeRevisionComments() {
		if (btnSeeRevisionComments == null) {
			btnSeeRevisionComments = new JButton("Comentarios de revisi\u00F3n");
			btnSeeRevisionComments.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showCommentsForAuthor();
				}
			});
			btnSeeRevisionComments.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnSeeRevisionComments.setBounds(10, 435, 167, 21);
			if (article.hasBeenRevised()) {
				btnSeeRevisionComments.setEnabled(true);
			} else {
				btnSeeRevisionComments.setEnabled(false);
			}
		}
		return btnSeeRevisionComments;
	}

	private String listStringCollection(List<String> list) {
		String str = "";
		for (String s : list) {
			str += s.toString() + "\n";
		}
		return str.trim();
	}

	private String listAuthorCollection(List<Autor> list) {
		String str = "";
		for (Autor s : list) {
			str += s.toString() + "\n";
		}
		return str.trim();
	}

	private void publishArticle() {
		DataBaseArticle.publishArticle(article.getId());
		JOptionPane.showMessageDialog(null, "El artículo está en proceso de ser publicado.");
		this.dispose();
		this.window.updateListArticles();
	}

	private void showCommentsForAuthor() {
		SeeRevisionCommentsForAuthorWindow srcWindow = new SeeRevisionCommentsForAuthorWindow(article);
		srcWindow.setVisible(true);
	}

}
