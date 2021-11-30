package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import business.Articulo;
import business.Revisor;
import persistence.DataBaseRevisor;
import persistence.DatabaseRecomendacion;

public class AsignarRevisoresRecomendados extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JLabel lblNewLabel;
    private JComboBox<Revisor> comboBox;
    private List<Revisor> revisoresRecomendados;
    private DatabaseRecomendacion dbr;
    private JButton btnNewButton;

    public AsignarRevisoresRecomendados(Articulo a) {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	dbr = new DatabaseRecomendacion(a);
	revisoresRecomendados = dbr.getRecomendaciones();
	getContentPane().setLayout(null);
	getContentPane().add(getLblNewLabel());
	getContentPane().add(getComboBox());
	getContentPane().add(getBtnNewButton());
    }

    private JLabel getLblNewLabel() {
	if (lblNewLabel == null) {
	    lblNewLabel = new JLabel("Revisores recomendados por el autor: ");
	    lblNewLabel.setBounds(10, 11, 195, 14);
	}
	return lblNewLabel;
    }

    private JComboBox<Revisor> getComboBox() {
	if (comboBox == null) {
	    comboBox = new JComboBox<Revisor>();
	    comboBox.setBounds(10, 36, 195, 22);
	    comboBox.setModel(new DefaultComboBoxModel<Revisor>(
		    revisoresRecomendados.toArray(new Revisor[revisoresRecomendados.size()])));
	}
	return comboBox;
    }

    private JButton getBtnNewButton() {
	if (btnNewButton == null) {
	    btnNewButton = new JButton("A\u00F1adir a la revista");
	    btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    DataBaseRevisor dbr = new DataBaseRevisor();
		    dbr.añadirRevisor((Revisor) getComboBox().getSelectedItem());
		}
	    });
	    btnNewButton.setBounds(257, 36, 165, 23);
	}
	return btnNewButton;
    }
}
