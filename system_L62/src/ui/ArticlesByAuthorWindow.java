package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business.Articulo;
import business.Autor;
import persistence.DataBaseArticle;

public class ArticlesByAuthorWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<Articulo> articlesModel;

	private JPanel contentPane;
	private JLabel lblAuthor;
	private JTextField textFieldAuthor;
	private JList<Articulo> listArticlesByAuthor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ArticlesByAuthorWindow frame = new ArticlesByAuthorWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ArticlesByAuthorWindow() {
		this.articlesModel = new DefaultListModel<>();

		setTitle("Visualizar art\u00EDculos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblAuthor());
		contentPane.add(getTextFieldAuthor());
		contentPane.add(getListArticlesByAuthor());
	}

	public JLabel getLblAuthor() {
		if (lblAuthor == null) {
			lblAuthor = new JLabel("Autor:");
			lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblAuthor.setBounds(10, 10, 45, 13);
		}
		return lblAuthor;
	}

	public JTextField getTextFieldAuthor() {
		if (textFieldAuthor == null) {
			textFieldAuthor = new JTextField();
			textFieldAuthor.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if (textFieldAuthor.getText() != null && !textFieldAuthor.getText().trim().isEmpty()) {
						updateListArticles();
					}
				}
			});
			textFieldAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldAuthor.setBounds(61, 8, 365, 19);
			textFieldAuthor.setColumns(10);
		}
		return textFieldAuthor;
	}

	public JList<Articulo> getListArticlesByAuthor() {
		if (listArticlesByAuthor == null) {
			listArticlesByAuthor = new JList<Articulo>(articlesModel);
			listArticlesByAuthor.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (e.getFirstIndex() != -1) {
						if (listArticlesByAuthor.getSelectedValue().canBeEditable())
							updateArticle(getListArticlesByAuthor().getSelectedValue());
					}
				}
			});
			listArticlesByAuthor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listArticlesByAuthor.setBorder(new LineBorder(new Color(0, 0, 0)));
			listArticlesByAuthor.setBounds(10, 33, 416, 192);
		}
		return listArticlesByAuthor;
	}

	private void updateArticle(Articulo a) {
		UploadWindow cw = new UploadWindow(this, a);
		cw.setVisible(true);
	}

	public void updateListArticles() {
		listArticlesByAuthor.setSelectedIndex(-1);
		articlesModel.removeAllElements();
		List<Articulo> articles = DataBaseArticle.findArticlesByAuthor(new Autor(textFieldAuthor.getText().trim()));
		for (Articulo a : articles)
			articlesModel.addElement(a);
	}
}
