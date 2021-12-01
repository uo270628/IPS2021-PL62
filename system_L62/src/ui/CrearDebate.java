package ui;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;



import business.Articulo;
import business.Debate;

import persistence.DataBaseManager;

public class CrearDebate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JSpinner spinner;
	private JButton btnNewButton;
	private JLabel lblNewLabel_3;
	private Articulo a;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CrearDebate frame = new CrearDebate();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CrearDebate(Articulo a) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.a = a;
		setBounds(100, 100, 412, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getSpinner());
		contentPane.add(getBtnNewButton());
		contentPane.add(getLblNewLabel_3());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Dias de debate:  ");
			lblNewLabel.setBounds(74, 53, 87, 14);
		}
		return lblNewLabel;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
			spinner.setBounds(171, 50, 30, 20);
		}
		return spinner;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Crear debate para este Articulo");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Debate debate = new Debate(UUID.randomUUID().toString(), a.getId(), LocalDateTime.now().plusDays((Integer)getSpinner().getValue()));
					DataBaseManager.crearDebate(a, debate);

				}
			});
			btnNewButton.setBounds(183, 81, 203, 23);
		}
		return btnNewButton;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Selecciona cuantos dias va a durar el debate\r\n");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel_3.setBounds(10, 11, 283, 23);
		}
		return lblNewLabel_3;
	}

}
