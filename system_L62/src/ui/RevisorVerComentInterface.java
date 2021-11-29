package ui;

import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import business.Comentario;
import persistence.DataBaseManager;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;

public class RevisorVerComentInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private String revisor;
	private JList<Comentario> listCommentsByRevisor;
	private DefaultListModel<Comentario> commentsModel;

	/**
	 * Create the application.
	 * 
	 * @param string
	 */
	public RevisorVerComentInterface(String revisor) {
		this.revisor = revisor;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		this.commentsModel = new DefaultListModel<>();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getListCommentsByRevisor());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private JList<Comentario> getListCommentsByRevisor() {
		if (listCommentsByRevisor == null) {
			listCommentsByRevisor = new JList<Comentario>(commentsModel);
			listCommentsByRevisor.addMouseListener(new MouseAdapter() {		
				@Override
				public void mouseClicked(MouseEvent e) {
					if (listCommentsByRevisor.getSelectedIndex() != -1) {
						JOptionPane.showMessageDialog(listCommentsByRevisor, listCommentsByRevisor.getSelectedValue().getTexto());
					}
				}
			});
			
			listCommentsByRevisor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCommentsByRevisor.setBorder(new LineBorder(new Color(0, 0, 0)));
			listCommentsByRevisor.setBounds(10, 33, 416, 192);
			listCommentsByRevisor.setCellRenderer(new DefaultListCellRenderer());
			updateList();
		}
		return listCommentsByRevisor;
	}

	public void updateList() {
		listCommentsByRevisor.setSelectedIndex(-1);
		commentsModel.removeAllElements();
		List<Comentario> list = DataBaseManager.SelectComentsVisibleForRevisor(revisor);
		for (Comentario a : list)
			if (a.getTexto() != "") {
				commentsModel.addElement(a);
			}
	}
}
