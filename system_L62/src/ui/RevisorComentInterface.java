package ui;

import javax.swing.JFrame;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import business.Articulo;
import persistence.DataBaseManager;
import ui.listeners.ActionListenerCerrarVentana;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class RevisorComentInterface implements Ventana{

	private JFrame frame;
	private JComboBox<Articulo> comboBox;
	private JTextArea ComentarioTextField;
	private JRadioButton rdbtnAceptar;
	private JRadioButton rdbtnAltoR;
	private JRadioButton rdbtnPocoR;
	private JRadioButton rdbtnRechazar;
	private JButton btnAceptar;
	private JButton btnVolver;
	private final ButtonGroup Aceptabilidad = new ButtonGroup();
	private String revisor;
	
	private JComboBox<String> comboBox_1;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RevisorComentInterface window = new RevisorComentInterface("pruebas");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public RevisorComentInterface(String revisor) {
		this.revisor = revisor;
		initialize();
	}

	
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getComboBox());
		frame.getContentPane().add(getComboBox_1());
		frame.getContentPane().add(getComentarioTextField());
		frame.getContentPane().add(getRdbtnAceptar());
		frame.getContentPane().add(getRdbtnAltoR());
		frame.getContentPane().add(getRdbtnPocoR());
		frame.getContentPane().add(getRdbtnRechazar());
		frame.getContentPane().add(getBtnAceptar());
		frame.getContentPane().add(getBtnCancelar());
		
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
					Articulo articulo = (Articulo) comboBox.getSelectedItem();
					String code = (String) comboBox_1.getSelectedItem();
					if(getRdbtnAceptar().isSelected()) {
						DataBaseManager.InsertReviewComment(revisor, ComentarioTextField.getText(), "Aceptar",articulo.getId(),code);
					}
					else if (getRdbtnAltoR().isSelected()) {
						DataBaseManager.InsertReviewComment(revisor, ComentarioTextField.getText(), "Altamente Recomendable Aceptar",articulo.getId(),code);
					}
					else if (getRdbtnPocoR().isSelected()) {
						DataBaseManager.InsertReviewComment(revisor, ComentarioTextField.getText(), "Poco Recomendable Aceptar",articulo.getId(),code);
					}
					else if (getRdbtnRechazar().isSelected()) {
						DataBaseManager.InsertReviewComment(revisor, ComentarioTextField.getText(), "Rechazar", articulo.getId(),code);
					}
					else {
						throw new IllegalArgumentException();
					}
				}
			});
			btnAceptar.setBounds(232, 227, 89, 23);
		}
		return btnAceptar;
	}
	private JComboBox<Articulo> getComboBox() {
		if (comboBox == null) {
			List<Articulo> list = DataBaseManager.SelectAllArticlesForRevisor(revisor);
			if(!list.isEmpty()) {
				comboBox= new JComboBox<Articulo>();
				comboBox.setModel(new DefaultComboBoxModel<Articulo>(list.toArray(new Articulo[list.size()])));
			}
			else {
				comboBox= new JComboBox<Articulo>();
			}
			comboBox.setBounds(274, 200, 160, 22);
		}
		return comboBox;
	}
	private JComboBox<String> getComboBox_1() {
		if (comboBox_1 == null) {
			List<String> list = new LinkedList<String>();
			list.add("Comentario para autor");
			list.add("Notas para editor");
			list.add("Decision propuesta");
			list.add("Temporal");
			if(!list.isEmpty()) {
				comboBox_1= new JComboBox<String>();
				comboBox_1.setModel(new DefaultComboBoxModel<String>(list.toArray(new String[list.size()])));
			}
			else {
				comboBox_1= new JComboBox<String>();
			}
			comboBox_1.setBounds(274, 170, 160, 22);
		}
		return comboBox_1;
	}
	private JButton getBtnCancelar() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListenerCerrarVentana(this));
			btnVolver.setBounds(335, 227, 89, 23);
		}
		return btnVolver;
	}

	@Override
	public void close() {
		frame.dispose();	
	}
}

