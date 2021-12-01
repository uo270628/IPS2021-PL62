package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business.Articulo;
import business.Comentario;
import persistence.DataBaseComentario;
import persistence.DataBaseManager;

public class SeeRevisionCommentsForAuthorWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<Comentario> commentsModel;
	private JPanel contentPane;
	private JButton btnBack;
	private JScrollPane scrollPaneComments;
	private JList<Comentario> listComments;
	private JScrollPane scrollPaneCommentInfo;
	private JTextArea textAreaCommentInfo;
	private JLabel lblCommentInfo;
	private JLabel lblComments;
	private JButton btnShowCard;

	private Articulo article;

	/**
	 * Create the frame.
	 */
	public SeeRevisionCommentsForAuthorWindow(Articulo article) {
		this.commentsModel = new DefaultListModel<>();
		this.article = article;
		article.setCarta(DataBaseManager.getCartaArticulo(article.getId()));
		List<Comentario> comentarios = DataBaseComentario.getComentariosDeUnArticulo(article);
		for (Comentario c : comentarios) {
			if (c.getType().equals("Comentario para autor")) {
				commentsModel.addElement(c);
			}
		}

		setResizable(false);
		setModal(true);
		setTitle("Comentarios de revisi\u00F3n");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnBack());
		contentPane.add(getScrollPaneComments());
		contentPane.add(getScrollPaneCommentInfo());
		contentPane.add(getLblCommentInfo());
		contentPane.add(getLblComments());
		contentPane.add(getBtnShowCard());
		setLocationRelativeTo(null);

	}

	public JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Atr\u00E1s");
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					disposeWindow();
				}
			});
			btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnBack.setBounds(341, 232, 85, 21);
		}
		return btnBack;
	}

	public JScrollPane getScrollPaneComments() {
		if (scrollPaneComments == null) {
			scrollPaneComments = new JScrollPane();
			scrollPaneComments.setBounds(341, 41, 85, 148);
			scrollPaneComments.setViewportView(getListComments());
		}
		return scrollPaneComments;
	}

	public JList<Comentario> getListComments() {
		if (listComments == null) {
			listComments = new JList<Comentario>(commentsModel);
			listComments.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					showComment();
				}
			});
			listComments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listComments.setCellRenderer(new CommentListCellRenderer());
		}
		return listComments;
	}

	public JScrollPane getScrollPaneCommentInfo() {
		if (scrollPaneCommentInfo == null) {
			scrollPaneCommentInfo = new JScrollPane();
			scrollPaneCommentInfo.setBounds(10, 41, 321, 212);
			scrollPaneCommentInfo.setViewportView(getTextAreaCommentInfo());
		}
		return scrollPaneCommentInfo;
	}

	public JTextArea getTextAreaCommentInfo() {
		if (textAreaCommentInfo == null) {
			textAreaCommentInfo = new JTextArea();
		}
		return textAreaCommentInfo;
	}

	public JLabel getLblCommentInfo() {
		if (lblCommentInfo == null) {
			lblCommentInfo = new JLabel("Informaci\u00F3n del comentario:");
			lblCommentInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblCommentInfo.setBounds(10, 12, 162, 13);
		}
		return lblCommentInfo;
	}

	public JLabel getLblComments() {
		if (lblComments == null) {
			lblComments = new JLabel("Comentarios:");
			lblComments.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblComments.setBounds(341, 13, 85, 13);
		}
		return lblComments;
	}

	public JButton getBtnShowCard() {
		if (btnShowCard == null) {
			btnShowCard = new JButton("Ver carta");
			btnShowCard.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					textAreaCommentInfo.setText(article.getCarta());
				}
			});
			btnShowCard.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnShowCard.setBounds(341, 201, 85, 21);
			if(article.getCartaObject()==null)
				btnShowCard.setEnabled(false);
			else
				btnShowCard.setEnabled(true);
		}
		return btnShowCard;
	}

	private void disposeWindow() {
		this.dispose();
	}

	private void showComment() {
		if (listComments.getSelectedIndex() != -1) {
			Comentario comment = listComments.getSelectedValue();
			getTextAreaCommentInfo()
					.setText("Comentario:\n" + comment.getTexto() + "\nVeredicto: " + comment.getRecomendacion());
		}
	}

	/**
	 * Clase para mostrar en el JList la información requerida de cada comentario
	 *
	 */
	private class CommentListCellRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public JComponent getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Comentario comment = (Comentario) value;
			String toString = "R" + comment.getIdRevisor() + " - " + comment.getRecomendacion();
			JLabel label = (JLabel) super.getListCellRendererComponent(list, toString, index, isSelected, cellHasFocus);

			label.setText(label.getText());

			return label;

		}
	}

}
