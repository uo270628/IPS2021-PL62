package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.Articulo.ArticleState;
import business.Autor;
import business.Tema;
import persistence.DataBaseArticle;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class interfazPublicar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnPublicar;
	private Articulo articulo;
	private JLabel lblDoi;
	private JLabel lblVolumen;
	private JLabel lblDoi_1_1;
	private JLabel lblNVolumen;
	private JLabel lblFecha;
	private JLabel lblSDoi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			List<Autor>list= new ArrayList<Autor>();
			List<String>list2 = new ArrayList<>();
			list.add(new Autor("Pepe"));
			list2.add("a");
			Articulo a = new Articulo("a", "e", new Autor("Pedro"), list, "a", list2,list2, new Tema( "Peces"));
			interfazPublicar dialog = new interfazPublicar(a);
			DataBaseArticle.uploadArticle(a);

			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public interfazPublicar(Articulo articulo) {
		this.articulo=articulo;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtnPublicar());
		contentPanel.add(getLblDoi());
		contentPanel.add(getLblVolumen());
		contentPanel.add(getLblDoi_1_1());
		contentPanel.add(getLblNVolumen());
		contentPanel.add(getLblFecha());
		contentPanel.add(getLblSDoi());
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.setForeground(Color.WHITE);
		btnGenerar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				lblSDoi.setText(UUID.randomUUID().toString());
				lblNVolumen.setText(articulo.getResumen().length()+"");
				Date date= new Date();
				int month= date.getMonth()+1;
				int year= date.getYear()+1900;
				lblFecha.setText(date.getDate()+"/"+month+"/"+year);
				btnGenerar.setVisible(false);

			}
		});
		btnGenerar.setBackground(Color.BLUE);
		btnGenerar.setMnemonic('G');
		btnGenerar.setBounds(141, 149, 104, 34);
		contentPanel.add(btnGenerar);
	}
	private JButton getBtnPublicar() {
		if (btnPublicar == null) {
			btnPublicar = new JButton("Publicar");
			btnPublicar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					articulo.setState(ArticleState.PUBLISHED);
					articulo.setDoi(getLblSDoi().getText());
					articulo.setVolumen(getLblNVolumen().getText());
					articulo.setFechaPublicacion(new Date());
					actualizarArticulo();
					dispose();
				}
			});
			btnPublicar.setBackground(Color.GREEN);
			btnPublicar.setMnemonic('p');
			btnPublicar.setBounds(318, 201, 89, 34);
		}
		return btnPublicar;
	}
	protected void actualizarArticulo() {
		DataBaseArticle.updateArticle(articulo);
		DataBaseArticle.updateArticleAlPublicar(articulo);
		
	}

	private JLabel getLblDoi() {
		if (lblDoi == null) {
			lblDoi = new JLabel("DOI: ");
			lblDoi.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDoi.setBounds(23, 22, 46, 14);
		}
		return lblDoi;
	}
	private JLabel getLblVolumen() {
		if (lblVolumen == null) {
			lblVolumen = new JLabel("Volumen:");
			lblVolumen.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblVolumen.setBounds(23, 63, 104, 14);
		}
		return lblVolumen;
	}
	private JLabel getLblDoi_1_1() {
		if (lblDoi_1_1 == null) {
			lblDoi_1_1 = new JLabel("Fecha de publicacion:");
			lblDoi_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDoi_1_1.setBounds(23, 110, 159, 14);
		}
		return lblDoi_1_1;
	}
	private JLabel getLblNVolumen() {
		if (lblNVolumen == null) {
			lblNVolumen = new JLabel("");
			lblNVolumen.setBounds(260, 64, 46, 14);
			
		      
		}
		return lblNVolumen;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("");
			lblFecha.setBounds(236, 111, 171, 14);
			
		}
		return lblFecha;
	}
	private JLabel getLblSDoi() {
		if (lblSDoi == null) {
			lblSDoi = new JLabel("");
			lblSDoi.setBounds(141, 23, 305, 14);
			
		}
		return lblSDoi;
	}
}
