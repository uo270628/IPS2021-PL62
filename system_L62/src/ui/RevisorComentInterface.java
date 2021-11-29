package ui;

import javax.swing.JFrame;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

import business.Articulo;
import persistence.DataBaseManager;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class RevisorComentInterface extends JFrame implements Ventana {

	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextArea ComentarioTextField;
	private JRadioButton rdbtnAceptar;
	private JRadioButton rdbtnAltoR;
	private JRadioButton rdbtnPocoR;
	private JRadioButton rdbtnRechazar;
	private JButton btnAceptar;
	private final ButtonGroup Aceptabilidad = new ButtonGroup();
	private String revisor;
	private Articulo articulo;
	private String code;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RevisorComentInterface window = new RevisorComentInterface("pruebas", "Decision propuesta",null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RevisorComentInterface(String revisor, String code, Articulo articulo) {
		this.articulo = articulo;
		this.revisor = revisor;
		this.code = code;
		initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getComentarioTextField());
		if (code.equals("Decision propuesta")) {
			frame.getContentPane().add(getRdbtnAceptar());
			frame.getContentPane().add(getRdbtnAltoR());
			frame.getContentPane().add(getRdbtnPocoR());
			frame.getContentPane().add(getRdbtnRechazar());
		}
		frame.getContentPane().add(getBtnAceptar());

		frame.setVisible(true);
	}

	private JTextArea getComentarioTextField() {
		if (ComentarioTextField == null) {
			ComentarioTextField = new JTextArea();
			ComentarioTextField.setBounds(10, 11, 216, 239);
			ComentarioTextField.setColumns(10);
		}
		return ComentarioTextField;
	}

	private JRadioButton getRdbtnAceptar() {
		if (rdbtnAceptar == null) {
			rdbtnAceptar = new JRadioButton("Aceptar");
			Aceptabilidad.add(rdbtnAceptar);
			rdbtnAceptar.setBounds(232, 11, 196, 23);
		}
		return rdbtnAceptar;
	}

	private JRadioButton getRdbtnAltoR() {
		if (rdbtnAltoR == null) {
			rdbtnAltoR = new JRadioButton("Altamente Recomendable Aceptar");
			Aceptabilidad.add(rdbtnAltoR);
			rdbtnAltoR.setBounds(232, 37, 196, 23);
		}
		return rdbtnAltoR;
	}

	private JRadioButton getRdbtnPocoR() {
		if (rdbtnPocoR == null) {
			rdbtnPocoR = new JRadioButton("Poco Recomendable Aceptar");
			Aceptabilidad.add(rdbtnPocoR);
			rdbtnPocoR.setBounds(232, 63, 196, 23);
		}
		return rdbtnPocoR;
	}

	private JRadioButton getRdbtnRechazar() {
		if (rdbtnRechazar == null) {
			rdbtnRechazar = new JRadioButton("Rechazar");
			rdbtnRechazar.setSelected(true);
			Aceptabilidad.add(rdbtnRechazar);
			rdbtnRechazar.setBounds(232, 89, 196, 23);
		}
		return rdbtnRechazar;
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getRdbtnAceptar().isSelected()) {
						DataBaseManager.InsertReviewComment(revisor, ComentarioTextField.getText(), "Aceptar",
								articulo.getId(), code);
					} else if (getRdbtnAltoR().isSelected()) {
						DataBaseManager.InsertReviewComment(revisor, ComentarioTextField.getText(),
								"Altamente Recomendable Aceptar", articulo.getId(), code);
					} else if (getRdbtnPocoR().isSelected()) {
						DataBaseManager.InsertReviewComment(revisor, ComentarioTextField.getText(),
								"Poco Recomendable Aceptar", articulo.getId(), code);
					} else if (getRdbtnRechazar().isSelected()) {
						DataBaseManager.InsertReviewComment(revisor, ComentarioTextField.getText(), "Rechazar",
								articulo.getId(), code);
					} else {
						throw new IllegalArgumentException();
					}
				}
			});
			btnAceptar.setBounds(232, 227, 89, 23);
		}
		return btnAceptar;
	}

	@Override
	public void close() {
		frame.dispose();
	}
}
