package ui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Articulo.ArticleState;
import persistence.DataBaseArticle;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class InterfazAceptarConCambiosMenores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAceptar;
	private JButton btnRechazar;
	private JLabel lblNewLabel;
	private Articulo articulo;

	/**
	 * Create the dialog.
	 */
	public InterfazAceptarConCambiosMenores(Articulo articulo) {
		setResizable(false);
		setLocationRelativeTo(null);
		this.articulo=articulo;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtnAceptar());
		contentPanel.add(getBtnRechazar());
		contentPanel.add(getLblNewLabel());
	}

	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					articulo.setState(ArticleState.ACCEPTED);
					DataBaseArticle.updateArticle(articulo);
					dispose();
				}
			});
			btnAceptar.setBackground(Color.GREEN);
			btnAceptar.setMnemonic('a');
			btnAceptar.setBounds(63, 165, 89, 41);
		}
		return btnAceptar;
	}
	private JButton getBtnRechazar() {
		if (btnRechazar == null) {
			btnRechazar = new JButton("Rechazar");
			btnRechazar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					articulo.setState(ArticleState.REJECTED);
					DataBaseArticle.updateArticle(articulo);
					dispose();
				}
			});
			btnRechazar.setBackground(Color.RED);
			btnRechazar.setMnemonic('r');
			btnRechazar.setBounds(268, 165, 89, 41);
		}
		return btnRechazar;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Desea aceptar el articulo con cambios menores");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(23, 42, 378, 46);
		}
		return lblNewLabel;
	}
}
