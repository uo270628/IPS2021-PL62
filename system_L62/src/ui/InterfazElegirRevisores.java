package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import business.Articulo;
import business.Autor;
import business.CartaRevisores;
import business.Revisor;
import business.Tema;
import persistence.DataBaseArticle;
import persistence.DataBaseRevisor;

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
	private JLabel lblTiempoDeRevision;
	private JSpinner spTiempoDeRevision;
	private JScrollPane scrollPaneRevisoresSugeridos;
	private JLabel lblRevisoresSugeridos;

	private JList<Revisor> listRevisoresSugeridos;
	private DefaultListModel<Revisor> revisoresModel;

	/**
	 * Create the frame.
	 */
	public InterfazElegirRevisores(Articulo articulo) {
		setResizable(false);
		this.articulo = articulo;
		this.revisoresModel = new DefaultListModel<Revisor>();
		new CartaRevisores();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		contentPane.add(getLblTiempoDeRevision());
		contentPane.add(getSpTiempoDeRevision());
		contentPane.add(getScrollPaneRevisoresSugeridos());
		contentPane.add(getLblRevisoresSugeridos());
		añadirRevisoresAlCombobox();
		setLocationRelativeTo(null);

		updateListRevisores();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					List<Autor> list = new ArrayList<Autor>();
					List<String> list2 = new ArrayList<>();
					list.add(new Autor("Pepe"));
					list2.add("a");
					Articulo a = new Articulo("a", "e", new Autor("Pedro"), list, "a", list2, list2, new Tema("Peces"));
					InterfazElegirRevisores frame = new InterfazElegirRevisores(a);
					DataBaseArticle.uploadArticle(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void añadirRevisoresAlCombobox() {
		DataBaseRevisor data = new DataBaseRevisor();
		List<Revisor> listOfRevisores = data.getAllRevisores();
		for (Revisor revisor : listOfRevisores) {
			cbRevisores.addItem(revisor);
		}
	}

	private JComboBox<Revisor> getCbRevisores() {
		if (cbRevisores == null) {
			cbRevisores = new JComboBox<Revisor>();
			cbRevisores.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					listRevisoresSugeridos.setSelectedIndex(-1);
				}
			});
			cbRevisores.setBounds(204, 171, 250, 29);
		}
		return cbRevisores;
	}

	private JLabel getLblRevisoresDisponibles() {
		if (lblRevisoresDisponibles == null) {
			lblRevisoresDisponibles = new JLabel("Revisores disponibles:");
			lblRevisoresDisponibles.setBounds(20, 171, 142, 29);
		}
		return lblRevisoresDisponibles;
	}

	private JButton getBtnAñadirRevisor() {
		if (btnAñadirRevisor == null) {
			btnAñadirRevisor = new JButton("A\u00F1adir revisor");
			btnAñadirRevisor.addActionListener(new ActionListener() {
				@Override
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
		Revisor selectedRevisor;
		if (listRevisoresSugeridos.getSelectedIndex() != -1) {
			selectedRevisor = listRevisoresSugeridos.getSelectedValue();
		} else {
			selectedRevisor = (Revisor) cbRevisores.getSelectedItem();
		}
		articulo.añadirRevisor(selectedRevisor);
		if (revisoresModel.contains(selectedRevisor)) {
			revisoresModel.removeElement(selectedRevisor);
		}
		cbRevisores.removeItem(selectedRevisor);
		getLblNumeroRevisoresRestantes().setText(articulo.getRevisoresRestantes() + "");
		activarBotonSiguiente();
	}

	private void activarBotonSiguiente() {
		if (articulo.getRevisoresRestantes() == 0) {
			getBtnAñadirRevisor().setEnabled(false);
			articulo.setState(Articulo.ArticleState.IN_REVISION);
			if (Integer.parseInt(getSpTiempoDeRevision().getValue().toString()) > 0) {
				getBtnMandarARevision().setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "El tiempo de revision no puede ser 0", "Tiempo",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private JLabel getLblRevisoresRestantes() {
		if (lblRevisoresRestantes == null) {
			lblRevisoresRestantes = new JLabel("Revisores restantes por elegir:");
			lblRevisoresRestantes.setBounds(0, 35, 198, 34);
		}
		return lblRevisoresRestantes;
	}

	private JLabel getLblNumeroRevisoresRestantes() {
		if (lblNumeroRevisoresRestantes == null) {
			lblNumeroRevisoresRestantes = new JLabel("3");
			lblNumeroRevisoresRestantes.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNumeroRevisoresRestantes.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumeroRevisoresRestantes.setBounds(55, 74, 27, 24);
		}
		return lblNumeroRevisoresRestantes;
	}

	private JButton getBtnMandarARevision() {
		if (btnMandarARevision == null) {
			btnMandarARevision = new JButton("Mandar a revision");
			btnMandarARevision.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actualizarArticulo();
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

	private void actualizarArticulo() {
		articulo.setTiempoMaximoRevision((int) spTiempoDeRevision.getValue());
		DataBaseArticle.updateArticleRevisores(articulo);
		DataBaseArticle.updateArticleTiempoMaximoRevisor(articulo);
	}

	private JLabel getLblTiempoDeRevision() {
		if (lblTiempoDeRevision == null) {
			lblTiempoDeRevision = new JLabel("Tiempo de revision:");
			lblTiempoDeRevision.setBounds(20, 260, 118, 14);
		}
		return lblTiempoDeRevision;
	}

	private JSpinner getSpTiempoDeRevision() {
		if (spTiempoDeRevision == null) {
			spTiempoDeRevision = new JSpinner();
			spTiempoDeRevision.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					activarBotonSiguiente();
				}
			});
			spTiempoDeRevision.setBounds(148, 253, 49, 29);
		}
		return spTiempoDeRevision;
	}

	public JScrollPane getScrollPaneRevisoresSugeridos() {
		if (scrollPaneRevisoresSugeridos == null) {
			scrollPaneRevisoresSugeridos = new JScrollPane();
			scrollPaneRevisoresSugeridos.setBounds(204, 78, 250, 63);
			scrollPaneRevisoresSugeridos.setViewportView(getListRevisoresSugeridos());
		}
		return scrollPaneRevisoresSugeridos;
	}

	public JLabel getLblRevisoresSugeridos() {
		if (lblRevisoresSugeridos == null) {
			lblRevisoresSugeridos = new JLabel("Revisores sugeridos:");
			lblRevisoresSugeridos.setBounds(204, 46, 134, 13);
		}
		return lblRevisoresSugeridos;
	}

	public JList<Revisor> getListRevisoresSugeridos() {
		if (listRevisoresSugeridos == null) {
			listRevisoresSugeridos = new JList<Revisor>(revisoresModel);
			listRevisoresSugeridos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listRevisoresSugeridos;
	}

	/**
	 * Actualiza la lista de revisores recomendados
	 */
	private void updateListRevisores() {
		listRevisoresSugeridos.setSelectedIndex(-1);
		revisoresModel.removeAllElements();
		List<Revisor> revisores = new DataBaseRevisor().getRevisores();
		List<Revisor> revisoresOrdenados = orderRevisores(revisores);
		for (Revisor r : revisoresOrdenados)
			revisoresModel.addElement(r);
	}

	/**
	 * Devuelve una lista de revisores filtrada y ordenada a partir de una lista de
	 * revisores cualquiera
	 * 
	 * @param lista original
	 * @return lista ordenada
	 */
	private List<Revisor> orderRevisores(List<Revisor> original) {
		List<Revisor> resultado = new ArrayList<>();
		int maxComparation;
		int comparation;
		Revisor revisor;
		for (int i = 0; i < 3 && i < original.size(); i++) {
			maxComparation = -1;
			revisor = null;
			for (Revisor r : original) {
				comparation = 0;
				if (!resultado.contains(r)) {
					for (Tema t : r.getListOfTemas()) {
						if (articulo.getTema().getNombre().toLowerCase().equals(t.getNombre().toLowerCase())) {
							comparation++;
						}
						for (String keyword : articulo.getKeywords()) {
							if (keyword.toLowerCase().equals(t.getNombre().toLowerCase())) {
								comparation++;
							}
						}
						if (articulo.getTitle().toLowerCase().equals(t.getNombre().toLowerCase())) {
							comparation++;
						}
						if (articulo.getResumen().toLowerCase().contains(t.getNombre().toLowerCase())) {
							comparation++;
						}
					}
					if (comparation > maxComparation) {
						revisor = r;
						maxComparation = comparation;
					}
				}
			}
			if (revisor != null)
				resultado.add(revisor);
		}
		return resultado;
	}
}
