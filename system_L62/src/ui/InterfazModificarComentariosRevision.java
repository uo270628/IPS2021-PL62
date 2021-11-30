package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Comentario;
import persistence.DataBaseComentario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazModificarComentariosRevision extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblComentario;
	private JTextField textField;
	private JButton btnEnviar;
	private JButton btnVolver;
	private Comentario comentario;

	/**
	 * Create the dialog.
	 */
	public InterfazModificarComentariosRevision(Comentario comentario,Articulo articulo) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.comentario=comentario;
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblComentario());
		contentPanel.add(getTextField());
		contentPanel.add(getBtnEnviar());
		contentPanel.add(getBtnVolver());
		setLocationRelativeTo(null);

		
		
	}
	private JLabel getLblComentario() {
		if (lblComentario == null) {
			lblComentario = new JLabel("Comentario: ");
			lblComentario.setHorizontalAlignment(SwingConstants.LEFT);
			lblComentario.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblComentario.setBounds(31, 63, 190, 29);
		}
		return lblComentario;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(200, 46, 210, 66);
			textField.setColumns(10);
			textField.setText(comentario.getTexto());
		}
		return textField;
	}
	private JButton getBtnEnviar() {
		if (btnEnviar == null) {
			btnEnviar = new JButton("Enviar");
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comentario.setTexto(textField.getText());
					DataBaseComentario.updateComentarios(comentario);
					JOptionPane.showMessageDialog(null,
							"Se ha actualizado el comentario", "Comentario",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
					
				}
			});
			btnEnviar.setBounds(298, 207, 89, 23);
		}
		return btnEnviar;
	}
	private JButton getBtnVolver() {
		
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					dispose();
				}
			});
			btnVolver.setBounds(77, 207, 89, 23);
		}
		return btnVolver;
	}
}
