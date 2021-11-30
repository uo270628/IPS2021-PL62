package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAuthor;
	private JButton btnRevisor;
	private JButton btnEditor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnAuthor());
		contentPane.add(getBtnRevisor());
		contentPane.add(getBtnEditor());
		setLocationRelativeTo(null);

	}

	public JButton getBtnAuthor() {
		if (btnAuthor == null) {
			btnAuthor = new JButton("Autor");
			btnAuthor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArticlesByAuthorWindow window = new ArticlesByAuthorWindow();
					window.setVisible(true);
				}
			});
			btnAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAuthor.setBounds(68, 44, 296, 34);
		}
		return btnAuthor;
	}

	public JButton getBtnRevisor() {
		if (btnRevisor == null) {
			btnRevisor = new JButton("Revisor");
			btnRevisor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					RevisorInterface.main(null);
				}
			});
			btnRevisor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnRevisor.setBounds(68, 106, 296, 34);
		}
		return btnRevisor;
	}

	public JButton getBtnEditor() {
		if (btnEditor == null) {
			btnEditor = new JButton("Editor");
			btnEditor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaEditor.main(null);
				}
			});
			btnEditor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnEditor.setBounds(68, 181, 296, 41);
		}
		return btnEditor;
	}
}
