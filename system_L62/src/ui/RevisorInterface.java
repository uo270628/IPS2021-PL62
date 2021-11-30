package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import business.Articulo;
import persistence.DataBaseManager;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RevisorInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<Articulo> articlesModel;

	private JPanel contentPane;
	private JLabel lblRevisor;
	private JTextField textFieldRevisor;
	private JList<Articulo> listArticlesByRevisor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RevisorInterface frame = new RevisorInterface();
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
	public RevisorInterface() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateListArticles();
			}
		});
		
		setResizable(false);
		this.articlesModel = new DefaultListModel<>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblRevisor());
		contentPane.add(getTextFieldRevisor());
		contentPane.add(getListArticlesByRevisor());
		setLocationRelativeTo(null);

	}

	public JLabel getLblRevisor() {
		if (lblRevisor == null) {
			lblRevisor = new JLabel("Revisor:");
			lblRevisor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblRevisor.setBounds(10, 10, 45, 13);
		}
		return lblRevisor;
	}

	public JTextField getTextFieldRevisor() {
		if (textFieldRevisor == null) {
			textFieldRevisor = new JTextField();
			textFieldRevisor.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if (textFieldRevisor.getText() != null && !textFieldRevisor.getText().trim().isEmpty()) {
						updateListArticles();
					}
				}
			});
			textFieldRevisor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldRevisor.setBounds(61, 8, 365, 19);
			textFieldRevisor.setColumns(10);
		}
		return textFieldRevisor;
	}

	public JList<Articulo> getListArticlesByRevisor() {
		if (listArticlesByRevisor == null) {
			listArticlesByRevisor = new JList<Articulo>(articlesModel);
			listArticlesByRevisor.addMouseListener(new MouseAdapter() {		
				@Override
				public void mouseClicked(MouseEvent e) {
					if (listArticlesByRevisor.getSelectedIndex() != -1) {
						RevisorArticuloActions rac = new RevisorArticuloActions(listArticlesByRevisor.getSelectedValue(),textFieldRevisor.getText());
						rac.setVisible(true);
					}
				}
			});
			listArticlesByRevisor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listArticlesByRevisor.setBorder(new LineBorder(new Color(0, 0, 0)));
			listArticlesByRevisor.setBounds(10, 33, 416, 192);
			listArticlesByRevisor.setCellRenderer(new ArticleListCellRenderer());
		}
		return listArticlesByRevisor;
	}

	
	public void updateListArticles() {
		listArticlesByRevisor.setSelectedIndex(-1);
		articlesModel.removeAllElements();
		if (!textFieldRevisor.getText().isEmpty()) {
			List<Articulo> articles = DataBaseManager.SelectAllArticlesForRevisor(textFieldRevisor.getText());
			for (Articulo a : articles)
				articlesModel.addElement(a);
		}
	}

	
	private class ArticleListCellRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public JComponent getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Articulo article = (Articulo) value;
			String toString = article.getTitle() + " - " + article.getState();
			JLabel label = (JLabel) super.getListCellRendererComponent(list, toString, index, isSelected, cellHasFocus);

			label.setText(label.getText());

			return label;

		}
	}


	public String getNombre() {
		return textFieldRevisor.getText();
	}
}
