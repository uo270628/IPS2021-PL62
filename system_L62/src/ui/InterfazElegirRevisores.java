package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Articulo;
import business.CartaRevisores;
import business.Revisor;
import business.Tema;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class InterfazElegirRevisores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Revisor> cbRevisores;
	private JLabel lblRevisoresDisponibles;
	private JButton btnAñadirRevisor;
	private JLabel lblRevisoresRestantes;
	private JLabel lblNumeroRevisoresRestantes;
	private JButton btnMandarARevision;
	
	private Articulo articulo;
	private CartaRevisores cartarevisores;
	
	
	/**
	 * Create the frame.
	 */
	public InterfazElegirRevisores(Articulo articulo) {
		this.articulo=articulo;
	
		cartarevisores=  new CartaRevisores();
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbRevisores());
		contentPane.add(getLblRevisoresDisponibles());
		contentPane.add(getBtnAñadirRevisor());
		contentPane.add(getLblRevisoresRestantes());
		contentPane.add(getLblNumeroRevisoresRestantes());
		contentPane.add(getBtnMandarARevision());
		añadirRevisoresAlCombobox();

		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazElegirRevisores frame = new InterfazElegirRevisores(new Articulo(new Tema( "Peces"),"25"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void añadirRevisoresAlCombobox() {
		List<Revisor>listOfRevisores= cartarevisores.getRevisoresPorTema(articulo.getTema().getNombre());
		for (Revisor revisor : listOfRevisores) {
			cbRevisores.addItem(revisor);
		}
		
		
		
	}

	private JComboBox<Revisor> getCbRevisores() {
		if (cbRevisores == null) {
			cbRevisores = new JComboBox<Revisor>();
			cbRevisores.setBounds(204, 151, 250, 29);
		}
		return cbRevisores;
	}
	private JLabel getLblRevisoresDisponibles() {
		if (lblRevisoresDisponibles == null) {
			lblRevisoresDisponibles = new JLabel("Revisores disponibles:");
			lblRevisoresDisponibles.setBounds(52, 151, 142, 29);
		}
		return lblRevisoresDisponibles;
	}
	private JButton getBtnAñadirRevisor() {
		if (btnAñadirRevisor == null) {
			btnAñadirRevisor = new JButton("A\u00F1adir revisor");
			btnAñadirRevisor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					elegirRevisor();
				}

				
			});
			btnAñadirRevisor.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnAñadirRevisor.setBackground(Color.GREEN);
			btnAñadirRevisor.setMnemonic('A');
			btnAñadirRevisor.setBounds(309, 240, 134, 34);
		}
		return btnAñadirRevisor;
	}
	private void elegirRevisor() {
		articulo.añadirRevisor((Revisor) cbRevisores.getSelectedItem());
		cbRevisores.removeItem((Revisor) cbRevisores.getSelectedItem());
		getLblNumeroRevisoresRestantes().setText(articulo.getRevisoresRestantes()+"");
		activarBotonSiguiente();
		
	}
	private void activarBotonSiguiente() {
		if(articulo.getRevisoresRestantes()==0) {
			
			getBtnMandarARevision().setEnabled(true);
			getBtnAñadirRevisor().setEnabled(false);
		}
	}
	private JLabel getLblRevisoresRestantes() {
		if (lblRevisoresRestantes == null) {
			lblRevisoresRestantes = new JLabel("Revisores restantes:");
			lblRevisoresRestantes.setBounds(81, 34, 142, 34);
		}
		return lblRevisoresRestantes;
	}
	private JLabel getLblNumeroRevisoresRestantes() {
		if (lblNumeroRevisoresRestantes == null) {
			lblNumeroRevisoresRestantes = new JLabel("3");
			lblNumeroRevisoresRestantes.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNumeroRevisoresRestantes.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumeroRevisoresRestantes.setBounds(217, 39, 27, 24);
		}
		return lblNumeroRevisoresRestantes;
	}
	private JButton getBtnMandarARevision() {
		if (btnMandarARevision == null) {
			btnMandarARevision = new JButton("Mandar a revision");
			btnMandarARevision.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
			});
			btnMandarARevision.setEnabled(false);
			btnMandarARevision.setMnemonic('M');
			btnMandarARevision.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnMandarARevision.setBackground(Color.GREEN);
			btnMandarARevision.setBounds(273, 306, 181, 34);
		}
		return btnMandarARevision;
	}
}
