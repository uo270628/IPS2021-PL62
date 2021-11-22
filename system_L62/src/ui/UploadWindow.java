package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

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
import business.Articulo.ArticleState;
import business.Autor;

public class UploadWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitle;
	private JLabel lblTitle;
	private JTextField textFieldAuthor;
	private JLabel lblAuthor;
	private JTextArea textAreaResumen;
	private JLabel lblResumen;
	private JScrollPane scrollPaneResumen;
	private JLabel lblOtherAuthors;
	private JTextField textFieldOtherAutors;
	private JButton btnAddAuthor;
	private JScrollPane scrollPaneOtherAuthors;
	private JTextArea textAreaOtherAuthors;
	private JLabel lblKeywords;
	private JTextField textFieldKeywords;
	private JButton btnAddKeyword;
	private JScrollPane scrollPaneKeywords;
	private JTextArea textAreaKeywords;
	private JButton btnBack;
	private JButton btnNext;
	private JButton btnDeleteOtherAuthors;
	private JButton btnDeleteKeywords;

	private List<Autor> authors;
	private List<String> keywords;
	private Articulo article;
	private ArticlesByAuthorWindow abaw;

	/**
	 * Create the frame.
	 * 
	 * @param frame
	 */
	public UploadWindow(ArticlesByAuthorWindow abaw, Articulo article) {
		this.abaw = abaw;
		this.article = article;
		if (article != null) {
			this.authors = article.getAuthors();
			this.keywords = article.getKeywords();
		} else {
			this.authors = new LinkedList<>();
			this.keywords = new LinkedList<>();
		}

		setModal(true);
		setResizable(false);
		setTitle("Edici\u00F3n de art\u00EDculo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 599, 485);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextFieldTitle());
		contentPane.add(getLblTitle());
		contentPane.add(getTextFieldAuthor());
		contentPane.add(getLblAuthor());
		contentPane.add(getLblResumen());
		contentPane.add(getScrollPaneResumen());
		contentPane.add(getLblOtherAuthors());
		contentPane.add(getTextFieldOtherAutors());
		contentPane.add(getBtnAddAuthor());
		contentPane.add(getScrollPaneOtherAuthors());
		contentPane.add(getLblKeywords());
		contentPane.add(getTextFieldKeywords());
		contentPane.add(getBtnAddKeyword());
		contentPane.add(getScrollPaneKeywords());
		contentPane.add(getBtnBack());
		contentPane.add(getBtnNext());
		contentPane.add(getBtnDeleteOtherAuthors());
		contentPane.add(getBtnDeleteKeywords());
	}

	public JTextField getTextFieldTitle() {
		if (textFieldTitle == null) {
			textFieldTitle = new JTextField();
			textFieldTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldTitle.setBounds(167, 24, 205, 25);
			textFieldTitle.setColumns(10);
			if (article != null)
				if (article.getTitle() != null)
					textFieldTitle.setText(article.getTitle());
		}
		return textFieldTitle;
	}

	public JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("T\u00EDtulo:");
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTitle.setBounds(37, 30, 60, 13);
		}
		return lblTitle;
	}

	public JTextField getTextFieldAuthor() {
		if (textFieldAuthor == null) {
			textFieldAuthor = new JTextField();
			textFieldAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldAuthor.setBounds(167, 59, 205, 25);
			textFieldAuthor.setColumns(10);
			if (article != null)
				if (article.getAuthor() != null)
					textFieldAuthor.setText(article.getAuthor().getName());
		}
		return textFieldAuthor;
	}

	public JLabel getLblAuthor() {
		if (lblAuthor == null) {
			lblAuthor = new JLabel("Autor principal:");
			lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblAuthor.setBounds(37, 60, 94, 13);
		}
		return lblAuthor;
	}

	public JTextArea getTextAreaResumen() {
		if (textAreaResumen == null) {
			textAreaResumen = new JTextArea();
			textAreaResumen.setLineWrap(true);
			textAreaResumen.setFont(new Font("Tahoma", Font.PLAIN, 12));
			if (article != null)
				if (article.getResumen() != null)
					textAreaResumen.setText(article.getResumen());
		}
		return textAreaResumen;
	}

	public JLabel getLblResumen() {
		if (lblResumen == null) {
			lblResumen = new JLabel("Resumen:");
			lblResumen.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblResumen.setBounds(37, 196, 60, 13);
		}
		return lblResumen;
	}

	public JScrollPane getScrollPaneResumen() {
		if (scrollPaneResumen == null) {
			scrollPaneResumen = new JScrollPane();
			scrollPaneResumen.setBounds(167, 192, 205, 65);
			scrollPaneResumen.setViewportView(getTextAreaResumen());
		}
		return scrollPaneResumen;
	}

	public JLabel getLblOtherAuthors() {
		if (lblOtherAuthors == null) {
			lblOtherAuthors = new JLabel("Otros autores:");
			lblOtherAuthors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblOtherAuthors.setBounds(37, 120, 94, 13);
		}
		return lblOtherAuthors;
	}

	public JTextField getTextFieldOtherAutors() {
		if (textFieldOtherAutors == null) {
			textFieldOtherAutors = new JTextField();
			textFieldOtherAutors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldOtherAutors.setBounds(167, 114, 129, 25);
			textFieldOtherAutors.setColumns(10);
		}
		return textFieldOtherAutors;
	}

	public JButton getBtnAddAuthor() {
		if (btnAddAuthor == null) {
			btnAddAuthor = new JButton("A\u00F1adir");
			btnAddAuthor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addAuthor();
				}
			});
			btnAddAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAddAuthor.setBounds(306, 114, 85, 25);
		}
		return btnAddAuthor;
	}

	public JScrollPane getScrollPaneOtherAuthors() {
		if (scrollPaneOtherAuthors == null) {
			scrollPaneOtherAuthors = new JScrollPane();
			scrollPaneOtherAuthors.setBounds(401, 116, 172, 53);
			scrollPaneOtherAuthors.setViewportView(getTextAreaOtherAuthors());
		}
		return scrollPaneOtherAuthors;
	}

	public JTextArea getTextAreaOtherAuthors() {
		if (textAreaOtherAuthors == null) {
			textAreaOtherAuthors = new JTextArea();
			textAreaOtherAuthors.setEditable(false);
			textAreaOtherAuthors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			if (article != null)
				textAreaOtherAuthors.setText(listAuthors());
		}
		return textAreaOtherAuthors;
	}

	public JLabel getLblKeywords() {
		if (lblKeywords == null) {
			lblKeywords = new JLabel("Palabras clave:");
			lblKeywords.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblKeywords.setBounds(37, 321, 85, 13);
		}
		return lblKeywords;
	}

	public JTextField getTextFieldKeywords() {
		if (textFieldKeywords == null) {
			textFieldKeywords = new JTextField();
			textFieldKeywords.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldKeywords.setBounds(167, 319, 129, 25);
			textFieldKeywords.setColumns(10);
		}
		return textFieldKeywords;
	}

	public JButton getBtnAddKeyword() {
		if (btnAddKeyword == null) {
			btnAddKeyword = new JButton("A\u00F1adir");
			btnAddKeyword.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addKeyword();
				}
			});
			btnAddKeyword.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAddKeyword.setBounds(306, 321, 85, 24);
		}
		return btnAddKeyword;
	}

	public JScrollPane getScrollPaneKeywords() {
		if (scrollPaneKeywords == null) {
			scrollPaneKeywords = new JScrollPane();
			scrollPaneKeywords.setBounds(401, 321, 172, 65);
			scrollPaneKeywords.setViewportView(getTextAreaKeywords());
		}
		return scrollPaneKeywords;
	}

	public JTextArea getTextAreaKeywords() {
		if (textAreaKeywords == null) {
			textAreaKeywords = new JTextArea();
			textAreaKeywords.setEditable(false);
			textAreaKeywords.setFont(new Font("Tahoma", Font.PLAIN, 12));
			if (article != null)
				textAreaKeywords.setText(listKeywords());
		}
		return textAreaKeywords;
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
			btnBack.setBounds(488, 418, 85, 21);
		}
		return btnBack;
	}

	public JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Siguiente");
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					checkFields();
				}
			});
			btnNext.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNext.setBounds(393, 418, 85, 21);
		}
		return btnNext;
	}

	public JButton getBtnDeleteOtherAuthors() {
		if (btnDeleteOtherAuthors == null) {
			btnDeleteOtherAuthors = new JButton("Borrar");
			btnDeleteOtherAuthors.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeAuthor();
				}
			});
			btnDeleteOtherAuthors.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnDeleteOtherAuthors.setBounds(306, 149, 85, 21);
		}
		return btnDeleteOtherAuthors;
	}

	public JButton getBtnDeleteKeywords() {
		if (btnDeleteKeywords == null) {
			btnDeleteKeywords = new JButton("Borrar");
			btnDeleteKeywords.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeKeyword();
				}
			});
			btnDeleteKeywords.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnDeleteKeywords.setBounds(306, 355, 85, 21);
		}
		return btnDeleteKeywords;
	}

	/**
	 * Devuelve los autores del artículo uno por línea
	 * 
	 * @return cadena de lista de autores
	 */
	private String listAuthors() {
		String str = "";
		for (Autor author : authors) {
			str += author.getName() + "\n";
		}
		return str.trim();
	}

	/**
	 * Devuelve las palabras clave del artículo una por línea
	 * 
	 * @return cadena de lista de palabras clave
	 */
	private String listKeywords() {
		String str = "";
		for (String keyword : keywords) {
			str += keyword + "\n";
		}
		return str.trim();
	}

	/**
	 * Cierra esta ventana y actualiza la lista de artículos de la ventana anterior
	 * 
	 */
	public void disposeAll() {
		this.abaw.updateListArticles();
		this.dispose();
	}

	/**
	 * Abre la ventana de edición de documentos a partir del artículo asociado a
	 * esta
	 */
	private void openDocWindow() {
		String id;
		if (article == null) {
			id = UUID.randomUUID().toString().substring(0, 20);
		} else {
			id = article.getId();
		}
		if (article == null) {
			DocumentsWindow dw = new DocumentsWindow(this, new Articulo(id, getTextFieldTitle().getText(),
					new Autor(getTextFieldAuthor().getText()), authors, getTextAreaResumen().getText(), keywords),
					article);
			dw.setVisible(true);
		} else {
			DocumentsWindow dw = new DocumentsWindow(this,
					new Articulo(id, getTextFieldTitle().getText(), new Autor(getTextFieldAuthor().getText()), authors,
							getTextAreaResumen().getText(), keywords, article.getPresentationCard(),
							article.getSrcFile(), article.getCvAuthors(), toArticleState(article.getState())),
					article);
			dw.setVisible(true);
		}
	}

	/**
	 * Añade un autor a la lista de autores del artículo
	 */
	private void addAuthor() {
		if (!getTextFieldOtherAutors().getText().trim().equals("")) {
			authors.add(new Autor(getTextFieldOtherAutors().getText().trim()));
			getTextFieldOtherAutors().setText("");
			getTextAreaOtherAuthors().setText(listAuthors());
		}
	}

	/**
	 * Añade una palabra clave a la lista de palabras clave del artículo
	 */
	private void addKeyword() {
		if (!getTextFieldKeywords().getText().trim().equals("")) {
			keywords.add(getTextFieldKeywords().getText().trim());
			getTextFieldKeywords().setText("");
			getTextAreaKeywords().setText(listKeywords());
		}
	}

	/**
	 * Abre la ventana de documentos si y sólo si hay un autor principal declarado
	 * en el artículo
	 */
	private void checkFields() {
		if (getTextFieldAuthor().getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Necesitas por lo menos identificarte como autor.");
		} else {
			openDocWindow();
		}
	}

	/**
	 * Borra un autor de la lista de autores del artículo
	 */
	private void removeAuthor() {
		if (listAuthors().contains(getTextFieldOtherAutors().getText().trim())) {
			authors.remove(new Autor(getTextFieldOtherAutors().getText().trim()));
			getTextAreaOtherAuthors().setText(listAuthors());
			getTextFieldOtherAutors().setText("");
		}
	}

	/**
	 * Borra una palabra clave de la lista de palabras clave del artículo
	 */
	private void removeKeyword() {
		if (listKeywords().contains(getTextFieldKeywords().getText().trim())) {
			keywords.remove(getTextFieldKeywords().getText().trim());
			getTextAreaKeywords().setText(listKeywords());
			getTextFieldKeywords().setText("");
		}
	}

	private ArticleState toArticleState(String state) {
		switch (state) {
		case "CREATED":
			return ArticleState.CREATED;
		case "SENT":
			return ArticleState.SENT;
		case "WITH_EDITOR":
			return ArticleState.WITH_EDITOR;
		case "IN_REVISION":
			return ArticleState.IN_REVISION;
		case "ACCEPTED":
			return ArticleState.ACCEPTED;
		case "ACCEPTED_WITH_MINOR_CHANGES":
			return ArticleState.ACCEPTED_WITH_MINOR_CHANGES;
		case "ACCEPTED_WITH_GREATER_CHANGES":
			return ArticleState.ACCEPTED_WITH_GREATER_CHANGES;
		case "REJECTED":
			return ArticleState.REJECTED;
		case "IN_EDITION":
			return ArticleState.IN_EDITION;
		case "PUBLISHED":
			return ArticleState.PUBLISHED;
		default:
			return null;
		}
	}
}
