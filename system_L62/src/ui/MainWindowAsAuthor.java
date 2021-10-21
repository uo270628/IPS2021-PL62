package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainWindowAsAuthor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnUploadArticle;
	private JButton btnTerminateArticle;
	private JButton btnUpdateArticle;
	private JButton btnPublishArticle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowAsAuthor frame = new MainWindowAsAuthor();
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
	public MainWindowAsAuthor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnUploadArticle());
		contentPane.add(getBtnTerminateArticle());
		contentPane.add(getBtnUpdateArticle());
		contentPane.add(GetBtnPublishArticle());
	}

	public JButton getBtnUploadArticle() {
		if (btnUploadArticle == null) {
			btnUploadArticle = new JButton("Subir art\u00EDculo");
			btnUploadArticle.setBounds(10, 65, 426, 23);
			btnUploadArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnUploadArticle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirm();
				}
			});
		}
		return btnUploadArticle;
	}
	public JButton getBtnTerminateArticle() {
		if (btnTerminateArticle == null) {
			btnTerminateArticle = new JButton("Terminar art\u00EDculo");
			btnTerminateArticle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sendToAprove();
				}
			});
			btnTerminateArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnTerminateArticle.setBounds(10, 127, 426, 23);
		}
		return btnTerminateArticle;
	}
	public JButton getBtnUpdateArticle() {
		if (btnUpdateArticle == null) {
			btnUpdateArticle = new JButton("Actualizar art\u00EDculo");
			btnUpdateArticle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					updateArticle();
				}
			});
			btnUpdateArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnUpdateArticle.setBounds(10, 182, 426, 23);
		}
		return btnUpdateArticle;
	}
	public JButton GetBtnPublishArticle() {
		if (btnPublishArticle == null) {
			btnPublishArticle = new JButton("Publicar art\u00EDculo");
			btnPublishArticle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					publishArticle();
				}
			});
			btnPublishArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnPublishArticle.setBounds(10, 227, 426, 23);
		}
		return btnPublishArticle;
	}
	
	private void confirm() {
		UploadWindow cw = new UploadWindow(null, null);
		cw.setVisible(true);
	}
	private void publishArticle() {
		IdArticleWindow sw = new IdArticleWindow(2);
		sw.setVisible(true);
	}
	private void sendToAprove() {
		IdArticleWindow sw = new IdArticleWindow(1);
		sw.setVisible(true);
	}
	private void updateArticle() {
		IdArticleWindow sw = new IdArticleWindow(0);
		sw.setVisible(true);
	}
}
